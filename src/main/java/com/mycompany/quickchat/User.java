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
    public User(String u, String p, String c) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your name:");
        this.FirstName = scanner.nextLine();
        System.out.println("Please enter last name:");
        this.LastName = scanner.nextLine();
        this.UserName = u;
        this.Password = p;
        this.CellphoneNumber = c;
    }
}
