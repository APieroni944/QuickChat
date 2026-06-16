package com.mycompany.quickchat;

import com.google.gson.Gson;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public class MessageList {
    private final Gson gson = new Gson();     //create instance of gson

    Message[] list = new Message[0];    //atribute list of type Message array
    int lastIndex = -1;                 //Atribute lastIndex of type int

    public void Append(Message message) {
        if (lastIndex + 1 >= list.length) {     //Check for space in the array
            Message[] newList = new Message[list.length + 1];
            System.arraycopy(list, 0, newList, 0, list.length); 
            list = newList;                     //Create new array with one extra element and assign it to list after copying over prior data
        }
        lastIndex += 1;                         //increment lastIndex
        list[lastIndex] = message;              //Assign Message to array
    }

    public void populate(String path) {
        try {
            String jsonString = Files.readString(Paths.get(path));    //read from file path

            list = gson.fromJson(jsonString, Message[].class);        //convert from json string to type Message[]

            if (list == null) {
                list = new Message[1];                                //if empty list initialise as 1 null elements
            }

            lastIndex = -1;                //Set lastIndex to the last populated element of the array
            while (list[lastIndex + 1] != null && !Objects.equals(list[lastIndex + 1], new Message(0, "+27000000000", ""))) {
                lastIndex += 1;
            }

        } catch (IOException e) {
            System.err.println("Failed to read file in path " + path + ": " + e.getMessage()); //print error message
            lastIndex = -1;
        } catch (ArrayIndexOutOfBoundsException e) {
            lastIndex = list.length - 1;
        }
    }

    public void save(String path) {
        try {
            String jsonString = gson.toJson(list);                  //convert Message[] to json string
            Files.writeString(Paths.get(path), jsonString);         //write to file path
        } catch (IOException e) {
            System.err.println("Failed to write file in path " + path + ": " + e.getMessage()); //print error message
        }
    }

    public Message getMessage(int index) {
        if (index < 0 || index > lastIndex) {     //if message out of bounds return null
            return null;
        }
        return list[index];   //return type Message at index in list
    }

    public void printMessages() {
        for (int i = 0; i < lastIndex; i++){
            //System.out.println("-------  Message " + i + "  -------");
            list[i].printMessageInfo(i);                                  //iterate through list and print messages
            //System.out.println("-----------------------------------");
        }
    }
    public String getAll() {
        String str = "";
        for (int i = 0; i < lastIndex; i++) {
            if (list[i] != null) {
                //System.out.println("-------  Message " + i + "  -------");
                list[i].getMessageInfo(i);                                  //iterate through list and print messages
                //System.out.println("-----------------------------------");
            }
        }
        return str;
    }
    public void delete(int index) {               //Note: this operation could be optomised by instead using a linked list
        for (int i = index; i<lastIndex; i++) {
            list[i] = list[i + 1];                //Move all elements after i back by one to overwrite i 
        }
        list[lastIndex] = new Message(0, "+27000000000", "");                   //Set the last element to null to avoid duplication
        lastIndex -= 1;                           //Decrement lastIndex 
    }
}
