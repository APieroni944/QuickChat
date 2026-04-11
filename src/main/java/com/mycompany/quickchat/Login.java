/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quickchat;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author lab_services_student
 */
public class Login {
    public boolean checkUserName(String UserName) {
        if (UserName.contains("_") && UserName.length() <= 5) {
            System.out.println("Username successfully captured");
            return true;
        } else {
            System.out.println("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than 5 characters.");
            return false;
        }
    }
    public boolean checkPasswordComplexity(String Password) {
        int UCase = 0;
        int Digit = 0;
        int Special = 0;
        for (char c : Password.toCharArray()) {
            if (Character.isUpperCase(c)) UCase +=1;            //count upper case
            if (Character.isDigit(c)) Digit +=1;                //count digit
            if (!Character.isLetterOrDigit(c)) Special +=1;     //count special
        }
        if (UCase > 0 && Digit > 0 && Special > 0) {            //check that all counts exceed 0
            System.out.println("Password successfully captured");
            return true;
        } else {
            System.out.println("Password is not correctly formatted; please ensure that your password contains at least 8 characters, a capital letter, a number and a special character.");
            return false;
        }
    }
    public boolean checkCellphoneNumber(String CellNum) {
        if (CellNum.startsWith("+27") && CellNum.length() == 12) {
            System.out.println("Cellphone number successfully captured");
            return true;
        } else {
            System.out.println("Cellphone number incorrectly formatted or does not contain international code");
            return false;
        }
    }
    public User registerUser(String Username, String Password, String CellNum) {

        if (checkUserName(Username) && checkPasswordComplexity(Password) && checkCellphoneNumber(CellNum)) {        //call relevant check methods
            return registerUserNoChecks(Username, Password, CellNum);       //call necessary process from other function to reduce redundancy
        } else {
            System.out.println("User not registered");
            return null;
        }
    }
    public User registerUserNoChecks(String Username, String Password, String CellNum) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your name:");
        String FirstName = scanner.nextLine();
        System.out.println("Please enter last name:");
        String LastName = scanner.nextLine();
        User user = new User(FirstName, LastName, Username, Password, CellNum);
        System.out.println("User registered successfully");
        return user;                //return user variable
    }
    public User loginUser(List<User> Users, String Username, String Password) {
        for (User user : Users) {           //iterate through user array
            if (Username.equals(user.UserName) && user.authenticatePassword(Password)) {        //check username and password
                System.out.println("Welcome "+ user.FirstName + ", " + user.LastName + ". It is great to see you again");
                return user;        //return matching user from array
            }
        }
        System.out.println("Username or password incorrect. Please try again");
        return null;        //return null if user not found
    }
}