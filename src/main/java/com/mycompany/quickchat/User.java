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
    String Password;
    String CellphoneNumber;
    public User(String firstName, String lastName, String userName, String password, String cellphoneNumber) {
        this.FirstName = firstName;
        this.LastName = lastName;
        this.UserName = userName;
        this.Password = password;
        this.CellphoneNumber = cellphoneNumber;
    }
}
