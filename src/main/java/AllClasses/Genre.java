package AllClasses;

public class Genre implements Structure.Genre {
    int genreId;
    String genreName;

    public Genre() {
    }

    public Genre(int genreId, String genreName) {
        this.genreId = genreId;
        this.genreName = genreName;
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
    public String getGenreName() {
        return genreName;
    }

    @Override
    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    @Override
    public String toString() {
        return "AllClasses.Genre{" +
                "genreId=" + genreId +
                ", genreName='" + genreName + '\'' +
                '}';
    }
}
