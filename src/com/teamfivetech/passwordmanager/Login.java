package com.teamfivetech.passwordmanager;

public class Login {
    // FIELDS
    static int counter = 1;

    private int id;
    private String siteName;
    private String userName;
    private String password;

    // METHODS
    // constructors
    public Login(String siteName, String userName, String password) {
        setId(counter++);
        setSiteName(siteName);
        setUserName(userName);
        setPassword(password);
    }

    // accessors
    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getSiteName() {
        return siteName;
    }

    private void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getUserName() {
        return userName;
    }

    private void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    @Override       // class Object
    public String toString() {
        return getId() + ", " + getSiteName() + ", " + getUserName() + ", " + getPassword();
    }
}