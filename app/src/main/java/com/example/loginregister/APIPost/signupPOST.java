package com.example.loginregister.APIPost;

public class signupPOST {

    private int id ;
    private String fullname ;
    private String username ;
    private String email ;
    private String password ;

    private String massage ;

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public signupPOST(String massage) {
        this.massage = massage;
    }

    public signupPOST(String fullname, String email, String username, String password) {

        this.fullname = fullname;
        this.username = username;
        this.username = username;

        this.password = password;
    }



    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
