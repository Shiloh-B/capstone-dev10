package capstone.models;

public class User {

    private int userId;
    private String username;
    private String passwordHash;
    private boolean isDisabled;
    public User(){
    }
    public User(int userId, String username, String passwordHash, boolean isDisabled) {
        this.userId = userId;
        this.username = username;
        this.passwordHash = passwordHash;
        this.isDisabled = isDisabled;
    }


    //TODO RoomUser list probably
    public int getUserID() {
        return userId;
    }

    public void setUserID(int userID) {
        this.userId = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public boolean getIsDisabled() {
        return isDisabled;
    }

    public void setDisabled(boolean disabled) {
        isDisabled = disabled;
    }

}
