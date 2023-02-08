package MainStartApplication;

import All.*;
import AllClasses.*;
import ConnectionSQL.*;

import java.util.List;
import java.util.Scanner;

public class JukeBoxApplication {
    public void startApplication() {
        int choice;
        Scanner sc = new Scanner(System.in);
        LogInAndSignUp login = new LogInAndSignUp();   //object for login and registration page
        Creation creation = new Creation();
        PlaySongs fetch1 = new PlaySongs(); //object of class for fetching data from database
        QueryDatabase fetch = new QueryDatabase();
        Printing print = new Printing();
        do {
            System.out.println("+------------------------------------------+");
            System.out.println("| Welcome To JukeBox Application           |");
            System.out.println("+------------------------------------------+");
            System.out.println("| Please Enter 1 for Login                 |");
            System.out.println("| Please Enter 2 for New Registration      |");
            System.out.println("| Please Enter 3 for Guest User            |");
            System.out.println("| Please Enter 4 for Exit Application      |");
            System.out.println("+------------------------------------------+");
            System.out.println("Please Enter Your Choose                    ");
            choice = sc.nextInt();
            switch (choice) {
                case 1: {
                    System.out.println("*------------please enter details of the user for which you want to login-----------*");
                    if (login.loginUser()) { // calling method for login
                        do {
                            System.out.println("+-------------------------------------+");
                            System.out.println("| Which action you want to perform?   |");
                            System.out.println("| Please select an option:            |");
                            System.out.println("+-------------------------------------+");
                            System.out.println("| 1. Create playlist                  |");
                            System.out.println("| 2. Insert Song into  playlist       |");
                            System.out.println("| 3. Show playlist                    |");
                            System.out.println("| 4. Search Playlist                  |");
                            System.out.println("| 5. Listen all songs                 |");
                            System.out.println("| 6. Listen songs by artist           |");
                            System.out.println("| 7. Listen songs by album            |");
                            System.out.println("| 8. Listen songs by genre            |");
                            System.out.println("| 9. Exit                             |");
                            System.out.println("+------------------------------------+");
                            System.out.print("Enter choice                           :");
                            choice = sc.nextInt();
                            switch (choice) {
                                case 1: {
                                    creation.createPlaylist(); // to create new playlist for a user
                                    break;
                                }
                                case 2: {
                                    List<PlayList> playList = fetch.fetchPlayList(QueryDatabase.uId); // fetching the playlist acc to user id
                                    print.printingPlaylistTable(playList); // displaying the playlist acc to user
                                    System.out.println("Please Enter Your Playlist Id In Which You Want To Insert Song");
                                    int choise = sc.nextInt();
                                    String allSongsQuery = "SELECT * FROM song";
                                    List<Song> songList = fetch.showSongList(allSongsQuery); // fetching the song list
                                    Printing.printSongTable(songList);
                                    fetch.InsertSongInPlaylist(choise);
                                    System.out.println("Please Enter Your Playlist Id To Play Songs");
                                    int playlistId = sc.nextInt();
                                    print.printingSongsInPlaylist(fetch.fetchSongsByPlayList(playlistId)); // fetching songs playlist and displaying it.
                                    // to play songs from playlist
                                    String songDetails = "select s.id,song_name,s.song_duration,s.path_name from song s, playlist p, playlist_song ps where ps.playlist_Id = " + playlistId + " and p.playlist_Id = ps.playlist_Id and s.id = ps.id";
                                    fetch1.playSong(songDetails); //
                                    break;
                                }
                                case 3: {
                                    List<PlayList> playList = fetch.showPlayList();  // to show the whole playlist
                                    print.printPlaylistTable(playList);
                                    System.out.println("please enter id of the playlist to play songs ");
                                    int id = sc.nextInt();
                                    String songPlaylistQuery = "select s.id,song_name,song_duration,s.path_name from song s, playlist p, playlist_song ps where ps.playlist_Id=" + id + " and p.playlist_Id = ps.playlist_Id and s.id = ps.id;\n";
                                    List<PlaylistSong> list = fetch.showSongsByPlayList(songPlaylistQuery);
                                    print.printTable(list);
                                    fetch1.playSong(songPlaylistQuery);
                                    break;
                                }
                                case 4: {
                                    List<PlayList> playListShow = fetch.showPlayList();  // to show the whole playlist
                                    print.printPlaylistTable(playListShow);
                                    System.out.println("please type something to search playlist it will search if entered value lie in playlist name ");
                                    String str = sc.next();
                                    String allSongsQuery = "SELECT * FROM playlist where playlist_name like '%" + str + "%';";
                                    List<PlayList> playList = fetch.searchPlaylist(allSongsQuery);// to search playlist from database
                                    print.printPlaylistTable(playList);
                                    System.out.println("Please Enter PlayListID");
                                    int id = sc.nextInt();
                                    String SQL = "select s.id,song_name,song_duration,s.path_name from song s, playlist p, playlist_song ps where ps.playlist_Id=" + id + " and p.playlist_Id = ps.playlist_Id and s.id = ps.id;";
                                    List<PlaylistSong> list = fetch.showSongsByPlayList(SQL);
                                    print.printTable(list);
                                    fetch1.playSong(SQL);
                                    break;
                                }
                                case 5: {
                                    String allSongsQuery = "SELECT * FROM song";
                                    List<Song> songList = fetch.showSongList(allSongsQuery);
                                    print.printSongTable(songList);
                                    String loginDet = "select id,song_name,song_duration, path_name from song";
                                    fetch1.playSong(loginDet);
                                    break;
                                }
                                case 6: {
                                    List<Artist> artistList1 = QueryDatabase.FetchArtist(); //Here I'm Fetching Artest
                                    print.printArtistTable(artistList1); // Here I'm Print that table
                                    System.out.println("please enter Artist Id to play songs present in that list");
                                    int artist_id = sc.nextInt();
                                    String SQL = "select * from song where artist_id = " + artist_id; // this is my quary to get data from song where artist_id = userInput
                                    List<Song> songList = QueryDatabase.showSongList(SQL); // this will be show all data from song where artist_id = userInput
                                    Printing.printSongTable(songList); // this method responsible to print the song
                                    fetch1.playSong(SQL); // this method responsible to play one by one song
                                    break; // After that I'm breaking this case
                                }
                                case 7: {
                                    List<Album> albumList = QueryDatabase.FetchAlbum();
                                    print.printAlbumTable(albumList);
                                    System.out.println("please enter Album Id to play songs present in that list");
                                    int Album_ID = sc.nextInt();
                                    String SQL = "select * from song where album_id = " + Album_ID;
                                    List<Song> songList = QueryDatabase.showSongList(SQL);
                                    Printing.printSongTable(songList);
                                    fetch1.playSong(SQL);
                                    break;
                                }
                                case 8: {
                                    List<Genre> genreList = QueryDatabase.FetchGenre();
                                    print.printGenreTable(genreList);
                                    System.out.println("please enter Genre Id to play songs present in that list");
                                    int Genre_ID = sc.nextInt();
                                    String SQL = "select * from song where genre_id = " + Genre_ID;
                                    List<Song> songList = QueryDatabase.showSongList(SQL);
                                    Printing.printSongTable(songList);
                                    fetch1.playSong(SQL);
                                    break;
                                }
                                case 9: {
                                    System.out.println("You have been logged out successfully.");
                                    System.exit(0);
                                }
                                default:
                                    System.out.println("you have entered wrong number please select correct number ");
                            }
                            System.out.println("if you want to enjoy the service again then please enter 1 to continue or enter 0 to exit");
                            choice = sc.nextInt();
                        } while (choice == 1);
                    }
                    break;
                }
                case 2: {
                    System.out.println("*------------please enter details of the user for new registration--------------*");
                    login.registerNewUser(); // calling method for new registration
                    break;
                }
                case 3: {
                    System.out.println("\033[0;34mYou are a guest user, so you can listen only to songs. If you want to use more functionality, you need to first create your account and then log in.\033[0m");
                    String allSongsQuery = "SELECT * FROM song";
                    List<Song> songList = fetch.showSongList(allSongsQuery);
                    print.printSongTable(songList);
                    String loginDet = "select id,song_name,song_duration, path_name from song";
                    fetch1.playSong(loginDet);
                    break;
                }
                case 4: {
                    System.out.println("You have been logged out successfully.");
                    System.exit(0);
                }
                default:
                    System.out.println("please select choice correctly");
            }
            System.out.println("if you want to repeat whole process from starting then please enter 1 to continue or enter 0 to exit");
            choice = sc.nextInt();
        } while (choice == 1);

        System.out.println("!!! Good Bye !!!");
    }
}