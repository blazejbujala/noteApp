package com.udemy.notesMaven.logic;

import com.udemy.notesMaven.model.Note;
import com.udemy.notesMaven.repository.NoteGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TempService {
    @Autowired
    List<String> temp(NoteGroupRepository repository){
        return repository.findAll().stream()
                .flatMap(noteGroup -> noteGroup.getNotes().stream())
                .map(Note::getDescription)
                .collect(Collectors.toList());
    }
}
