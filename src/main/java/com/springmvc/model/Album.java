package com.springmvc.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="ALBUMS")
public class Album {

    @Id
    @Column(name ="ALBUM_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Size(min=1, max=50)
    @Column(name = "NAME", nullable = false)
    private String name;

    @NotNull
    @Size(min=4, max=4)
    @Column(name = "YEAR", nullable = false)
    private int year;

    @NotNull
    @Column(name = "COVERSOURCE", nullable = false)
    private String coverSource;

    @NotNull
    @Column(name = "ARTISTID", nullable = false)
    private int artistId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }


    public String getCoverSource() {
        return coverSource;
    }

    public void setCoverSource(String source) {
        this.coverSource = coverSource;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Album))
            return false;
        Album other = (Album) obj;
        if (id != other.id)
            return false;
        if (!name.equals(other.name) )
            return false;
        if (year != other.year)
            return false;
        if (artistId != other.artistId)
            return false;
        if (coverSource.equals(other.coverSource))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Album [id=" + id + ", name=" + name + ", year="
                + year + ", artistId=" + artistId + ", coverSource=" + coverSource + "]";
    }

}