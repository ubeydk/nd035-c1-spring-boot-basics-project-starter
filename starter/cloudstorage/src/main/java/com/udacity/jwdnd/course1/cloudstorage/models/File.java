package com.udacity.jwdnd.course1.cloudstorage.models;

public class File{
    private int fileid;
    private String filename;
    private String contenttype;
    private String filesize;
    private int userid;
    private byte[] filedata;

    public File(){}

    public File(int fileid, String filename, String contenttype, String filesize, int userid, byte[] filedata) {
        this.fileid = fileid;
        this.filename = filename;
        this.contenttype = contenttype;
        this.filesize = filesize;
        this.userid = userid;
        this.filedata = filedata;
    }

    public void setFileid(int fileid) {
        this.fileid = fileid;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setContenttype(String contenttype) {
        this.contenttype = contenttype;
    }

    public void setFilesize(String filesize) {
        this.filesize = filesize;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public void setFiledata(byte[] filedata) {
        this.filedata = filedata;
    }

    public int getFileid() {
        return fileid;
    }

    public String getFilename() {
        return filename;
    }

    public String getContenttype() {
        return contenttype;
    }

    public String getFilesize() {
        return filesize;
    }

    public int getUserid() {
        return userid;
    }

    public byte[] getFiledata() {
        return filedata;
    }
}