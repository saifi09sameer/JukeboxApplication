package ConnectionSQL;
import AllClasses.*;
import java.util.List;
public class Printing {
    public void printingSongsInPlaylist(List<PlaylistSong> playListSongs) {
        System.out.println("+--------+-----------+-----------+");
        System.out.println("| Song Id| Song Name      |");
        System.out.println("+--------+-----------+-----------+");
        for (PlaylistSong playListSong : playListSongs) {
            System.out.printf("| %-8d| %-15s|\n", playListSong.getSongId(), playListSong.getSongName());
        }
        System.out.println("+--------+-----------+----------+");
    }
    public static void printSongTable(List<Song> songList) {
        System.out.println("+----*------------------*---------*------------*----------*----------------*----------*-------------------------------------------------+");
        System.out.println("| ID |    Song Name     |\t Artist ID | Genre ID | Album ID |  Song Duration            |                    Path                         |");
        System.out.println("+----+------------------+----------+----------+----------+----------------+----------+--------------------------------------------------+");

        for (Song song : songList) {
            System.out.format("| %2d | %-26s | %-10d | %-8d | %-8d | %-14s | %-11s |\n",
                    song.getId(), song.getSongName(), song.getArtistId(), song.getGenreId(),
                    song.getAlbumId(), song.getSongDuration(), song.getPathName());
        }
        System.out.println("+----+------------------+----------+----------+----------+----------------+----------+--------------------------------------------------+");
    }
    public static void printPlaylistTable(List<PlayList> playList) {
        System.out.println("+---------------+---------------------+----------+");
        System.out.println("| Playlist ID   | Playlist Name       | User ID  |");
        System.out.println("+---------------+---------------------+----------+");
        for (PlayList playlist : playList) {
            System.out.format("| %-14d| %-20s| %-9d|\n", playlist.getPlaylistId(), playlist.getPlaylistName(), playlist.getUserId());
        }
        System.out.println("+---------------+---------------------+----------+");
    }
    public void printArtistTable(List<Artist> artistList) {
        System.out.println("+---------------+---------------------+");
        System.out.println("| Artist ID     | Artist Name         |");
        System.out.println("+---------------+---------------------+");
        for (Artist artist : artistList) {
            System.out.format("| %-14d | %-20s |\n", artist.getArtistId(), artist.getArtistName());
        }
        System.out.println("+---------------+---------------------+");
    }
    public void printAlbumTable(List<Album> albumList) {
        System.out.println("+---------------+---------------------+");
        System.out.println("| Album ID      |  Album Name         |");
        System.out.println("+---------------+---------------------+");

        for (Album album : albumList) {
            System.out.format("| %-14d | %-20s |\n", album.getAlbumId(), album.getAlbumName());
        }

        System.out.println("+---------------+---------------------+");
    }
    public void printGenreTable(List<Genre> genres) {
        System.out.println("+---------------+---------------------+");
        System.out.println("| Genre ID      |  Genre Name         |");
        System.out.println("+---------------+---------------------+");

        for (Genre genre : genres) {
            System.out.format("| %-14d | %-20s |\n", genre.getGenreId(), genre.getGenreName());
        }
        System.out.println("+---------------+---------------------+");
    }
    public void printTable(List<PlaylistSong> playListSongs) {
        System.out.println(" Song Id\t Song Name");
        for (PlaylistSong playListSong : playListSongs) {
            System.out.printf("%d\t\t%s\n", playListSong.getSongId(), playListSong.getSongName());
        }
    }
    public void printingPlaylistTable(List<PlayList> playList) {
        System.out.println("+---------------+---------------------+----------+");
        System.out.println("| Playlist ID   | Playlist Name       | User ID  |");
        System.out.println("+---------------+---------------------+----------+");

        for (PlayList playlist : playList) {
            System.out.format("| %-14d| %-20s| %-9d|\n", playlist.getPlaylistId(), playlist.getPlaylistName(), playlist.getUserId());
        }

        System.out.println("+---------------+---------------------+----------+");
    }
}