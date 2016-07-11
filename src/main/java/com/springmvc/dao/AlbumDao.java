package com.springmvc.dao;

import com.springmvc.model.Album;
import com.springmvc.model.Song;

import java.util.List;


public interface AlbumDao {
    Album findById(int id);

    void saveAlbum(Album album);

    void deleteAlbumById(int id);

    List<Album> findAllAlbum();

    List<Album> findAlbumsByArtist(int artistId);


}
