package com.mycompany.quickchat;

import com.google.gson.Gson;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MessageList {
    private final Gson gson = new Gson();

    private Message[] list = new Message[0];
    private int lastIndex = -1; 
    public void Append(Message message) {
        if (lastIndex + 1 >= list.length) {
            Message[] newList = new Message[list.length + 10];
            System.arraycopy(list, 0, newList, 0, list.length);
            list = newList;
        }
        lastIndex += 1;
        list[lastIndex] = message;
    }

    public void populate(String path) {
        try {
            String jsonString = Files.readString(Paths.get(path));

            list = gson.fromJson(jsonString, Message[].class);

            if (list == null) {
                list = new Message[0];
            }

            lastIndex = list.length - 1;

        } catch (IOException e) {
            System.err.println("Failed to read file in path " + path + ": " + e.getMessage());
            lastIndex = -1;
        }
    }

    public void save(String path) {
        try {
            String jsonString = gson.toJson(list);
            Files.writeString(Paths.get(path), jsonString);
        } catch (IOException e) {
            System.err.println("Failed to write file in path " + path + ": " + e.getMessage());
        }
    }

    public Message getMessage(int index) {
        if (index < 0 || index > lastIndex) {
            return null;
        }
        return list[index];
    }
}
