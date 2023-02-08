package AllClasses;

public class Song implements Structure.Song {
    private int id;
    private String songName;
    private int artistId;
    private int genreId;
    private int albumId;
    private String songDuration;
    private String pathName;

    public Song() {
    }

    public Song(int id, String songName, int artistId, int genreId, int albumId, String songDuration, String pathName) {
        this.id = id;
        this.songName = songName;
        this.artistId = artistId;
        this.genreId = genreId;
        this.albumId = albumId;
        this.songDuration = songDuration;
        this.pathName = pathName;
    }
@Override
    public int getId() {
        return id;
    }
@Override
    public void setId(int id) {
        this.id = id;
    }
@Override
    public String getSongName() {
        return songName;
    }
@Override
    public void setSongName(String songName) {
        this.songName = songName;
    }
@Override
    public int getArtistId() {
        return artistId;
    }
@Override
    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }
@Override
    public int getGenreId() {
        return genreId;
    }
@Override
    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }
@Override
    public int getAlbumId() {
        return albumId;
    }
@Override
    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }
@Override
    public String getSongDuration() {
        return songDuration;
    }
@Override
    public void setSongDuration(String songDuration) {
        this.songDuration = songDuration;
    }
@Override
    public String getPathName() {
        return pathName;
    }
@Override
    public void setPathName(String pathName) {
        this.pathName = pathName;
    }

    @Override
    public String toString() {
        return " id = " + id + " songName = " + songName + "          artistId = " + artistId + " genreId = " + genreId + " albumId = " + albumId + " songDuration = " + songDuration;
    }
}
