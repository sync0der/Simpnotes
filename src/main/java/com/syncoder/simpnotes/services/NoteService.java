package com.syncoder.simpnotes.services;

import com.syncoder.simpnotes.domain.Note;
import com.syncoder.simpnotes.repositories.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;

    public List<Note> findAllNotes() {
        return noteRepository.findAll();
    }

    public void saveNote(Note note){
        noteRepository.save(note);
    }

    public List<Note> findNotesByTag(String tag) {
        return noteRepository.findByTag(tag);
    }
}
