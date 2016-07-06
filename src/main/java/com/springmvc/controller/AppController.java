package com.springmvc.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
        Song song = new Song();
        model.addAttribute("song", song);
        model.addAttribute("edit", false);
        return "songchanger";
    }

    @RequestMapping(value = { "/new" }, method = RequestMethod.POST)
    public String saveSong(@Valid Song song, BindingResult result,
                               ModelMap model) {

        if (result.hasErrors()) {
            return "songchanger";
        }

        service.saveSong(song);

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

}
