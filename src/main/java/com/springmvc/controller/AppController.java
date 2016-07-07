package com.springmvc.controller;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.springmvc.model.Song;
import com.springmvc.service.SongService;

@Controller
@RequestMapping("/")
public class AppController {

    @Autowired
    SongService service;

    @Autowired
    MessageSource messageSource;

    @RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
    public String listSongs(ModelMap model) {

        List<Song> songs = service.findAllSong();
        model.addAttribute("songs", songs);
        return "allsongs";
    }

    @RequestMapping(value = { "/new" }, method = RequestMethod.GET)
    public String newSong(ModelMap model) {
        model.addAttribute(new HandleSongForm());
        model.addAttribute("edit", false);
        return "songchanger";
    }

    @RequestMapping(value = { "/new" }, method = RequestMethod.POST)
    public String saveSong(@ModelAttribute(value = "handleSongForm") HandleSongForm handleSongForm, BindingResult result) throws IOException {

        if (result.hasErrors()) {
            return "songchanger";
        }
        try {
            String pathDir = System.getProperty("user.home") + "\\music_catalog-webapp\\music\\"
                    + handleSongForm.getArtistId() + "\\" + handleSongForm.getAlbumId()+ "\\";

            if(Files.notExists(Paths.get(pathDir))) {
                boolean res = new File(pathDir).mkdirs();
                System.out.println(res);
            }
            String destPath = pathDir + handleSongForm.getName() + ".mp3";

            InputStream initialStream = handleSongForm.getFile().getInputStream();
            byte[] buffer = new byte[initialStream.available()];
            initialStream.read(buffer);
            File destFile = new File(destPath);
            destFile.createNewFile();
            OutputStream outStream = new FileOutputStream(destFile);
            outStream.write(buffer);
            initialStream.close();
            outStream.close();

            Song song = new Song();
            song.setName(handleSongForm.getName());
            song.setAlbumId(handleSongForm.getAlbumId());
            song.setArtistId(handleSongForm.getArtistId());
            song.setUserId(handleSongForm.getUserId());
            song.setSource(destPath);
            service.saveSong(song);
        }
        catch (IOException e) {
            e.fillInStackTrace();
        }

        return "redirect:/list";
    }

    @RequestMapping(value = { "/edit-{id}-song" }, method = RequestMethod.GET)
    public String editEmployee(@PathVariable int id, ModelMap model) {
        Song song = service.findById(id);
        model.addAttribute("song", song);
        model.addAttribute("edit", true);
        return "songchanger";
    }

    @RequestMapping(value = { "/edit-{id}-song" }, method = RequestMethod.POST)
    public String updateEmployee(@Valid Song song, BindingResult result,
                                 ModelMap model, @PathVariable int id) {

        if (result.hasErrors()) {
            return "songchanger";
        }
        service.updateSong(song);

        return "redirect:/list";
    }

    @RequestMapping(value = { "/delete-{id}-song" }, method = RequestMethod.GET)
    public String deleteEmployee(@PathVariable int id) {
        service.deleteSongById(id);
        return "redirect:/list";
    }

}
