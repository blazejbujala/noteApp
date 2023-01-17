package com.udemy.notesMaven.model.projection;

import com.udemy.notesMaven.model.Note;

import java.time.LocalDateTime;

public class NoteWriteModel {

    private String description;
    private LocalDateTime deadLine;

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

    public Note toNote (){
        return new Note(description, deadLine);
    }
}
