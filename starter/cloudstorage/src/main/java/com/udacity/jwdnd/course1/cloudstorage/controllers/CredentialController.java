package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/credentials")
public class CredentialController {

    CredentialService credentialService;

    public CredentialController(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @DeleteMapping
    public String deleteCredential(@RequestParam("id") int credentialid){
        if(credentialid > 0){
            credentialService.deleteCredential(credentialid);
            return "/result?success";
        }
        return "/result?error";
    }

    @PostMapping
    public String addOrUpdateCredential(Authentication authentication, Credential credential){
        User user = (User)authentication.getPrincipal();
        if(credential.getCredentialid() > 0)
            credentialService.updateCredential(credential);
        else
            credentialService.insertCredential(credential, user.getUserid());
        return "/result/success";
    }
}
