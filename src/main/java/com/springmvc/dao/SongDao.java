package com.springmvc.dao;

import java.util.List;
import com.springmvc.model.Song;

public interface SongDao {

    Song findById(int id);

    void saveSong(Song song);

    void deleteSongById(int id);

    List<Song> findAllSong();

    List<Song> findSongsByAlbum(int albumId);

    List<Song> findSongsByArtist(int artistId);
}