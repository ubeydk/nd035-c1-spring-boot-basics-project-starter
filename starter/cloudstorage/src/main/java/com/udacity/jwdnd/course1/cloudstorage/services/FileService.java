package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.File;
import com.udacity.jwdnd.course1.cloudstorage.models.HomeFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {

    private final FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    private List<File> getByUser(int userid){
        return fileMapper.getByUserid(userid);
    }

    public List<HomeFile> getHomeFilesByUser(int userid){
        List<File> files = fileMapper.getByUserid(userid);
        List<HomeFile> homeFiles = new ArrayList<>();
        for(File file : files){
            homeFiles.add(new HomeFile(file));
        }
        return homeFiles;
    }

    private boolean fileExist(String filename, int userid){
        return !fileMapper.getByFilename(filename, userid).isEmpty();
    }

    public int addFile(MultipartFile multipartFile, int userid) throws IOException {
        File file = new File();
        try{
            if(fileExist(multipartFile.getOriginalFilename(), userid))
                return -1;
            file.setFilename(multipartFile.getOriginalFilename());
            file.setContenttype(multipartFile.getContentType());
            file.setFilesize(String.valueOf(multipartFile.getSize()));
            file.setFiledata(multipartFile.getBytes());
        } catch (IOException e){
            throw e;
        }
        return fileMapper.insertFile(file, userid);
    }

    public int deleteFile(int fileid){
        return fileMapper.deleteById(fileid);
    }

}
