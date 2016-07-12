package com.springmvc.dao;

import com.springmvc.model.Artist;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("artistDao")
public class ArtistDaoImpl extends AbstractDao<Integer, Artist> implements ArtistDao {

    public Artist findById(int id) {
        return getByKey(id);
    }

    public void saveArtist(Artist artist) {
        persist(artist);
    }

    public void deleteArtistById(int id) {
        delete(getByKey(id));
    }

    @SuppressWarnings("unchecked")
    public List<Artist> findAllArtist() {
        Criteria criteria = createEntityCriteria();
        return (List<Artist>) criteria.list();
    }

}
