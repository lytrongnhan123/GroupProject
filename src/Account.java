public class Account
{
    private String userId;
    private String userName;
    private String password;
    private String phoneNumber;
    private String email;
    private String accountType;
    
    public Account(String userId, String userName, String password, String phoneNumber, String email,
            String accountType) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.accountType = accountType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String toString()
    {
        return String.format("User ID: %s\nUsername: %s\nPassword: %s\nPhone Number: %s\nEmail: %s",userId, userName, password, phoneNumber, email);
    }
    
}