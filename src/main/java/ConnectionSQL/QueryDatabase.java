package ConnectionSQL;

import AllClasses.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QueryDatabase extends Connection_DB {
    static Scanner scanner = new Scanner(System.in);
    static Connection connection;
    public static int uId = 0;

    //This method is responsible to insert new user into database
    public static void insertUserIntoDataBase(Users users) {
        try {
            connection = getConnection();
            Statement st = connection.createStatement();
            String str = "insert into users values('" + users.getUserId() + "','" + users.getName() + "','" + users.getUserName() + "','" + users.getPassword() + "','" + users.getEmailID() + "'," + users.getMobileNumber() + ");";
            int status = st.executeUpdate(str);
            if (status > 0) {
                System.out.println(status + " Registration is completed. Please use username and password for login purpose.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //This method is responsible to checkUser Details if user have a account or not
    public static String checkUser(String userName, String password) {
        try {
            String loginDet = "select * from users";
            connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(loginDet);
            while (resultSet.next()) {
                String uname = resultSet.getString("User_Name");
                String pass = resultSet.getString("Password");
                String name = resultSet.getString("Name");
                int userId = resultSet.getInt("userId");
                if (userName.equalsIgnoreCase(uname) && password.equalsIgnoreCase(pass)) {
                    System.out.println("Your UserID is      : " + userId);
                    QueryDatabase.uId = userId;
                    return name;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    //this method is responsible to Fetch Artest Table from database and return the List of Artist
    public static List<Artist> FetchArtist() {
        List<Artist> artistList = new ArrayList<>();
        connection = getConnection();
        Artist artist = null;
        try {
            String line = "select * from artist";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(line);
            while (resultSet.next()) {
                int artist_id = resultSet.getInt("artist_id");
                String artist_name = resultSet.getString("artist_name");
                artist = new Artist(artist_id, artist_name);
                artistList.add(artist);
                artist = null;
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return artistList;
    }
    public List<PlayList> fetchPlayList(int userid) {
        List<PlayList> playList = new ArrayList<>();
        connection = getConnection();
        try {
            String playlistQuery = "SELECT * FROM playlist WHERE userId ="+userid;
            PreparedStatement stmt = connection.prepareStatement(playlistQuery);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                PlayList allPlayList = new PlayList();
                allPlayList.setPlaylistId(rs.getInt("playlist_Id"));
                allPlayList.setPlaylistName(rs.getString("playlist_name"));
                allPlayList.setUserId(rs.getInt("userId"));

                if (allPlayList.getUserId() ==userid) {
                    playList.add(allPlayList);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return playList;
    }
    public static List<Album> FetchAlbum() {
        List<Album> albumList = new ArrayList<>();
        connection = getConnection();
        Album album = null;
        try {
            String line = "select * from album";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(line);
            while (resultSet.next()) {
                int album_id = resultSet.getInt("album_id");
                String album_name = resultSet.getString("album_name");
                album = new Album(album_id, album_name);
                albumList.add(album);
                album = null;
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return albumList;
    }

    public static List<Genre> FetchGenre() {
        List<Genre> genreList = new ArrayList<>();
        connection = getConnection();
        Genre genre = null;
        try {
            String line = "select * from genre";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(line);
            while (resultSet.next()) {
                int genre_id = resultSet.getInt("genre_id");
                String genre_name = resultSet.getString("genre_name");
                genre = new Genre(genre_id, genre_name);
                genreList.add(genre);
                genre = null;
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return genreList;
    }


    // this method is
    public static void insertPlayListIntoDataBase(PlayList playList) {
        try {
            connection = getConnection();
            Statement st = connection.createStatement();
            int result = st.executeUpdate("insert into playlist values(" + playList.getPlaylistId() + ", '" + playList.getPlaylistName() + "', " + playList.getUserId() + ");");
            if (result > 0) {
                System.out.println(result + "- Record Inserted in playlist table ");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Song> showSongList(String sql) {
        List<Song> songList = new ArrayList<>();
        connection = getConnection();
        Song song = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String song_name = resultSet.getString("song_name");
                int artist_id = resultSet.getInt("artist_id");
                int genre_id = resultSet.getInt("genre_id");
                int album_id = resultSet.getInt("album_id");
                String song_duration = resultSet.getString("song_duration");
                String pathName = resultSet.getString("path_name");
                song = new Song(id, song_name, artist_id, genre_id, album_id, song_duration, pathName);
                songList.add(song);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return songList;
    }

    //this method is used to print the playlist
    public List<PlayList> showPlayList() {
        List<PlayList> playList = new ArrayList<>();
        connection = getConnection();
        try {
            String playlistQuery = "SELECT * FROM playlist";
            PreparedStatement stmt = connection.prepareStatement(playlistQuery);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                PlayList allPlayList = new PlayList();
                allPlayList.setPlaylistId(rs.getInt("playlist_Id"));
                allPlayList.setPlaylistName(rs.getString("playlist_name"));
                allPlayList.setUserId(rs.getInt("userId"));

                if (allPlayList.getUserId() == uId) {
                    playList.add(allPlayList);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return playList;
    }

    // this method is to search playlist from database
    public List<PlayList> searchPlaylist(String SQL) {
        List<PlayList> playList = new ArrayList<>();
        PlayList playList1 = null;
        connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                int playlist_Id = resultSet.getInt("playlist_Id");
                String playlist_name = resultSet.getString("playlist_name");
                int userId = resultSet.getInt("userId");
                playList1 = new PlayList(playlist_Id, playlist_name, userId);
                playList.add(playList1);
                playList1 = null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return playList;
    }

    public List<PlaylistSong> showSongsByPlayList(String SQL) {
        List<PlaylistSong> playListSongs = new ArrayList<>();
        try {
            connection = getConnection();
            PreparedStatement stmt = connection.prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                PlaylistSong allPlayList = new PlaylistSong();
                allPlayList.setSongId(rs.getInt("id"));
                allPlayList.setSongName(rs.getString("song_name"));
                allPlayList.setPathOfSong(rs.getString("path_name"));

                playListSongs.add(allPlayList);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
        return playListSongs;
    }
    public void InsertSongInPlaylist(int playlistId) {

        boolean flag;
        int input;
        do {
            System.out.println("Please Enter Your Song Id as Of Which Song You Need To Add In The Playlist");
            int songId=scanner.nextInt();
            flag=true;
            try {
                Statement st = getConnection().createStatement();
                st.executeUpdate("INSERT INTO playlist_song(playlist_Id, id) " +
                        "VALUES("+playlistId+", "+songId+");");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Enter 1 if you want to insert other song else 0");
            input = scanner.nextInt();
        } while (input == 1);
    }
    public List<PlaylistSong> fetchSongsByPlayList(int playlistId) {
        List<PlaylistSong> playListSongs = new ArrayList<>();
        try {
            String songPlaylistQuery = "select s.id,song_name,s.path_name from song s, playlist p, playlist_song ps where ps.playlist_Id=" + playlistId + " and p.playlist_Id = ps.playlist_Id and s.id = ps.id;\n";
            PreparedStatement stmt = connection.prepareStatement(songPlaylistQuery);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                PlaylistSong allPlayList = new PlaylistSong();
                allPlayList.setSongId(rs.getInt("id"));
                allPlayList.setSongName(rs.getString("song_name"));
                allPlayList.setPathOfSong(rs.getString("path_name"));
                playListSongs.add(allPlayList);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return playListSongs;
    }
}
