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
    static MessageList sent = new MessageList();
    static MessageList saved = new MessageList();
    static Scanner scanner = new Scanner(System.in); // Create instance of scanner

    public static void main(String[] args) {
        User user = Part1();
        Part2(user);
    }

    public static User Part1() {
        Login login = new Login();                      // create instance of login
        User user = null;                               // initialise current user as null
        String UName;
        String pwd;
        String cellnum;
        ArrayList<User> userList = new ArrayList<>();   // define arrayList of type user
        userList.add(new User("Kyle", "Johnson","kyl_1", "ABC123#d", "+27123456789"));      // hardcoded entry to userList for testing purposes
        System.out.println("Welcome to QuickChat");
        while (user == null) {
            do {
                System.out.println("Please enter your username:");
                UName = scanner.nextLine();
            } while (!login.checkUserName(UName));     // repeat until valid username
            do {
                System.out.println("Please enter your password:");
                pwd = scanner.nextLine();
            } while (!login.checkPasswordComplexity(pwd)); // repeat until valid password
            try {
                user = login.loginUser(userList, UName, pwd);       // check for existing user
                if (user == null) {                                 // register new user if user not found
                    System.out.println("Registering new user");
                    do {
                        System.out.println("Please enter your cellphone number:");
                        cellnum = scanner.nextLine();
                    } while (!login.checkCellphoneNumber(cellnum));     // repeat until valid cellphone number
                    user = login.registerUserNoChecks(UName, pwd, cellnum);     // register user bypassing checks, as they have already been completed
                    userList.add(user);         // add current user to userList array
                }
            } catch(IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("current user: " + user.getUserName());      // print username of current user
        return user;
    }

    public static void Part2(User user) {
        //sent.populate("sent.json");
        saved.populate("saved.json");

        System.out.println("Welcome to QuickChat \n");
        while(true) {
            System.out.println("Select an option: \n  1: Send messages \n  2: Show recently sent messages \n  3: Quit \n(default = 1)");

            String option = scanner.nextLine();
            switch (option) {
                case "2": {
                    System.out.println("Coming soon");
                    break;
                }
                case "3": {
                    // Quit program
                    saved.save("saved.json");
                    //sent.save("sent.json");
                    System.exit(0);
                }
                default: {
                    System.out.println("How many messages would you like to send?");
                    int numMessages = 0;
                    String cellnum = "";
                    do {
                        try {
                            String input = scanner.nextLine();
                            numMessages = Integer.parseInt(input);
                        } catch (Exception e) {
                            System.out.println("Please enter a valid integer");
                        }
                    } while (numMessages == 0);
                    for (int i = 0; i < numMessages; i++) {
                        do {
                            System.out.println("Please enter the recipient phone number");
                            cellnum = scanner.nextLine();
                        } while (!Message.checkRecipientCell(cellnum));
                        String text;
                        do {
                            System.out.println("Please enter your message (under 250 characters)");
                            text = scanner.nextLine();
                        } while (!Message.checkMessageLength(text));
                        Message message = new Message(numMessages, cellnum, text);
                        switch (message.SentMessage(i)) {
                            case 0: {
                                break;
                            }
                            case 1: {
                                sent.Append(message);
                                break;
                            }
                            case 2: {
                                saved.Append(message);
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
}
