package com.udemy.notesMaven.adaptor;

import com.udemy.notesMaven.model.Project;
import com.udemy.notesMaven.repository.ProjectRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SqlProjectRepository extends ProjectRepository, JpaRepository<Project, Integer> {

    @Override
    @Query("from Project g join fetch g.steps")
    List<Project> findAll();

}
