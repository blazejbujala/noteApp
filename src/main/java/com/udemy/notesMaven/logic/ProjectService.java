package com.udemy.notesMaven.logic;

import com.udemy.notesMaven.NoteConfigurationProperties;
import com.udemy.notesMaven.model.Note;
import com.udemy.notesMaven.model.NoteGroup;
import com.udemy.notesMaven.model.Project;
import com.udemy.notesMaven.model.projection.GroupReadModel;
import com.udemy.notesMaven.repository.NoteGroupRepository;
import com.udemy.notesMaven.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {
    private ProjectRepository projectRepository;
    private NoteGroupRepository noteGroupRepository;
    private NoteConfigurationProperties noteConfigurationProperties;

    public ProjectService(final ProjectRepository projectRepository, final NoteGroupRepository noteGroupRepository) {
        this.projectRepository = projectRepository;
        this.noteGroupRepository = noteGroupRepository;
    }

    public List<Project> readAll() {
        return projectRepository.findAll().stream().toList();
    }

    public void createProject(Project source) {
        projectRepository.save(source);
    }

    public GroupReadModel createGroup(int projectId, LocalDateTime deadLine) {
        if (!noteConfigurationProperties.getTemplate().isAllowMultipleNotes() &&
                noteGroupRepository.existsByDoneIsFalseAndProject_Id(projectId)) {
            throw new IllegalStateException("Multiple undone Project groups are not allowed");
        }
        NoteGroup result = projectRepository.findById(projectId)
                .map(project -> {
                    var targetGroup = new NoteGroup();
                    targetGroup.setDescription(project.getDescription());
                    targetGroup.setNotes(
                            project.getSteps()
                                    .stream()
                                    .map(projectSteps -> new Note(
                                            projectSteps.getDescription(),
                                            deadLine.plusDays(projectSteps.getDaysToDeadLine())))
                                    .collect(Collectors.toSet())
                    );
                    return targetGroup;
                }).orElseThrow(() -> new IllegalArgumentException("No project with id: " + projectId));
        return new GroupReadModel(result);
    }
}
