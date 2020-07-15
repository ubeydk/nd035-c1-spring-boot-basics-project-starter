package com.udacity.jwdnd.course1.cloudstorage.models;

import java.util.Base64;

public class HomeFile {
    private int fileid;
    private String filename;
    private String dataURL;

    public HomeFile(File file){
        this.fileid = file.getFileid();
        this.filename = file.getFilename();
        String base64 = Base64.getEncoder().encodeToString(file.getFiledata());
        this.dataURL = "data:" + file.getContenttype() +";base64,"+base64;
    }

    public int getFileid() {
        return fileid;
    }

    public void setFileid(int fileid) {
        this.fileid = fileid;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getDataURL() {
        return dataURL;
    }

    public void setDataURL(String dataURL) {
        this.dataURL = dataURL;
    }
}
