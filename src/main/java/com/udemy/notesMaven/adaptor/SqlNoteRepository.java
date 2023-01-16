package com.udemy.notesMaven.adaptor;

import com.udemy.notesMaven.model.Note;
import com.udemy.notesMaven.repository.NoteRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface SqlNoteRepository extends JpaRepository<Note, Integer>, NoteRepository {
    @Override
    @Query(nativeQuery = true, value = "select count(*) > 0 from note where id=:id")
    boolean existsById(@Param("id") Integer id);

    @Override
    boolean existsByDoneIsFalseAndGroup_Id(Integer groupId);

}



