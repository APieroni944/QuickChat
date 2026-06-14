package com.mycompany.quickchat;

public class strArray {
    String[] list;
    int lastIndex = -1;
    public void append(String s) {
        if (lastIndex + 1 >= list.length) {     //Check for space in the array
            String[] newList = new String[list.length + 10];
            System.arraycopy(list, 0, newList, 0, list.length);
            list = newList;                     //Create new array with ten extra elements and assign it to list after copying over prior data
        }
        lastIndex += 1;                         //increment lastIndex
        list[lastIndex] = s;              //Assign Message to array
    }
    public void delete(int index) {
        for (int i = index; i<lastIndex; i++) {
            list[i] = list[i + 1];
        }
        list[lastIndex] = null;
        lastIndex -= 1;
    }
}
