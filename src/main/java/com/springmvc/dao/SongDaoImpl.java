package com.springmvc.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.springmvc.model.Song;

@Repository("songDao")
public class SongDaoImpl extends AbstractDao<Integer, Song> implements SongDao {

    public Song findById(int id) {
        return getByKey(id);
    }

    public void saveSong(Song song) {
        persist(song);
    }

    public void deleteSongById(int id) {
        delete(getByKey(id));
    }

    @SuppressWarnings("unchecked")
    public List<Song> findAllSong() {
        Criteria criteria = createEntityCriteria();
        return (List<Song>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    public List<Song> findSongsByAlbum(int albumId) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("AlbumId", Integer.toString(albumId)));
        return (List<Song>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    public List<Song> findSongsByUser(int userId) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("UserId", Integer.toString(userId)));
        return (List<Song>) criteria.list();
    }
}