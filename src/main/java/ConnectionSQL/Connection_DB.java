package ConnectionSQL;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connection_DB {
    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/juskeboxapplication", "root", "Ammi@123");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return con;
    }
}
