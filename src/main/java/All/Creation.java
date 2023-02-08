package All;
import AllClasses.PlayList;
import ConnectionSQL.Connection_DB;
import ConnectionSQL.QueryDatabase;
import MainStartApplication.JukeBoxApplication;

import java.util.Scanner;
public class Creation {
    Scanner scanner = new Scanner(System.in);
    public void createPlaylist() {
        int choice;
        PlayList playList = null;
        do {
            System.out.println("please enter playlist ID          : ");
            int playListId = scanner.nextInt();
            System.out.println("please enter name of the playlist : ");
            String playListName = scanner.next();
            playList = new PlayList(playListId,playListName,QueryDatabase.uId);
            QueryDatabase.insertPlayListIntoDataBase(playList);
            System.out.println("if you want to create another playlist then please enter 1 to continue or enter 0 to exit");
            choice = scanner.nextInt();
        } while (choice == 1);
        System.out.println("your details have been saved in database");
    }
}