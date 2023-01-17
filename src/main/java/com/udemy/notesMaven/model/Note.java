package com.udemy.notesMaven.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Entity
@RequiredArgsConstructor
public class Note {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    @NotBlank(message = "Note's description must not be blank")
    private String description;
    private boolean done;
    private LocalDateTime deadLine;

    public Note(String description, LocalDateTime deadLine ) {
        this.description = description;
        this.deadLine = deadLine;
    }

    @Embedded
    private Audit audit = new Audit();
    @ManyToOne
    @JoinColumn(name="note_group_id")
    private NoteGroup group;

    public LocalDateTime getDeadline() {
        return deadLine;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadLine = deadline;
    }

    public int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public NoteGroup getGroup() {
        return group;
    }

    public void setGroup(NoteGroup group) {
        this.group = group;
    }

    public void updateFrom(Note source){
        description = source.description;
        done = source.done;
        deadLine = source.deadLine;
        group = source.group;
    }

}
