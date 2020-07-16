package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/files")
public class FileController {
    FileService fileService;
    UserService userService;

    public FileController(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }

    @PostMapping
    public String addFile(Authentication authentication, MultipartFile fileUpload) throws IOException {
        String username = authentication.getName();
        User user = (User) userService.loadUserByUsername(username);
        if(fileUpload.isEmpty())
            return "redirect:/result?error";
        int serviceResult = fileService.addFile(fileUpload, user.getUserid());
        if(serviceResult == -1)
            return "redirect:/result?error";
        return "redirect:/result?success";
    }

    @GetMapping("/delete")
    public String deleteFile(@RequestParam("id") int fileid){
        fileService.deleteFile(fileid);
        return "redirect:/result?success";
    }
}
