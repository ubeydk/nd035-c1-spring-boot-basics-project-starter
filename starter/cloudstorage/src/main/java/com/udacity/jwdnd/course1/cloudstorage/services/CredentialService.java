package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService {
    private final CredentialMapper credentialMapper;
    private final EncryptionService encryptionService;

    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
    }

    public Credential getById(int credentialid){
        return credentialMapper.getById(credentialid);
    }

    public List<Credential> getByUserid(int userid){
        return credentialMapper.getByUserid(userid);
    }

    public int insertCredential(Credential credential, int userid){
        return credentialMapper.insertCredential(credential, userid);
    }

    public int deleteCredential(int credentialid){
        return credentialMapper.deleteById(credentialid);
    }

    public int updateCredential(Credential credential){
        return credentialMapper.updateById(credential);
    }

    private Credential encryptCredential(Credential credential){
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        credential.setPassword(encryptionService.encryptValue(credential.getPassword(),encodedKey));
        credential.setKey(encodedKey);
        return credential;
    }

    private String getDecryptedPassword(Credential credential){
        return encryptionService.decryptValue(credential.getPassword(), credential.getKey());
    }
}
