/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quickchat;

/**
 *
 * @author lab_services_student
 */
public class User {
    String FirstName;
    String LastName;
    String UserName;
    private String Password;        //Variable Password private for improved security
    String CellphoneNumber;
    public User(String firstName, String lastName, String userName, String password, String cellphoneNumber) {
        this.FirstName = firstName;         //store first name
        this.LastName = lastName;           //store last name
        this.UserName = userName;           //store username
        this.Password = encryptString(password);        //encrypt then store password
        this.CellphoneNumber = cellphoneNumber;         //store cellphone number
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
        return Password.equals(encryptString(password)); //encrypt input with same key as password and compare
    }
    public void setPassword(String password) {
        Password = encryptString(password);         //encrypt and store password
    }
    public String getCellphoneNumber() {
        return CellphoneNumber;
    }
    public void setCellphoneNumber(String cellphoneNumber) {
        CellphoneNumber = cellphoneNumber;
    }
    private String encryptString(String initial) {
        final char KEY = '%';
        char[] charArray = initial.toCharArray();       //convert string to char array
        for (int i = 0; i < charArray.length; i++) {    //iterate through char array
            charArray[i] = (char) (charArray[i] ^ KEY);     //run bitwise XOR comparison on ASCII value of char and KEY and store result as char
        }
        String result = new String(charArray);      //convert back to string
        return result;                              //return encrypted string
    }
}
