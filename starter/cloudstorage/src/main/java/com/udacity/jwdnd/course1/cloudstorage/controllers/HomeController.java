package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {
    CredentialService credentialService;
    FileService fileService;
    NoteService noteService;
    UserService userService;

    public HomeController(UserService userService, CredentialService credentialService, FileService fileService, NoteService noteService) {
        this.userService = userService;
        this.credentialService = credentialService;
        this.fileService = fileService;
        this.noteService = noteService;
    }

    @GetMapping("/login")
    public String loginView(){
        return "login";
    }

    @GetMapping("/")
    public String defaultView(){
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String getHomePage(Authentication authentication, Model model){
        String username = authentication.getName();
        User user = (User)userService.loadUserByUsername(username);
        model.addAttribute("files", fileService.getHomeFilesByUser(user.getUserid()));
        model.addAttribute("notes", noteService.getByUserId(user.getUserid()));
        model.addAttribute("credentials", credentialService.getByUserid(user.getUserid()));
        return "home";
    }

    @GetMapping("/result")
    public String getResultPage(){
        return "result";
    }
}
