package com.mycompany.quickchat;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class MessageTest {

    private MessageList messages;
    private List<User> users;

    @BeforeAll
    void initlogin() {
        users.add(new User("Kyle", "Johnson", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        messages.Append(new Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?"));
        messages.Append(new Message(2, "08575975889", "Hi kegan, did you decieve the payment?"));
        messages.Append(new Message(3, "+27123456789", "Over 250 character message: qwertyuioplkjhgfdsazxcvbnm,lkjhgfdsaqwertyuiop poiuytrewqasdfghjkl mnbvcxzasdfghjkoiuytrewasdfghjklmnb qwertyui. nbvcxasdfghjk98765432sdfghj kjhgfdswertyuikjhgf zxcvbnm.kjhgfdwertyu. wertyuioplkjhgfdszxcvbnmlkjhgfdsa oiuytrewqasdfghjk wertyuiokjhgfdsazxcvbn lkjhgfdsaqwertyuiolkjhg .mnbvcxsdrtyh"));
    }

    @Test
    MessageLength() {
        assertTrue(Message.checkMessageLength(messages[0].list.message));
        assertFalse(Message.checkMessageLength(messages[2].list.message));
    }
