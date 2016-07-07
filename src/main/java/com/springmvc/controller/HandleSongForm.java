package com.springmvc.controller;

import com.springmvc.model.Song;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class HandleSongForm extends Song {
    private CommonsMultipartFile file;

    public CommonsMultipartFile getFile() {
        return file;
    }
    public void setFile(CommonsMultipartFile file) {
        this.file = file;
    }

}
