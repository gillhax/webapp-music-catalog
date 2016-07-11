package com.springmvc.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.springmvc.model.Album;
import com.springmvc.service.AlbumService;
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
    SongService songService;

    @Autowired
    AlbumService albumService;

    @Autowired
    MessageSource messageSource;

    @RequestMapping(value = { "/", "/song-list" }, method = RequestMethod.GET)
    public String listSongs(ModelMap model) {

        List<Song> songs = songService.findAllSong();
        model.addAttribute("songs", songs);
        return "allsongs";
    }

    @RequestMapping(value = { "/song-new" }, method = RequestMethod.GET)
    public String newSong(ModelMap model) {
        model.addAttribute(new HandleSongForm());
        model.addAttribute("edit", false);
        return "songchanger";
    }

    @RequestMapping(value = { "/song-new" }, method = RequestMethod.POST)
    public String saveSong(@Valid@ModelAttribute(value = "handleSongForm") HandleSongForm handleSongForm, BindingResult result) throws IOException {

        if (result.hasErrors()) {
            return "songchanger";
        }

        songService.saveSong(handleSongForm.prepareSongToSave());

        return "redirect:/song-list";
    }

    @RequestMapping(value = { "/edit-{id}-song" }, method = RequestMethod.GET)
    public String editSong(@PathVariable int id, ModelMap model) {
        HandleSongForm handleSongForm = new HandleSongForm();
        handleSongForm.setSong(songService.findById(id));
        model.addAttribute("handleSongForm", handleSongForm);
        model.addAttribute("edit", true);
        return "songchanger";
    }

    @RequestMapping(value = { "/edit-{id}-song" }, method = RequestMethod.POST)
    public String editSong(@Valid@ModelAttribute(value = "handleSongForm") HandleSongForm handleSongForm, BindingResult result,
                                 ModelMap model, @PathVariable int id) {

        if (result.hasErrors()) {
            model.addAttribute("edit",true);
            return "songchanger";
        }

        Song oldSong = songService.findById(id);
        Song song = handleSongForm.prepareSongToUpdate(oldSong);
        songService.updateSong(song);

        return "redirect:/song-list";
    }

    @RequestMapping(value = { "/delete-{id}-song" }, method = RequestMethod.GET)
    public String deleteSong(@PathVariable int id) {

        HandleSongForm.deleteOldFile(HandleSongForm.getRealPathToFileBySource(songService.findById(id)));
        songService.deleteSongById(id);

        return "redirect:/song-list";
    }


    //##Album implements

    @RequestMapping(value = { "/album-list" }, method = RequestMethod.GET)
    public String listAlbums(ModelMap model) {
        List<Album> albums = albumService.findAllAlbum();

        ArrayList<Integer> albumIds = new ArrayList<Integer>();
        for(Album album : albums) {
            albumIds.add(album.getId());
        }
        List<String> SongsSourceByAlbumId = new ArrayList<String>();
        for(int id : albumIds) {
            List<Song> songs = songService.findSongsByAlbum(id);
            StringBuilder songsSource = new StringBuilder();
            for(Song song : songs) {
                songsSource.append("<script src=");
                songsSource.append(song.getSource());
                songsSource.append(" ></script><br>");
            }
            SongsSourceByAlbumId.add(songsSource.toString());
        }

        model.addAttribute("albums", albums);
        model.addAttribute("SongsSourceByAlbumId", SongsSourceByAlbumId);
        return "album-list";
    }

}
