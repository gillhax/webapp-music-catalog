package com.springmvc.service;

import com.springmvc.model.Album;

import java.util.List;

public interface AlbumService {

    Album findById(int id);

    void saveAlbum(Album Album);

    void updateAlbum(Album Album);

    void deleteAlbumById(int id);

    List<Album> findAllAlbum();

    List<Album> findAlbumsByArtist(int artistId);

}
