package com.springmvc.service;

import com.springmvc.dao.ArtistDao;
import com.springmvc.model.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("artistService")
@Transactional
public class ArtistServiceImpl implements ArtistService {

    @Autowired
    private ArtistDao dao;

    public Artist findById(int id) {
        return dao.findById(id);
    }

    public void saveArtist(Artist artist) {
        dao.saveArtist(artist);
    }

    public void updateArtist(Artist artist) {
        Artist entity = dao.findById(artist.getId());
        if (entity != null) {
            entity.setName(artist.getName());
        }
    }

    public void deleteArtistById(int id) {
        dao.deleteArtistById(id);
    }

    public List<Artist> findAllArtist() {
        return dao.findAllArtist();
    }

}
