package com.mycompany.quickchat;

import com.google.gson.Gson;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MessageList {
    // 1. Instantiated the Gson object so you can use it
    private final Gson gson = new Gson();

    // 2. Changed array to start empty, it will be resized dynamically by Gson
    private Message[] list = new Message[0];
    private int lastIndex = -1; // Standard Java style uses camelCase

    public void Append(Message message) {
        // 3. Prevent an IndexOutOfBoundsException by expanding the array if it gets full
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
            // 4. Fixed typo: Changed 'writeString' to 'readString' to import the data
            String jsonString = Files.readString(Paths.get(path));

            // 5. Gson automatically instantiates and sizes this array
            list = gson.fromJson(jsonString, Message[].class);

            // 6. If the JSON file was completely empty, fallback to an empty array
            if (list == null) {
                list = new Message[0];
            }

            // 7. Fixed loop: Standard arrays track size using '.length'
            lastIndex = list.length - 1;

        } catch (IOException e) {
            System.err.println("Failed to read file in path " + path + ": " + e.getMessage());
            lastIndex = -1;
        }
    }

    public void save(String path) {
        try {
            // 8. Fixed variable: Changed 'messages' to your actual variable name 'list'
            String jsonString = gson.toJson(list);
            Files.writeString(Paths.get(path), jsonString);
        } catch (IOException e) {
            System.err.println("Failed to write file in path " + path + ": " + e.getMessage());
        }
    }

    public Message getMessage(int index) {
        // 9. Added a basic bounds check to keep your app from crashing if an invalid index is requested
        if (index < 0 || index > lastIndex) {
            return null;
        }
        return list[index];
    }
}
