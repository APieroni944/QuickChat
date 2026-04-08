package com.mycompany.quickchat;

import java.io.FileInputStream;

import static org.junit.jupiter.api.Assertions.*;

class LoginTest {

    @org.junit.jupiter.api.Test
    void checkUserName() {
    }

    @org.junit.jupiter.api.Test
    void checkPasswordComplexity() {
    }

    @org.junit.jupiter.api.Test
    void checkCellphoneNumber() {
    }

    @org.junit.jupiter.api.Test
    void registerUser() {
        System.setIn(new FileInputStream("src/test/resources/input.txt"));
    }
    @org.junit.jupiter.api.Test
    void loginUser() {
        User user = new User("Kyle", "Doe", )
    }
}