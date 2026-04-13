/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.quickchat;
import java.util.*;
/**
 *
 * @author lab_services_student
 */
public class QuickChat {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);       //Create instance of scanner
        Login login = new Login();                      //create instance of login
        User user = null;                               //initialise current user as null
        String UName;
        String pwd;
        String cellnum;
        ArrayList<User> userList = new ArrayList<>();   //define arrayList of type user
        userList.add(new User("Kyle", "Johnson","kyl_1", "ABC123#d", "+27123456789"));      //hardcoded entry to userList for testing purposes
        System.out.println("Welcome to QuickChat");
        while (user == null) {
            do {
                System.out.println("Please enter your username:");
                UName = scanner.nextLine();
            } while (!login.checkUserName(UName));     //repeat until valid username
            do {
                System.out.println("Please enter your password:");
                pwd = scanner.nextLine();
            } while (!login.checkPasswordComplexity(pwd)); //repeat until valid password
            try {
                user = login.loginUser(userList, UName, pwd);       //check for existing user
                if (user == null) {                                 //register new user if user not found
                    System.out.println("Registering new user");
                    do {
                        System.out.println("Please enter your cellphone number:");
                        cellnum = scanner.nextLine();
                    } while (!login.checkCellphoneNumber(cellnum));     //repeat until valid cellphone number
                    user = login.registerUserNoChecks(UName, pwd, cellnum);     //register user bypassing checks, as they have already been completed
                    userList.add(user);         //add current user to userList array
                }
            } catch(IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("current user: " + user.getUserName());      //print username of current user
    }
}
