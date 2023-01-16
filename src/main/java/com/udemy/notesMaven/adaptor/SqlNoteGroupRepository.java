package com.udemy.notesMaven.adaptor;

import com.udemy.notesMaven.model.NoteGroup;
import com.udemy.notesMaven.repository.NoteGroupRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SqlNoteGroupRepository extends NoteGroupRepository, JpaRepository<NoteGroup, Integer> {
    @Override
    @Query("from NoteGroup g join fetch g.notes")
    List<NoteGroup> findAll();

    @Override
    boolean existsByDoneIsFalseAndProject_Id(Integer projectId);
}
