package com.udemy.notesMaven.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

@Entity
@Table(name = "projects")
public class Project {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    @NotBlank(message = "Project's description must not be blank")
    private String description;
    @OneToMany(mappedBy = "project")
     private Set<NoteGroup> groups;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
    private Set<ProjectSteps> steps;

    public int getId() {
        return id;
    }

    Set<ProjectSteps> getSteps() {
        return steps;
    }

    void setSteps(Set<ProjectSteps> steps) {
        this.steps = steps;
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

    public Set<NoteGroup> getGroups() {
        return groups;
    }

    public void setGroups(Set<NoteGroup> groups) {
        this.groups = groups;
    }
}
