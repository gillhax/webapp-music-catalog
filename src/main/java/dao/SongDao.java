package dao;

import java.util.List;
import model.Song;

public interface SongDao {

    Song findById(int id);

    void saveSong(Song employee);

    void deleteSongById(int id);

    List<Song> findAllSong();

    List<Song> findSongsByAlbum(int albumId);

    List<Song> findSongsByUser(int userId);
}