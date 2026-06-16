package com.mycompany.quickchat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {
    MessageList sent;
    MessageList saved;
    MessageList disregarded;

    Message msg2 = new Message(1, "+27838884567", "Where are you? You are late! I have asked you to be on time.");
    Message msg3 = new Message(1, "+27834484567", "Yohoooo, I am outside your gate.");
    Message msg1 = new Message(1, "+27834557896", "Did you get the cake?");
    Message msg4 = new Message(2, "0838884567", "It is dinner time!");
    Message msg5 = new Message(2, "+27838884567", "Ok, I am leaving without you.");

    @BeforeEach
    public void setup() {
        QuickChat.sent = new MessageList();
        QuickChat.saved = new MessageList();
        QuickChat.disregarded = new MessageList();

        QuickChat.sent.Append(msg1);
        QuickChat.sent.Append(msg4);

        QuickChat.saved.Append(msg2);
        QuickChat.saved.Append(msg5);

        QuickChat.disregarded.Append(msg3);

        sent = QuickChat.sent;
        saved = QuickChat.saved;
        disregarded = QuickChat.disregarded;    }

    @Test
    public void populateArray() {
        MessageList expected = new MessageList();
        expected.Append(msg1);
        expected.Append(msg4);

        assertEquals(sent.getAll(), expected.getAll()); // Requires equals() in MessageList
    }

    @Test
    public void testLongmsg() {
        assertEquals(msg2.getMessageInfo(0), QuickChat.longMessage().getMessageInfo(0)); // Requires equals() in Message
    }

    @Test
    public void testSearchID() {
        assertEquals(msg4, QuickChat.SearchID(msg4.ID));
    }

    @Test
    public void testSearchRecipient() {
        MessageList expected = new MessageList();
        expected.Append(msg2);
        expected.Append(msg5);

        assertEquals(expected.getAll(), QuickChat.SearchRecipient("+27838884567").getAll());
    }

    @Test
    public void testDeleteMessage() {
        MessageList expected = new MessageList();
        expected.Append(msg5);

        QuickChat.deleteHash(msg2.Hash);
        assertEquals(expected.getAll(), saved.getAll());
    }

    @Test
    public void testDisplayReport() {
        User user = new User("Kyle", "Johnson","kyl_1", "ABC123#d", "+27123456789");

        String expectedReport = " \n\n Sender: " + user.CellphoneNumber +
                " \n Recipient: " + msg2.RecipientCell +
                " \n Message Hash: " + msg2.Hash +
                " \n\n Sender: " + user.CellphoneNumber +
                " \n Recipient: " + msg5.RecipientCell +
                " \n Message Hash: " + msg5.Hash;

        assertEquals(expectedReport, QuickChat.displayRecipient(user));
    }
}
