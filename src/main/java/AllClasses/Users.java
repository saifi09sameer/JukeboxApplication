package AllClasses;

public class Users implements Structure.Users {
    private int userId;
    private String name;
    private String UserName;
    private String EmailID;
    private String password;
    private long mobileNumber;

    public Users(int userId, String name, String userName, String emailID, String password, long mobileNumber) {
        this.userId = userId;
        this.name = name;
        UserName = userName;
        EmailID = emailID;
        this.password = password;
        this.mobileNumber = mobileNumber;
    }

    @Override
    public String toString() {
        return "User_Details{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", UserName='" + UserName + '\'' +
                ", EmailID='" + EmailID + '\'' +
                ", password='" + password + '\'' +
                ", mobileNumber=" + mobileNumber +
                '}';
    }

    public Users() {
    }

    @Override
    public String getEmailID() {
        return EmailID;
    }

    @Override
    public void setEmailID(String emailID) {
        EmailID = emailID;
    }

    @Override
    public long getMobileNumber() {
        return mobileNumber;
    }

    @Override
    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Override
    public int getUserId() {
        return userId;
    }

    @Override
    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getUserName() {
        return UserName;
    }

    @Override
    public void setUserName(String userName) {
        UserName = userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

}
