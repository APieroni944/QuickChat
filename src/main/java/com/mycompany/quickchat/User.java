/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quickchat;
import java.util.Scanner;

/**
 *
 * @author lab_services_student
 */
public class User {
    String FirstName;
    String LastName;
    String UserName;
    private String Password;
    String CellphoneNumber;
    public User(String firstName, String lastName, String userName, String password, String cellphoneNumber) {
        this.FirstName = firstName;
        this.LastName = lastName;
        this.UserName = userName;
        this.Password = encryptString(password);
        this.CellphoneNumber = cellphoneNumber;
    }

    public String getFirstName() {
        return FirstName;
    }
    public void setFirstName(String firstName) {
        FirstName = firstName;
    }
    public String getLastName() {
        return LastName;
    }
    public void setLastName(String lastName) {
        LastName = lastName;
    }
    public String getUserName() {
        return UserName;
    }
    public void setUserName(String userName) {
        UserName = userName;
    }
    public boolean authenticatePassword(String password) {
        return password.equals(encryptString(Password));
    }
    public void setPassword(String password) {
        Password = encryptString(password);
    }
    public String getCellphoneNumber() {
        return CellphoneNumber;
    }
    public void setCellphoneNumber(String cellphoneNumber) {
        CellphoneNumber = cellphoneNumber;
    }
    private String encryptString(String initial) {
        final char KEY = '%';
        char[] charArray = initial.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            charArray[i] = (char) (charArray[i] ^ KEY);
        }
        String result = new String(charArray);
        return result;
    }
}
