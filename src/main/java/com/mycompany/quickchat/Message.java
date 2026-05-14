
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
    ID = rand.nextInt(1000000000, 10000000000);
    NumSent = numSent;
    RecipientCell = recipientCell;
    Message = message;
    Hash = createMessageHash();
  }
  public boolean checkMessageID() {
    return this.ID.length() <= 10;
  } 
  public boolean checkRecipientCell(self) {
    this.RecipientCell.matches("^\\+27\\d{9}$"));
  } 
  private String createMessageHash() {
    String[] splitMessage = this.Message.split(" ").toUpperCase;
    return this.ID.substring(0, 2) + ":" + Integer.toString(this.NumSent) + ":" + splitMessage[0] + splitMessage[splitMessage.length()-1];
  }
  public void SentMessage() {
    String option;
    Scanner scanner = new scanner
    system.out.println("What do you want to do with the message? press the number fot the option you want: \n     0: Disregard message \n     1: Send Message \n     2: Save message to send later \n(default = 1)");
    String option = scanner.nextLine();
    switch (option) {
      case "0": {
        //Disregard message
        break;
      };
      case "2": {
        //save message
        break;
      };
      default: {
        //send message
        break;
      };
    }
  }
}
