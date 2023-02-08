package All;
import AllClasses.Users;
import ConnectionSQL.QueryDatabase;
import ExceptionUser.UserNotFoundException;
import ExceptionUser.UserValidationException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class LogInAndSignUp {
    Scanner scanner = new Scanner(System.in);
    public void registerNewUser() {
        Users users = null;
        System.out.println("Please Enter User Id              : ");
        int id = scanner.nextInt();
        System.out.println("Please Enter User Name            : ");
        String nameOfUser = scanner.next();
        System.out.println("Please Enter username of the User : ");
        String userName = scanner.next();
        System.out.println("Please Enter EmailID              : ");
        String emailId = scanner.next();
        System.out.println("Please Enter a Password           : ");
        String pass = scanner.next();
        System.out.println("Please Enter MobileNumber         : ");
        long mobileNumber = scanner.nextLong();
        try {
            if (userValidation(emailId, pass, mobileNumber)) {
                users = new Users(id, nameOfUser, userName, emailId, pass, mobileNumber);
                QueryDatabase.insertUserIntoDataBase(users);
            } else throw new UserValidationException("Please Enter Valid Details : ");
        }catch (UserValidationException userValidationException){
            System.out.println(userValidationException.getMessage());
        }


    }

    public boolean loginUser() {
        System.out.println("Please Enter username : ");
        String userName = scanner.next();
        System.out.println("Please Enter password : ");
        String pass = scanner.next();
        try {
            String name = QueryDatabase.checkUser(userName, pass);
            if (name != null) {
                System.out.println(name + " has logged in successfully. ");
                return true;
            }else throw new UserNotFoundException("Check username and password again, Login Failed!!");

        } catch (UserNotFoundException userNotFoundException) {
            System.out.println(userNotFoundException.getMessage());
        }
        return false;
    }
    public static boolean userValidation(String EmailID, String password, long mobileNumber) {
        int counter = 0;
        if (EmailID == null || password == null || mobileNumber == 0) {
            return false;
        }
        //PasswordChecking
        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";
        Pattern passwordValid = Pattern.compile(regex);
        Matcher passwordMatcher = passwordValid.matcher(password);
        boolean result = passwordMatcher.matches();
        if (result == true) {
            counter++;
        }
        //MobileNumberChecking
        String mobileNumberString = String.valueOf(mobileNumber);
        Pattern mobileValid = Pattern.compile("(0|91)?[6-9][0-9]{9}");
        Matcher mobileMatcher = mobileValid.matcher(mobileNumberString);
        boolean resultMobile = mobileMatcher.find() && mobileMatcher.group().equals(mobileNumberString);
        if (resultMobile == true) {
            counter++;
        }
        //EmailChecking
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        boolean resultEmail = pat.matcher(EmailID).matches();
        if (resultEmail == true) {
            counter++;
        }
        //Counting all result are true or not
        if (counter == 3) {
            return true;
        }
        return false;
    }
}
