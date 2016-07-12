package com.springmvc.model;

    import javax.persistence.Column;
    import javax.persistence.Entity;
    import javax.persistence.GeneratedValue;
    import javax.persistence.GenerationType;
    import javax.persistence.Id;
    import javax.persistence.Table;
    import javax.validation.constraints.NotNull;
    import javax.validation.constraints.Size;

    @Entity
    @Table(name="Songs")
    public class Song {
        @Id
        @Column(name ="ID")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @Size(min=1, max=50)
        @Column(name = "NAME", nullable = false)
        private String name;

        @NotNull
        @Column(name = "ALBUMID", nullable = false)
        private int albumId;

        @NotNull
        @Column(name = "ARTISTID", nullable = false)
        private int artistId;

        @NotNull
        @Column(name = "USERID", nullable = false)
        private int userId;

        @NotNull
        @Column(name = "SOURCE", nullable = false)
        private String source;

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

        public int getAlbumId() {
            return albumId;
        }

        public void setAlbumId(int albumId) {
            this.albumId = albumId;
        }

        public int getArtistId() {
            return artistId;
        }

        public void setArtistId(int artistId) {
            this.artistId = artistId;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (!(obj instanceof Song))
                return false;
            Song other = (Song) obj;
            if (id != other.id)
                return false;
            if (!name.equals(other.name) )
                return false;
            if (albumId != other.albumId)
                return false;
            if (artistId != other.artistId)
                return false;
            if (userId != other.userId)
                return false;
            if (source.equals(other.source))
                return false;
            return true;
        }

        @Override
        public String toString() {
            return "Song [id=" + id + ", name=" + name + ", albumId="
                    + albumId + ", artistId=" + artistId + ", userId=" + userId + ", source=" + source + "]";
        }

    }