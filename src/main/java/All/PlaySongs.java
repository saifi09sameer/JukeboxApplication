package All;
import ConnectionSQL.Connection_DB;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
public class PlaySongs extends Connection_DB {
    Scanner scanner = new Scanner(System.in);
    static String STATUS= "Nothing";

    // this method is to play song by id
    public void playSong(String loginDet) {
        Connection con = getConnection();
        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(loginDet);
            int id = 0;
            String path = "";
            String song_name = "";
            String song_duration="";
            System.out.println("Please enter the id of the song that you want to play");
            int songId = scanner.nextInt();
            while (resultSet.next()) {
                id = resultSet.getInt("id");
                path = resultSet.getString("path_name");
                song_name = resultSet.getString("song_name");
                song_duration = resultSet.getString("song_duration");
                File file = new File(path);
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);

                if (songId == id) {
                    String response = "";
                    while (!response.equals("Q")) {
                        System.out.println("+-----------------------------------------------------------------------+");
                        System.out.println("| Song Name     :                |"+ song_name);
                        System.out.println("| Song Duration :                |"+ song_duration);
                        System.out.println("| Song STATUS   :                |"+ STATUS);
                        System.out.println("+-----------------------------------------------------------------------+");
                        System.out.println("P = play, S = Stop, L = Loop, N = Next, Q = Quit");
                        System.out.print("Enter your choice : ");
                        response = scanner.next();
                        response = response.toUpperCase();
                        switch (response) {
                            case ("P"):
                                long currentTimeMillis = System.currentTimeMillis();
                                SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
                                System.out.println("Music started at: " + sdf1.format(new Date(currentTimeMillis)));
                                clip.start();  // to play the song
                                STATUS="Playing";
                                break;
                            case ("S"):
                                long currentTimeMillis1 = System.currentTimeMillis();
                                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                                System.out.println("Music stopped at: " + sdf.format(new Date(currentTimeMillis1)));
                                clip.stop();  // to stop the song
                                STATUS="STOP";
                                break;
                            case ("L"):
                                clip.loop(Clip.LOOP_CONTINUOUSLY);       // to play song in the loop
                                STATUS = "IN LOOP";
                                break;
                            case ("N"):         // added case to play the next song
                                clip.close();   // close the current clip
                                // check if there is another song to play
                                if (resultSet.next()) {
                                    path = resultSet.getString("path_name");
                                    song_name = resultSet.getString("song_name");
                                    song_duration = resultSet.getString("song_duration");
                                    file = new File(path);
                                    audioStream = AudioSystem.getAudioInputStream(file);
                                    clip = AudioSystem.getClip();
                                    clip.open(audioStream);
                                    clip.start();
                                    STATUS = "Playing";
                                } else {
                                    System.out.println("No more songs to play");
                                    response = "Q";
                                }
                                break;
                            case ("Q"):
                                clip.close();    //to close the clip
                                break;
                            default:
                                System.out.println("Not a valid response");
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Problem is " + e);
        }
    }
    // this method is used to get all songs present in the list

}


