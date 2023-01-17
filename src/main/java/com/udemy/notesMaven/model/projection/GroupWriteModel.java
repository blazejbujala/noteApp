package com.udemy.notesMaven.model.projection;

import com.udemy.notesMaven.model.NoteGroup;

import java.util.Set;
import java.util.stream.Collectors;

public class GroupWriteModel {
    private  String description;
    private Set<NoteWriteModel> notes;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<NoteWriteModel> getNotes() {
        return notes;
    }

    public void setNotes(Set<NoteWriteModel> notes) {
        this.notes = notes;
    }

    public NoteGroup toNoteGroup (){
        var results = new NoteGroup();
        results.setDescription(description);
        results.setNotes(notes.stream()
                .map(NoteWriteModel::toNote)
                .collect(Collectors.toSet()));
        return results;
    }
}
