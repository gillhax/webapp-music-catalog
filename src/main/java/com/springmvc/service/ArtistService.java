package com.springmvc.service;

import com.springmvc.model.Artist;

import java.util.List;

public interface ArtistService {

    Artist findById(int id);

    void saveArtist(Artist artist);

    void updateArtist(Artist artist);

    void deleteArtistById(int id);

    List<Artist> findAllArtist();

}
