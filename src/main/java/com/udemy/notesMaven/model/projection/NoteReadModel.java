package com.udemy.notesMaven.model.projection;

import com.udemy.notesMaven.model.Note;

public class NoteReadModel {

    private String description;
    private boolean done;

    public NoteReadModel(Note source){
        description = source.getDescription();
        done = source.isDone();
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

}
