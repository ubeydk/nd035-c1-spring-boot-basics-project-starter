package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public List<Note> getByUserId(int userid){
        return noteMapper.getByUserid(userid);
    }

    public Note getByid(int noteid){
        return noteMapper.getById(noteid);
    }

    public int insertNote(Note note, int userid){
        return noteMapper.insertNote(note, userid);
    }

    public int deleteNote(int noteid){
        return noteMapper.deleteById(noteid);
    }

    public int updateNote(Note note){
        return noteMapper.updateById(note);
    }
}
