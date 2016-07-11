package com.springmvc.dao;

import com.springmvc.model.Album;
import com.springmvc.model.Song;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("albumDao")
public class AlbumDaoImpl extends AbstractDao<Integer, Album> implements AlbumDao {

    public Album findById(int id) {
        return getByKey(id);
    }

    public void saveAlbum(Album album) {
        persist(album);
    }

    public void deleteAlbumById(int id) {
        delete(getByKey(id));
    }

    @SuppressWarnings("unchecked")
    public List<Album> findAllAlbum() {
        Criteria criteria = createEntityCriteria();
        return (List<Album>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    public List<Album> findAlbumsByArtist(int artistId) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("artistId", Integer.toString(artistId)));
        return (List<Album>) criteria.list();
    }

}
