package com.example.loginregister.APIPost;

public class loginPOST {
    String username , password , massage;

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public loginPOST(String massage) {
        this.massage = massage;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public loginPOST(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
