package com.udemy.notesMaven.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "project_steps")
public class ProjectSteps {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    @NotBlank(message = "Project step's description must not be blank")
    private String description;
    private int daysToDeadLine;
    @ManyToOne
    @JoinColumn(name="project_id")
    private Project project;

    public int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    void setDescription(String description) {
        this.description = description;
    }

    public int getDaysToDeadLine() {
        return daysToDeadLine;
    }

    void setDaysToDeadLine(int daysToDeadLine) {
        this.daysToDeadLine = daysToDeadLine;
    }

    Project getProject() {
        return project;
    }

    void setProject(Project project) {
        this.project = project;
    }
}
