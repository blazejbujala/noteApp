package com.udemy.notesMaven.repository;

import com.udemy.notesMaven.model.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface NoteRepository {

    List<Note> findAll();

    Page<Note> findAll(Pageable page);

    Optional<Note> findById(Integer i);

    Note save (Note note);

    boolean existsById(Integer id);
    boolean existsByDoneIsFalseAndGroup_Id(Integer groupId);

    void deleteById(Integer id);
}
