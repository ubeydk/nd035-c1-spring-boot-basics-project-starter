package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {
    @Select("SELECT * FROM FILES WHERE fileid = #{fileid}")
    public File getById(int fileid);

    @Select("SELECT * FROM FILES WHERE userid = #{userid}")
    public List<File> getByUserid(int userid);

    @Insert("INSERT INTO FILES (filename, contenttype, filesize, filedata, userid) VALUES (#{file.filename}, #{file.contenttype}, #{file.filesize}, #{file.filedata}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "file.fileid")
    public int insertFile(File file, int userid);

    @Delete("DELETE FROM FILES WHERE fileid = #{fileid}")
    public int deleteById(int fileid);
}
