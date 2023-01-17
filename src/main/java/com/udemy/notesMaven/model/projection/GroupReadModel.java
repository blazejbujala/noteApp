package com.udemy.notesMaven.model.projection;

import com.udemy.notesMaven.model.Note;
import com.udemy.notesMaven.model.NoteGroup;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public class GroupReadModel {

    private String description;

    private LocalDateTime deadLine;

    private Set<NoteReadModel> notes;

    public GroupReadModel(NoteGroup noteGroup){
        description = noteGroup.getDescription();
        noteGroup
                .getNotes()
                .stream()
                .map(Note::getDeadline)
                .max(LocalDateTime::compareTo)
                .ifPresent(date -> deadLine = date);
        notes = noteGroup.getNotes().stream()
                .map(NoteReadModel::new)
                .collect(Collectors.toSet());
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(LocalDateTime deadLine) {
        this.deadLine = deadLine;
    }

    public Set<NoteReadModel> getNotes() {
        return notes;
    }

    public void setNotes(Set<NoteReadModel> notes) {
        this.notes = notes;
    }

}
