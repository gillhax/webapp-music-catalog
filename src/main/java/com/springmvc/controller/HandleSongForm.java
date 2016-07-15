package com.springmvc.controller;

import com.springmvc.model.Song;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HandleSongForm extends Song {
    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public void setSong(Song song) {
        this.setId(song.getId());
        this.setName(song.getName());
        this.setArtistId(song.getArtistId());
        this.setAlbumId(song.getAlbumId());
        this.setSource(song.getSource());
    }

    public Song getSong() {
        Song song = new Song();
        song.setId(this.getId());
        song.setName(this.getName());
        song.setAlbumId(this.getAlbumId());
        song.setArtistId(this.getArtistId());
        song.setSource(this.getSource());
        return song;
    }

    public Song prepareSongToSave() {
        try {
            String pathDir = System.getProperty("user.home") + "\\music_catalog-webapp\\music\\"
                    + this.getArtistId() + "\\" + this.getAlbumId()+ "\\";
            String destPath = pathDir + this.getName() + ".mp3";
            String source = "/music/"+ this.getArtistId() + "/" + this.getAlbumId()+ "/"
                    + this.getName() + ".mp3";

            if(Files.notExists(Paths.get(pathDir))) {
                new File(pathDir).mkdirs();
            }

            file.transferTo(new File(destPath));
            this.setSource(source);
            return getSong();
        }
        catch (IOException e) {
            e.fillInStackTrace();
            return null;
        }
    }


    public Song prepareSongToUpdate(Song oldSong) {
        try {
            String realPathToOldFile = HandleSongForm.getRealPathToFileBySource(oldSong);
            String pathDir = System.getProperty("user.home") + "\\music_catalog-webapp\\music\\"
                    + this.getArtistId() + "\\" + this.getAlbumId()+ "\\";
            String destPath = pathDir + this.getName() + ".mp3";
            String source = "/music/"+ this.getArtistId() + "/" + this.getAlbumId()+ "/"
                    + this.getName() + ".mp3";

            if(Files.notExists(Paths.get(pathDir))) {
                new File(pathDir).mkdirs();
            }
            //if (import new audio file == false)
            if(this.getFile().getContentType().equalsIgnoreCase("application/octet-stream")) {
                File sourceFile = new File(realPathToOldFile);
                File destFile = new File(destPath);
                Files.copy(sourceFile.toPath(), destFile.toPath());
                if(!destPath.equalsIgnoreCase(realPathToOldFile))
                    deleteOldFile(realPathToOldFile);
            }
            else {
                file.transferTo(new File(destPath));
                if(!destPath.equalsIgnoreCase(realPathToOldFile))
                    deleteOldFile(realPathToOldFile);
            }
            this.setSource(source);
            return getSong();
        }
        catch (IOException e) {
            e.fillInStackTrace();
            return null;
        }
    }

    public static void deleteOldFile(String sourcePath) {

        File file = new File(sourcePath);
        String parentDir;

        do {
            parentDir = file.getParent();
            file.delete();
            file = new File(parentDir);
        } while (file.list().length == 0);

    }

    public static String getRealPathToFileBySource(Song song) {
        return  System.getProperty("user.home") + "\\music_catalog-webapp\\music\\"
                + song.getArtistId() + "\\" + song.getAlbumId()  + "\\" + song.getName() + ".mp3";
    }
}
