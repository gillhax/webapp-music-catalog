package com.springmvc.controller;


import com.springmvc.model.Album;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class HandleAlbumForm extends Album {
    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public void setAlbum(Album album) {
        this.setId(album.getId());
        this.setName(album.getName());
        this.setYear(album.getYear());
        this.setArtistId(album.getArtistId());
        this.setCoverSource(album.getCoverSource());
    }

    public Album getAlbum() {
        Album album = new Album();
        album.setId(this.getId());
        album.setName(this.getName());
        album.setYear(this.getYear());
        album.setArtistId(this.getArtistId());
        album.setCoverSource(this.getCoverSource());
        return album;
    }

    public Album prepareAlbumToSave() {
        try {
            String pathDir = System.getProperty("user.home") + "\\music_catalog-webapp\\cover\\";

            String[] findType =  file.getContentType().split("/");
            String fileType = "." + findType[findType.length-1];
            String destPath = pathDir + this.getArtistId() + "-" + this.getName() + fileType;
            String source = "/cover/"+ this.getArtistId() + "-" + this.getName() + fileType ;

            file.transferTo(new File(destPath));
            this.setCoverSource(source);
            return getAlbum();
        }
        catch (IOException e) {
            e.fillInStackTrace();
            return null;
        }
    }


    public Album prepareAlbumToUpdate(Album oldAlbum) {
        try {
            String realPathToOldFile = HandleAlbumForm.getRealPathToFileBySource(oldAlbum);
            String pathDir = System.getProperty("user.home") + "\\music_catalog-webapp\\cover\\";
            String[] findType;
            if(this.getFile().getContentType().equalsIgnoreCase("application/octet-stream"))
                findType =  realPathToOldFile.split("\\.");
            else
                findType =  file.getContentType().split("/");
            String fileType = "." + findType[findType.length-1];
            String destPath = pathDir + this.getArtistId() + "-" + this.getName() + fileType;
            String source = "/cover/"+ this.getArtistId() + "-" + this.getName() + fileType;

       //if (import new cover file == false)
            if(this.getFile().getContentType().equalsIgnoreCase("application/octet-stream")) {
                File sourceFile = new File(realPathToOldFile);
                File destFile = new File(destPath);
                Files.copy(sourceFile.toPath(), destFile.toPath());
                deleteOldFile(realPathToOldFile);
            }
            else {
                deleteOldFile(realPathToOldFile);
                file.transferTo(new File(destPath));
            }
            this.setCoverSource(source);
            return getAlbum();
        }
        catch (IOException e) {
            e.fillInStackTrace();
            return null;
        }
    }

    public static boolean deleteOldFile(String sourcePath) {
         return new File(sourcePath).delete();
    }

    public static String getRealPathToFileBySource(Album album) {
        String [] source = album.getCoverSource().split("/", -1);
        return System.getProperty("user.home") + "\\music_catalog-webapp\\cover\\" + source[2];
    }
}
