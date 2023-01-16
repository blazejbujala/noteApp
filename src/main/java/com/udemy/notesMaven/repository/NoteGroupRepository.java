package com.udemy.notesMaven.repository;

import com.udemy.notesMaven.model.NoteGroup;

import java.util.List;
import java.util.Optional;

public interface NoteGroupRepository {
    List<NoteGroup> findAll();

    Optional<NoteGroup> findById(Integer id);

    boolean existsByDoneIsFalseAndProject_Id(Integer projectId);

    NoteGroup save(NoteGroup entity);

}
