package com.springmvc.dao;

import com.springmvc.model.Artist;

import java.util.List;

public interface ArtistDao {

    Artist findById(int id);

    void saveArtist(Artist artist);

    void deleteArtistById(int id);

    List<Artist> findAllArtist();

}