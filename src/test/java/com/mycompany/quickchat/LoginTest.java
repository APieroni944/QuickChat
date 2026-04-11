package com.mycompany.quickchat;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class LoginTest {

    private Login login;
    private User[] users;

    @BeforeEach
    void initlogin() {
        login = new Login();
        users = new User[1];
        users[0] = new User("Kyle", "Johnson", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
    }

    @Test
    void checkUserName() {
        assertTrue(login.checkUserName("kyl_1"));
        assertFalse(login.checkUserName("kyle!!!!!!!"));
    }

    @Test
    void checkPasswordComplexity() {
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
        assertFalse(login.checkPasswordComplexity("password"));
    }

    @Test
    void checkCellphoneNumber() {
        assertTrue(login.checkCellphoneNumber("+27838968976"));
        assertFalse(login.checkCellphoneNumber("08966553"));
    }

    @Test
    void loginUser() {
        assertNull(login.loginUser(users, "Jon_2", "ABC123#d"));
        assertEquals(users[0], login.loginUser(users, "kyl_1", "Ch&&sec@ke99!"));
    }
}