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
    public String saveSong(@Valid@ModelAttribute(value = "handleSongForm") HandleSongForm handleSongForm, BindingResult result) throws IOException {

        if (result.hasErrors()) {
            return "songchanger";
        }

        service.saveSong(handleSongForm.PrepareSongToSave());

        return "redirect:/list";
    }

    @RequestMapping(value = { "/edit-{id}-song" }, method = RequestMethod.GET)
    public String editSong(@PathVariable int id, ModelMap model) {
        HandleSongForm handleSongForm = new HandleSongForm();
        handleSongForm.setSong(service.findById(id));
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

        Song oldSong = service.findById(id);
        Song song = handleSongForm.PrepareSongToUpdate(oldSong);
        service.updateSong(song);

        return "redirect:/list";
    }

    @RequestMapping(value = { "/delete-{id}-song" }, method = RequestMethod.GET)
    public String deleteSong(@PathVariable int id) {

        HandleSongForm.DeleteOldFile(HandleSongForm.GetRealPathToFileBySource(service.findById(id)));
        service.deleteSongById(id);

        return "redirect:/list";
    }

}
