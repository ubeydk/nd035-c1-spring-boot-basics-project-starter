package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {

    @Select("SELECT * FROM NOTES WHERE noteid = #{noteid}")
    public Note getById(int noteid);

    @Select("SELECT * FROM NOTES WHERE userid = #{userid}")
    public List<Note> getByUserid(int userid);

    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) VALUES (#{note.notetitle}, #{note.notedescription}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "note.noteid")
    public int insertNote(Note note, int userid);

    @Delete("DELETE FROM NOTES WHERE noteid = #{noteid}")
    public int deleteById(int noteid);

    @Update("UPDATE NOTES SET notetitle = #{notetitle}, notedescription = #{notedescription} WHERE noteid = #{noteid}")
    public int updateById(Note note);

}
