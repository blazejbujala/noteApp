package com.udemy.notesMaven.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

@Embeddable
public class Audit {

    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

    @PrePersist
    void createDate (){
        createdOn = LocalDateTime.now();
    }

    @PreUpdate
    void updateDate (){
        updatedOn = LocalDateTime.now();
    }

}
