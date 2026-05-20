package com.mycompany.quickchat;

import java.util.Scanner;
import java.util.Random;

/**
 *
 * @author lab_services_student
 */
public class Message {
    String ID;              //Message ID
    int NumSent;            //Number of messages sent
    String RecipientCell;   //Recipient cell number
    String Message;         //Message
    String Hash;            //Message hash

    public Message(int numSent, String recipientCell, String message) {               //constructor
        Random rand = new Random();                                                   //initialise Random
        this.ID = String.valueOf(rand.nextLong(1_000_000_000L, 10_000_000_000L));     // Generates a random 10-digit number as a string
        this.NumSent = numSent;
        this.RecipientCell = recipientCell;
        this.Message = message;
        this.Hash = createMessageHash();                                               // generate message hash
    }

    public boolean checkMessageID() {
        return this.ID.length() <= 10;                        //Check if message ID is over 10 digits
    }

    public static boolean checkRecipientCell(String cell) {
        // FIXED: Added the return keyword
        return cell.matches("^\\+27\\d{9}$");                 //Use regex to check for valid cellphone number
    }
    public static boolean checkMessageLength(String text) {
        return text.length() <= 250;                          //Check the length of a message
    }

    private String createMessageHash() {
        // Handle empty message to prevent array index crashes
        if (this.Message == null || this.Message.trim().isEmpty()) {
            return this.ID.substring(0, 2) + ":" + this.NumSent + ":EMPTY";
        }

        String[] splitMessage = this.Message.toUpperCase().split(" ");                  //Make the message uppercase and split at ' ' character
        return this.ID.substring(0, 2) + ":" + Integer.toString(this.NumSent) + ":" + splitMessage[0] + splitMessage[splitMessage.length-1];    //return hash
    }

    public int SentMessage(int i) {
        Scanner scanner = new Scanner(System.in); //Initialise scanner
        System.out.println("What do you want to do with the message? press the number for the option you want: \n     0: Disregard message \n     1: Send Message \n     2: Save message to send later \n(default = 1)");
        String option = scanner.nextLine();
        switch (option) {           //Use switch case to decide between options
            case "0": {
                System.out.println("Disregarding message");
                return 0;
            }
            case "2": {
                System.out.println("Saving message");
                return 2;
            }
            default: {
                System.out.println("Message successfully sent");
                this.printMessageInfo(i);
                return 1;
            }
        }
    }

    /*                                                              Moved to MessageList class
    public static void printMessages(Message[] messages) {
        for (int i = 0; i < messages.length; i++){
            System.out.println("-------  Message " + i + "  -------");
            messages[i].printMessageInfo();
            System.out.println("-----------------------------------");
        }
    }
    */

    public int returnTotalMessages() {
        return this.NumSent;
    }

    public void printMessageInfo(int i) {
        System.out.println("\n-------  Message " + i + "  -------");
        System.out.println("Message ID:   " + this.ID + "\nMessage Hash:    " + this.Hash + "\nRecipient:    " + this.RecipientCell + "\nMessage:    " + this.Message);
        System.out.println("----------------------------------- \n");
    }
}
