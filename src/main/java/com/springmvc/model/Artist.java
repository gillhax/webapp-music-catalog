package com.springmvc.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="ARTISTS")
public class Artist {

    @Id
    @Column(name ="ARTIST_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Size(min=1, max=50)
    @Column(name = "NAME", nullable = false)
    private String name;

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Artist))
            return false;
        Artist other = (Artist) obj;
        if (id != other.id)
            return false;
        if (!name.equals(other.name) )
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Artist [id=" + id + ", name=" + name + "]";
    }

}