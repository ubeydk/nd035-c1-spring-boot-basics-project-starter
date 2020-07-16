package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/credentials")
public class CredentialController {

    CredentialService credentialService;
    UserService userService;

    public CredentialController(UserService userService, CredentialService credentialService) {
        this.userService = userService;
        this.credentialService = credentialService;
    }

    @PostMapping
    public String addOrUpdateCredential(Authentication authentication, Credential credential){
        String username = authentication.getName();
        User user = (User)userService.loadUserByUsername(username);
        if(credential.getCredentialid() > 0)
            credentialService.updateCredential(credential);
        else
            credentialService.insertCredential(credential, user.getUserid());
        return "redirect:/result?success";
    }

    @GetMapping("/delete")
    public String deleteCredential(@RequestParam("id") int credentialid){
        if(credentialid > 0){
            credentialService.deleteCredential(credentialid);
            return "redirect:/result?success";
        }
        return "redirect:/result?error";
    }
}
