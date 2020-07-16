package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CredentialService {
    private final CredentialMapper credentialMapper;
    private final EncryptionService encryptionService;

    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
    }

    public Credential getById(int credentialid){
        return getDecryptedPassword(credentialMapper.getById(credentialid));
    }

    public List<Credential> getByUserid(int userid){
        return credentialMapper.getByUserid(userid).stream().map(s -> getDecryptedPassword(s)).collect(Collectors.toList());
    }

    public int insertCredential(Credential credential, int userid){
        encryptCredential(credential);
        return credentialMapper.insertCredential(credential, userid);
    }

    public int deleteCredential(int credentialid){
        return credentialMapper.deleteById(credentialid);
    }

    public int updateCredential(Credential credential){
        encryptCredential(credential);
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

    private Credential getDecryptedPassword(Credential credential){
        credential.setDecryptedPassword(encryptionService.decryptValue(credential.getPassword(), credential.getKey()));
        return credential;
    }
}
