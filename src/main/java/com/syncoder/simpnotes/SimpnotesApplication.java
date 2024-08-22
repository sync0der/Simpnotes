package com.syncoder.simpnotes;

import com.syncoder.simpnotes.domain.Note;
import com.syncoder.simpnotes.repositories.NoteRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SimpnotesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpnotesApplication.class, args);
    }


}
