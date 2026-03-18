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
    String Password;
    String CellphoneNumber;
    public void User (String f, String l, String u, String p, String c) {
        this.FirstName = f;
        this.LastName = l;
        this.UserName = u;
        this.Password = p;
        this.CellphoneNumber = c;
    }
}
