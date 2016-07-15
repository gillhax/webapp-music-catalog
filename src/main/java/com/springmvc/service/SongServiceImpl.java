package com.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.dao.SongDao;
import com.springmvc.model.Song;

@Service("songService")
@Transactional
public class SongServiceImpl implements SongService {

    @Autowired
    private SongDao dao;

    public Song findById(int id) {
        return dao.findById(id);
    }

    public void saveSong(Song song) {
        dao.saveSong(song);
    }

    public void updateSong(Song song){
        Song entity = dao.findById(song.getId());
        if(entity!=null){
            entity.setName(song.getName());
            entity.setAlbumId(song.getAlbumId());
            entity.setArtistId(song.getArtistId());
            entity.setSource(song.getSource());
        }
    }

    public void deleteSongById(int id){
        dao.deleteSongById(id);
    }

    public List<Song> findAllSong(){
        return dao.findAllSong();
    }

    public List<Song> findSongsByAlbum(int albumId){
        return dao.findSongsByAlbum(albumId);
    }

    public List<Song> findSongsByArtist(int artistId){
        return dao.findSongsByArtist(artistId);
    }

}