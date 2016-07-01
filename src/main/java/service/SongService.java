package service;

import java.util.List;

import model.Song;

public interface SongService {

    Song findById(int id);

    void saveSong(Song song);

    void updateSong(Song song);

    void deleteSongById(int id);

    List<Song> findAllSong();

    List<Song> findSongsByAlbum(int albumId);

    List<Song> findSongsByUser(int userId);

}