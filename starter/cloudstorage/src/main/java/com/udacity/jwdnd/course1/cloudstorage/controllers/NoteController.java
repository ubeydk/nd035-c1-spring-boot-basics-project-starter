package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/notes")
public class NoteController {
    NoteService noteService;
    UserService userService;

    public NoteController(UserService userService, NoteService noteService) {
        this.userService = userService;
        this.noteService = noteService;
    }

    @PostMapping
    public String insertOrAddNote(Authentication authentication, Note note){
        System.out.println("***********triggered");
        System.out.println(note.getNotedescription());
        System.out.println(note.getNotetitle());
        System.out.println(note.getNoteid());
        System.out.println(note.getUserid());
        System.out.println("****************");
        String username = authentication.getName();
        User user = (User)userService.loadUserByUsername(username);
        if(note.getNoteid() > 0){
            noteService.updateNote(note);
        }else{
            noteService.insertNote(note, user.getUserid());
        }
        return "redirect:/result?success";
    }


    @GetMapping("/delete")
    public String deleteNote(@RequestParam("id") int noteid){
        if(noteid <= 0)
            return "redirect:/result?error";
        noteService.deleteNote(noteid);
        return "redirect:/result?success";
    }
}
