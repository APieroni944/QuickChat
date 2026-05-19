package com.mycompany.quickchat;

import java.util.Scanner;
import java.util.Random;

/**
 *
 * @author lab_services_student
 */
public class Message {
    String ID;
    int NumSent;
    String RecipientCell;
    String Message;
    String Hash;

    public Message(int numSent, String recipientCell, String message) {
        Random rand = new Random();
        // Generates a random 10-digit number as a string
        this.ID = String.valueOf(rand.nextLong(1_000_000_000L, 10_000_000_000L));
        this.NumSent = numSent;
        this.RecipientCell = recipientCell;
        this.Message = message;
        this.Hash = createMessageHash();
    }

    public boolean checkMessageID() {
        return this.ID.length() <= 10;
    }

    public boolean checkRecipientCell(String cell) {
        // FIXED: Added the return keyword
        return cell.matches("^\\+27\\d{9}$");
    }

    private String createMessageHash() {
        // FIXED: Moved .toUpperCase() to the String before splitting it
        // Handle empty message safely to prevent array index crashes
        if (this.Message == null || this.Message.trim().isEmpty()) {
            return this.ID.substring(0, 2) + ":" + this.NumSent + ":EMPTY";
        }

        String[] splitMessage = this.Message.toUpperCase().split(" ");
        return this.ID.substring(0, 2) + ":" + Integer.toString(this.NumSent) + ":" + splitMessage[0] + splitMessage[splitMessage.length-1];
    }

    public int SentMessage() {
        // FIXED: Passed System.in into the Scanner constructor
        Scanner scanner = new Scanner(System.in);
        System.out.println("What do you want to do with the message? press the number for the option you want: \n     0: Disregard message \n     1: Send Message \n     2: Save message to send later \n(default = 1)");
        String option = scanner.nextLine();
        switch (option) {
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
                this.printMessageInfo();
                return 1;
            }
        }
    }

    // FIXED: Changed to static so it can process an arbitrary array of messages
    public static void printMessages(Message[] messages) {
        for (int i = 0; i < messages.length; i++){
            System.out.println("-------  Message " + i + "  -------");
            messages[i].printMessageInfo();
            System.out.println("-----------------------------------");
        }
    }

    public int returnTotalMessages() {
        return this.NumSent;
    }

    public void printMessageInfo() {
        System.out.println("Message ID:   " + this.ID + "\nMessage Hash:    " + this.Hash + "\nRecipient:    " + this.RecipientCell + "\nMessage:    " + this.Message);
    }
}
