package com.udemy.notesMaven.controller;

import com.udemy.notesMaven.NoteConfigurationProperties;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {

    private DataSourceProperties dataSource;
    private NoteConfigurationProperties prop;

    InfoController(final DataSourceProperties dataSource,
                   final NoteConfigurationProperties prop) {
        this.dataSource = dataSource;
        this.prop = prop;
    }

    @GetMapping("/info/prop")
    boolean prop(){
        return prop.getTemplate().isAllowMultipleNotes();
    }

    @GetMapping("/info/url")
    String url(){
        return dataSource.getUrl();
    }
}
