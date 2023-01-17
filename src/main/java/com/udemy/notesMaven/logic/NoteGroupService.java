package com.udemy.notesMaven.logic;

import com.udemy.notesMaven.model.NoteGroup;
import com.udemy.notesMaven.model.projection.GroupReadModel;
import com.udemy.notesMaven.model.projection.GroupWriteModel;
import com.udemy.notesMaven.repository.NoteGroupRepository;
import com.udemy.notesMaven.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteGroupService {
    private NoteGroupRepository repository;
    private NoteRepository noteRepository;

    NoteGroupService(final NoteGroupRepository repository, final NoteRepository noteRepository) {
        this.repository = repository;
        this.noteRepository = noteRepository;
    }

    public GroupReadModel createGroup(GroupWriteModel source) {
        NoteGroup noteGroup = repository.save(source.toNoteGroup());
        return new GroupReadModel(noteGroup);
    }

    public List<GroupReadModel> readAll() {
        return repository.findAll().stream()
                .map(GroupReadModel::new)
                .collect(Collectors.toList());
    }

    public void toggleGroup(int groupId) {
        if (noteRepository.existsByDoneIsFalseAndGroup_Id(groupId)) {
            throw new IllegalStateException("Group has undone notes, please complete all notes first");
        }
        NoteGroup results = repository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("Note group with id: " + groupId + " not found"));
        results.setDone(!results.isDone());
    }
}
