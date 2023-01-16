package com.udemy.notesMaven.controller;

import com.udemy.notesMaven.model.Note;
import com.udemy.notesMaven.repository.NoteRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
public class NoteController {

    private final NoteRepository repository;

    public NoteController(final NoteRepository repository) {
        this.repository = repository;
    }

    @PostMapping(value = "/notes")
    ResponseEntity<URI> addNote(@RequestBody @Valid Note note) {
        log.info("Adding Note with desc : " + note.getDescription());
        repository.save(note);
        return ResponseEntity.created(URI.create("localhote:8080/note/" + note.getId())).build();
    }

    @GetMapping(value = "/notes", params = {"!page", "!size", "!sort"})
    ResponseEntity<List<Note>> getAllNotes() {
        log.info("Exposing all Notes");
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping(value = "/notes/{id}")
    ResponseEntity<Note> getNote(@PathVariable int id) {
        log.info("Exposing Notes");
        return ResponseEntity.ok(repository.findById(id).get());
    }

    @PutMapping(value = "/notes/{id}")
    ResponseEntity<Note> updateNote(@PathVariable int id, @RequestBody @Valid Note updatedNote) {
        if(!repository.existsById(id)){
            log.warn("Note " + id + " not found");
            return ResponseEntity.notFound().build();
        }
        repository.findById(id)
                        .ifPresent(note -> {
                            note.updateFrom(updatedNote);
                        repository.save(note);
                        });
        log.warn("Updating note " + id);
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @PatchMapping(value = "/notes/{id}")
    ResponseEntity<?> togleNote(@PathVariable int id) {
        if(!repository.existsById(id)){
            log.warn("Note " + id + " not found");
            return ResponseEntity.notFound().build();
        }
        repository.findById(id)
                        .ifPresent(note-> {
                            note.setDone(!note.isDone());
                        });
        log.warn("Changing status of note no " + id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/notes/{id}")
    ResponseEntity deleteNote(@PathVariable int id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
