package com.syncoder.simpnotes.controllers;

import com.syncoder.simpnotes.domain.Note;
import com.syncoder.simpnotes.domain.User;
import com.syncoder.simpnotes.services.NoteService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @GetMapping("/")
    public String message(@RequestParam(required = false) String filter, Model model) {
        List<Note> notes;
        if (filter != null && !filter.isEmpty())
            notes = noteService.findNotesByTag(filter);
        else
            notes = noteService.findAllNotes();
        model.addAttribute("notes", notes);
        model.addAttribute("filter", filter);
        return "note";
    }

    @PostMapping("/")
    public String message(@AuthenticationPrincipal User user, @RequestParam String text, @RequestParam String tag) {
        noteService.saveNote(new Note(text, tag, user));
        return "redirect:/";
    }



}
