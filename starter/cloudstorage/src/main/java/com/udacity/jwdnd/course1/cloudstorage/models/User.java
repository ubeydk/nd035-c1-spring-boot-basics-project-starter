package com.udacity.jwdnd.course1.cloudstorage.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class User implements UserDetails {

    private static Boolean ENABLED = true;
    private static String ROLE = "USER";

    private Integer userid;
    private String username;
    private String salt;
    private String password;
    private String firstname;
    private String lastname;


    public User(Integer userid, String username, String salt, String password, String firstname, String lastname) {
        this.userid = userid;
        this.username = username;
        this.salt = salt;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities(){
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        grantedAuthorityList.add(new SimpleGrantedAuthority(ROLE));
        return grantedAuthorityList;
    }

    @Override
    public boolean isAccountNonExpired() {
        return ENABLED;
    }

    @Override
    public boolean isAccountNonLocked() {
        return ENABLED;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return ENABLED;
    }

    @Override
    public boolean isEnabled() {
        return ENABLED;
    }

    public Integer getUserid() {
        return userid;
    }

    public String getUsername() {
        return username;
    }

    public String getSalt() {
        return salt;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}