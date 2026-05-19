/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.quickchat;
import java.util.*;
import com.google.gson.Gson;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
/**
 *
 * @author lab_services_student
 */
public class QuickChat {
    Message[] sent = new Message[100];
    int sentIndex = 0;
    Message[] saved = new Message[100];
    int savedindex;
    Message[] deleted = new Message[100];
    int deletedindex;
    Scanner scanner = new Scanner(System.in);       //Create instance of scanner

    public static void main(String[] args) {
      User user = Part1();
    }

    public Message[] RetrieveFile(String path) {
        try {
            String jsonString = Files.writeString(Paths.get(path));
            return gson.fromJson(jsonString, Message[].class);
        } catch (IOException e) {
            System.err.println("Failed to read file in path " + path + ": " + e.getMessage());
            return null;
        }
    }
    public void SaveFile(Message[] messages, String path){
        try {
            String jsonString = gson.toJson(messages);
            Files.writeString(Paths.get(path), jsonString);
        } catch (IOException e) {
            System.err.println("Failed to write file in path " + path + ": " + e.getMessage());
        }
    }

    public user Part1() {
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
        return user;
    }
    public void Part2(User user) {
        try {
            SavedMessages = RetrieveFile("SavedMessages.json");
            for(; SavedIndex < PastMessages.length(); SavedIndex++) {
                Saved[SavedIndex] = SavedMessages[SavedIndex];
            }
            SentIndex -= 1;
        } catch (Exception e) {
            system.out.println("Failed to retrieve past messages: " + e.getMessage())
        }


        System.out.println("Welcome to QuickChat \n");
        while(true) {
            System.out.println("Select an option: \n  1: Send messages \n  2: Show recently sent messages \n  3: Quit \n(defautl = 1)");
            
            String option = scanner.nextLine();
            switch (option) {
                case "2": {
                    System.out.println("Coming soon");
                    break;
                }
                case "3": {
                    //Quit program
                    try {
                        SaveFile(SavedMessages, "SavedMessages.json")
                    } catch (Exception e) {
                        System.out.println("Failed to save past messages: " + e.getMessage())
                    }
                    System.exit(0);
                }
                default: {
                    System.out.println("How many messages would you like to send?");
                    int numMessages = null;
                    while numMessages = null {
                        try {
                            String option = scanner.nextLine();
                            numMessages = Integer.paarseint(option);
                        } catch (Exception e) {
                            System,out.println("Please enter a valid integer");
                        }
                    }
                    for (int i = 0; i < numMessages; i++) {

                    }
                }
            }
        }
    }
}

