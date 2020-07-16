package com.udacity.jwdnd.course1.cloudstorage.models;

public class Note{
    private int noteid;
    private String notetitle;
    private String notedescription;
    private int userid;

    public int getNoteid() {
        return noteid;
    }

    public void setNoteid(int noteid) {
        this.noteid = noteid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getNotetitle() {
        return notetitle;
    }

    public String getNotedescription() {
        return notedescription;
    }

    public int getUserid() {
        return userid;
    }

    public void setNotetitle(String notetitle) {
        this.notetitle = notetitle;
    }

    public void setNotedescription(String notedescription) {
        this.notedescription = notedescription;
    }
}