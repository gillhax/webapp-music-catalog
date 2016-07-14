package com.springmvc.controller;


import java.util.List;

import javax.validation.Valid;

import com.springmvc.model.Album;
import com.springmvc.model.Artist;
import com.springmvc.service.AlbumService;
import com.springmvc.service.ArtistService;
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
    ArtistService artistService;

    @Autowired
    MessageSource messageSource;


    //##Artist implements
    @RequestMapping(value = {"/", "/artist-list" }, method = RequestMethod.GET)
    public String listArtists(ModelMap model) {
        List<Artist> artists = artistService.findAllArtist();
        model.addAttribute("artists", artists);
        return "artist-list";
    }


    @RequestMapping(value = { "/artist-new" }, method = RequestMethod.GET)
    public String newArtist(ModelMap model) {

        model.addAttribute(new Artist());
        model.addAttribute("edit", false);
        return "artist-changer";
    }

    @RequestMapping(value = { "/artist-new" }, method = RequestMethod.POST)
    public String saveArtist(@Valid Artist artist, BindingResult result){

        if (result.hasErrors()) {
            return "artist-changer";
        }
        artistService.saveArtist(artist);

        return "redirect:/artist-list";
    }

    @RequestMapping(value = { "/edit-{id}-artist" }, method = RequestMethod.GET)
    public String editArtist(@PathVariable int id, ModelMap model) {
        model.addAttribute("artist", artistService.findById(id));
        model.addAttribute("edit", true);
        return "artist-changer";
    }

    @RequestMapping(value = { "/edit-{id}-artist" }, method = RequestMethod.POST)
    public String editArtist(@Valid Artist artist, BindingResult result,
                           ModelMap model) {

        if (result.hasErrors()) {
            model.addAttribute("edit",true);
            return "artist-changer";
        }
        artistService.updateArtist(artist);

        return "redirect:/artist-list";
    }

    @RequestMapping(value = { "/delete-{id}-artist" }, method = RequestMethod.GET)
    public String deleteArtist(@PathVariable int id) {
        artistService.deleteArtistById(id);
        return "redirect:/artist-list";
    }


    //##Album implements

    @RequestMapping(value = { "/album-list" }, method = RequestMethod.GET)
    public String listAlbums(ModelMap model) {
        model.addAttribute("albums", albumService.findAllAlbum());
        model.addAttribute("songService", songService);
        return "album-list";
    }

    @RequestMapping(value = { "/album-new" }, method = RequestMethod.GET)
    public String newAlbum(ModelMap model) {
        model.addAttribute(new HandleAlbumForm());
        model.addAttribute("artists", artistService.findAllArtist());
        model.addAttribute("edit", false);
        return "album-changer";
    }

    @RequestMapping(value = { "/album-new" }, method = RequestMethod.POST)
    public String saveAlbum(@Valid@ModelAttribute(value = "handleAlbumForm") HandleAlbumForm handleAlbumForm, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            model.addAttribute("artists", artistService.findAllArtist());
            model.addAttribute("edit", false);
            return "album-changer";
        }
        albumService.saveAlbum(handleAlbumForm.prepareAlbumToSave());
        return "redirect:/album-list";
    }

    @RequestMapping(value = { "/edit-{id}-album" }, method = RequestMethod.GET)
    public String editAlbum(@PathVariable int id, ModelMap model) {
        HandleAlbumForm handleAlbumForm = new HandleAlbumForm();
        handleAlbumForm.setAlbum(albumService.findById(id));
        model.addAttribute("handleAlbumForm", handleAlbumForm);
        model.addAttribute("artists", artistService.findAllArtist());
        model.addAttribute("edit", true);
        return "album-changer";
    }

    @RequestMapping(value = { "/edit-{id}-album" }, method = RequestMethod.POST)
    public String editAlbum(@Valid@ModelAttribute(value = "handleAlbumForm") HandleAlbumForm handleAlbumForm, BindingResult result,
                           ModelMap model, @PathVariable int id) {
        if (result.hasErrors()) {
            model.addAttribute("artists", artistService.findAllArtist());
            model.addAttribute("edit",true);
            return "album-changer";
        }
        Album oldAlbum = albumService.findById(id);
        albumService.updateAlbum(handleAlbumForm.prepareAlbumToUpdate(oldAlbum));
        return "redirect:/album-list";
    }

    @RequestMapping(value = { "/delete-{id}-album" }, method = RequestMethod.GET)
    public String deleteAlbum(@PathVariable int id) {
        HandleAlbumForm.deleteOldFile(HandleAlbumForm.getRealPathToFileBySource(albumService.findById(id)));
        albumService.deleteAlbumById(id);
        return "redirect:/album-list";
    }


    //##Song implements

    @RequestMapping(value = { "/song-list" }, method = RequestMethod.GET)
    public String listSongs(ModelMap model) {
        model.addAttribute("songs", songService.findAllSong());
        model.addAttribute("artistService", artistService);
        model.addAttribute("albumService", albumService);
        return "song-list";
    }

    @RequestMapping(value = { "/song-new" }, method = RequestMethod.GET)
    public String newSong(ModelMap model) {
        model.addAttribute(new HandleSongForm());
        model.addAttribute("edit", false);
        return "songchanger";
    }

    @RequestMapping(value = { "/song-new" }, method = RequestMethod.POST)
    public String saveSong(@Valid@ModelAttribute(value = "handleSongForm") HandleSongForm handleSongForm, BindingResult result) {
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

}
