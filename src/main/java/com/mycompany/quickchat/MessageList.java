package com.mycompany.quickchat;

import com.google.gson.Gson;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MessageList {
    private final Gson gson = new Gson();     //create instance of gson

    private Message[] list = new Message[0];    //atribute list of type Message array
    private int lastIndex = -1;                 //Atribute lastIndex of type int
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
                list = new Message[0];                                //if empty list initialise as one null element
            }

            lastIndex = list.length - 1;                //Set lastIndex to the last populated element of the array

        } catch (IOException e) {
            System.err.println("Failed to read file in path " + path + ": " + e.getMessage()); //print error message
            lastIndex = -1;
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
        for (int i = 0; i < list.length; i++){
            //System.out.println("-------  Message " + i + "  -------");
            list[i].printMessageInfo(i);                                  //iterate through list and print messages
            //System.out.println("-----------------------------------");
        }
    }
}
