package AllClasses;

public class Album implements Structure.Album {
    int albumId;
    String albumName;

    public Album() {
    }

    public Album(int albumId, String albumName) {
        this.albumId = albumId;
        this.albumName = albumName;
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
    public String getAlbumName() {
        return albumName;
    }

    @Override
    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    @Override
    public String toString() {
        return "AllClasses.Album{" +
                "albumId=" + albumId +
                ", albumName='" + albumName + '\'' +
                '}';
    }
}
