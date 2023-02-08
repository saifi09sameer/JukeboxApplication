import All.LogInAndSignUp;
import AllClasses.Users;
import ConnectionSQL.Connection_DB;
import ConnectionSQL.QueryDatabase;
import org.junit.Assert;
import org.junit.Test;
import java.sql.Connection;
public class LogInAndSignUpTest {
    @Test
    public void testGetConnection() {
        Connection con = Connection_DB.getConnection();
        Assert.assertNotNull(con);
    }
    @Test
    public void checkLoginUser() {
        String username = "saifi09sameer";
        String password = "Ammi@123";
        String name = QueryDatabase.checkUser(username, password);
        Assert.assertEquals("Sameer Saifi",name);
    }
    @Test
    public void checkLoginUserFail(){
        String username = "saifi09sameer";
        String password = "Ammi";
        String name = QueryDatabase.checkUser(username, password);
        Assert.assertNotEquals("Sameer Saifi",name);
    }
    @Test
    public void checkRegistration(){
        Users users = new Users(7, "Hello", "hello@123", "hello@gmail.comm", "Hello@123", 0000000000l);
        QueryDatabase.insertUserIntoDataBase(users);
        String Name = QueryDatabase.checkUser("hello@123","Hello@123");
        Assert.assertEquals("Hello",Name);
    }
    @Test
    public void checkRegistrationFail(){
        Users users = new Users(7, "Hello", "hello@123", "hello@gmail.comm", "Hello@123", 0000000000l);
        QueryDatabase.insertUserIntoDataBase(users);
        String Name = QueryDatabase.checkUser("hello@123","Hello@123");
        Assert.assertNotEquals("Duplicate entry '7' for key 'users.PRIMARY'",Name);
    }
    @Test
    public void checkUserValidation(){
        boolean result = LogInAndSignUp.userValidation("saifi09sameer@gmail.com","Ammi@123",9953262233l);
        Assert.assertEquals(true,result);
        boolean result1 = LogInAndSignUp.userValidation("salmansaifi7@gmail.com","Salman@123",8802112339l);
        Assert.assertEquals(true,result1);
        boolean result2 = LogInAndSignUp.userValidation("hello@gmail.com","Hello@123",8482838484l);
        Assert.assertEquals(true,result2);
    }
    @Test
    public void checkUserValidationFail() {
        boolean result = LogInAndSignUp.userValidation("saifi09sameergmail.com", "Ammi@123", 9953262233l);
        Assert.assertNotEquals(true, result);
        boolean result1 = LogInAndSignUp.userValidation("salmansaifi7@gmail.com", "salman@123", 8802112339l);
        Assert.assertNotEquals(true, result1);
        boolean result2 = LogInAndSignUp.userValidation("hello@gmail.com", "Hello@123", 1482838484l);
        Assert.assertNotEquals(true, result2);
    }
}
