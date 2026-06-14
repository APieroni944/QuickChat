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
    static MessageList disregarded = new MessageList();
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

    public static void Part2(User user) {         //Also contains parts of part 3
        //sent.populate("sent.json");
        saved.populate("saved.json");             //Populate saved messages array from .json file

        System.out.println("Welcome to QuickChat \n");
        while(true) {                             //while(true) so the program keeps repeating untill terminated
            System.out.println("Select an option: \n  1: Send messages \n  2: Show recently sent messages \n  3: Quit \n  4: Stored messages \n (default = 1)");

            String option = scanner.nextLine();
            switch (option) {                      //switch case to decide between options
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
                case "4": {
                    //work with all stored messages
                    Part3(user);
                    break;
                }
                default: {                          //catch all case to send message to avoid errors
                    System.out.println("How many messages would you like to send?");
                    int numMessages = -1;            //Initialise numMessages and cellnum outside of try catch so they stay in scope
                    String cellnum = "";
                    do {
                        try {                       //try catch in case the user enters a !digit
                            String input = scanner.nextLine();
                            numMessages = Integer.parseInt(input);
                        } catch (Exception e) {
                            System.out.println("Please enter a valid integer");
                        }
                    } while (numMessages == -1);
                    for (int i = 0; i < numMessages; i++) {
                        do {
                            System.out.println("Please enter the recipient phone number");    //prompt user for cellphone number
                            cellnum = scanner.nextLine();
                        } while (!Message.checkRecipientCell(cellnum));       //repeat until cellnum is valid
                        String text;
                        do {
                            System.out.println("Please enter your message (under 250 characters)");
                            text = scanner.nextLine();
                        } while (!Message.checkMessageLength(text));          //repeat until message under 250 characters
                        Message message = new Message(numMessages, cellnum, text);      //declare message
                        switch (message.SentMessage(i)) {     //call SentMessage and use return value for switch case
                            case 0: {
                                break;                    //disregard message (do nothing)
                            }
                            case 1: {
                                sent.Append(message);     //Append to sent array
                                break;
                            }
                            case 2: {
                                saved.Append(message);    //Append to saved array
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
    public static void Part3(User user) {
        System.out.println("Select an option: " +
                "\n  1: Display sender and recipient of all saved messages " +
                "\n  2: Display the longest stored message " +
                "\n  3: Search for message by ID " +
                "\n  4: Search stored messages by recipient " +
                "\n  5: Delete message by message hash " +
                "\n  6: List details of all stored messages " +
                "\n (default = NONE)");

        String option = scanner.nextLine();
        switch (option) {
            case "1": {
                //Display sender and recipient of all saved messages
                for (Message msg : saved.list) {
                    System.out.println("\n Sender: " + user.CellphoneNumber +
                            " \n Recipient: " + msg.RecipientCell +
                            "\n");
                }
                break;
            }
            case "2": {
                //Display the longest stored message
                Message longmsg = saved.list[0];
                for (Message msg : saved.list) {
                    if (msg.Message.length() > longmsg.Message.length()) {
                        longmsg = msg;
                    }
                }
                longmsg.printMessageInfo(0);
                break;
            }
            case "3": {
                //Search for message by ID
                System.out.println("Enter the message ID you wish to search for");
                String id = scanner.nextLine();
                try {
                    Message msg = SearchID(id);
                    msg.printMessageInfo(0);
                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            }
            case "4": {
                //Search stored messages by recipient
                System.out.println("Enter the message recipient you wish to search for");
                String id = scanner.nextLine();
                try {
                    Message msg = SearchRecipient(id);
                    msg.printMessageInfo(0);
                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            }
            case "5": {
                //Delete message by message hash
                System.out.println("Enter the message hash you wish to delete");
                String hash = scanner.nextLine();
                try {
                    deleteHash(hash);
                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            }
            case "6": {
                //List details of all stored messages
                System.out.println("\n\n  SAVED MESSAGES \n");
                saved.printMessages();
                System.out.println("\n\n  SENT MESSAGES \n");
                sent.printMessages();
                System.out.println("\n\n  DISREGARDED MESSAGES \n");
                disregarded.printMessages();
                break;
            }
            default: {
                //Do nothing
                break;
            }
        }
    }
    public static Message SearchID(String id) {
        for (Message msg : sent.list) {
            if(msg.ID == id) {
                return msg;
            }
        }
        for (Message msg : saved.list) {
            if(msg.ID == id) {
                return msg;
            }
        }
        for (Message msg : disregarded.list) {
            if(msg.ID == id) {
                return msg;
            }
        }
        throw new IllegalArgumentException("Message ID does not exist");
    }
    public static Message SearchRecipient(String recipient) {
        for (Message msg : sent.list) {
            if(Objects.equals(msg.RecipientCell, recipient)) {
                return msg;
            }
        }
        for (Message msg : saved.list) {
            if(Objects.equals(msg.RecipientCell, recipient)) {
                return msg;
            }
        }
        for (Message msg : disregarded.list) {
            if(Objects.equals(msg.RecipientCell, recipient)) {
                return msg;
            }
        }
        throw new IllegalArgumentException("Message ID does not exist");
    }
    public static void deleteHash(String hash) {
        for (int i = 0; i <= sent.lastIndex; i++) {
            if(Objects.equals(sent.list[i].Hash, hash)) {
                sent.delete(i);
                System.out.println("Message successfully deleted");
                return;
            }
        }
        for (int i = 0; i <= saved.lastIndex; i++) {
            if(Objects.equals(saved.list[i].Hash, hash)) {
                saved.delete(i);
                System.out.println("Message successfully deleted");
                return;
            }
        }
        for (int i = 0; i <= disregarded.lastIndex; i++) {
            if(Objects.equals(disregarded.list[i].Hash, hash)) {
                disregarded.delete(i);
                System.out.println("Message successfully deleted");
                return;
            }
        }
        throw new IllegalArgumentException("Message hash does not exist");
    }
}
