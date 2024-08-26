package com.syncoder.simpnotes.services;

import com.syncoder.simpnotes.domain.Image;
import com.syncoder.simpnotes.domain.Note;
import com.syncoder.simpnotes.repositories.ImageRepository;
import com.syncoder.simpnotes.repositories.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;
    private final ImageRepository imageRepository;

    public List<Note> findAllNotes() {
        return noteRepository.findAll();
    }

    public void saveNote(Note note) {
        noteRepository.save(note);
    }

    public List<Note> findNotesByTag(String tag) {
        return noteRepository.findByTag(tag);
    }

    public void saveNoteWithImage(Note note, MultipartFile file) {
        Image image = new Image();
        try {
            extractImage(image, note, file);
        } catch (IOException e) {
            throw new RuntimeException();
        }
        noteRepository.save(note);
    }

    private void extractImage(Image image, Note note, MultipartFile file) throws IOException {
        if (file.getSize() != 0) {
            image = getImageEntity(file);
            addImageToNote(image, note);
        }
    }

    private Image getImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }

    private void addImageToNote(Image image, Note note) {
        image.setNote(note);
        note.getImages().add(image);
    }

}
