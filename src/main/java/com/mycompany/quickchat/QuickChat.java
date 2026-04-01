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
        Scanner scanner = new Scanner(System.in);
        Login login = new Login();
        User user = null;
        String UName;
        String pwd;
        String cellnum;
        ArrayList<User> userList = new ArrayList<>();
        System.out.println("Welcome to QuickChat");
        while (user == null) {
            do {
                System.out.println("Please enter your username:");
                UName = scanner.nextLine();
            } while (!login.checkUserName(UName));
            do {
                System.out.println("Please enter your pasword:");
                pwd = scanner.nextLine();
            } while (!login.checkPasswordComplexity(pwd));
            do {
                System.out.println("Please enter your cellphone number:");
                cellnum = scanner.nextLine();
            } while (!login.checkCellphoneNumber(cellnum));
            user = login.registerUserNoChecks(UName, pwd, cellnum);
        }
        userList.add(user);
    }
}
