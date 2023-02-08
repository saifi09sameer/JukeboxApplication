package Structure;

public interface Song {
    int getId();

    void setId(int id);

    String getSongName();

    void setSongName(String songName);

    int getArtistId();

    void setArtistId(int artistId);

    int getGenreId();

    void setGenreId(int genreId);

    int getAlbumId();

    void setAlbumId(int albumId);

    String getSongDuration();

    void setSongDuration(String songDuration);

    String getPathName();

    void setPathName(String pathName);
}
