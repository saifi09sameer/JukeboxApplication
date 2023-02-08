package ExceptionUser;

public class UserValidationException extends Exception{
    public UserValidationException(String msg){
        super(msg);
    }
}
