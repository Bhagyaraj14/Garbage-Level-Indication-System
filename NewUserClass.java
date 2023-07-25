package com.example.garbagemonitoringsystem;

public class NewUserClass {
    private String userId;
    private String firstName;
    private String lastName;
    private String emailId;
    private String phoneNum;
    private String userName;
    private String password;

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    private int distance;


    public NewUserClass(){}
    public NewUserClass(String userId, String firstName, String lastName,
                           String emailId, String phoneNum,
                           String userName, String password, int distance) {
        setUserId(userId);
        setFirstName(firstName);
        setLastName(lastName);
        setEmailId(emailId);
        setPhoneNum(phoneNum);
        setUserName(userName);
        setPassword(password);
        setDistance(distance);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
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
}
