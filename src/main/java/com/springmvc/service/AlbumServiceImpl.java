package com.springmvc.service;


import com.springmvc.dao.AlbumDao;
import com.springmvc.model.Album;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("albumService")
@Transactional
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumDao dao;

    public Album findById(int id) {
        return dao.findById(id);
    }

    public void saveAlbum(Album album) {
        dao.saveAlbum(album);
    }

    public void updateAlbum(Album album) {
        Album entity = dao.findById(album.getId());
        if (entity != null) {
            entity.setName(album.getName());
            entity.setArtistId(album.getArtistId());
            entity.setYear(album.getYear());
            entity.setCoverSource(album.getCoverSource());
        }
    }

    public void deleteAlbumById(int id) {
        dao.deleteAlbumById(id);
    }

    public List<Album> findAllAlbum() {
        return dao.findAllAlbum();
    }


    public List<Album> findAlbumsByArtist(int artistId) {
        return dao.findAlbumsByArtist(artistId);
    }

}