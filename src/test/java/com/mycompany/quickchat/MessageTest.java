package com.mycompany.quickchat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    private final InputStream originalSystemIn = System.in;

    @AfterEach
    public void tearDown() {
        System.setIn(originalSystemIn);
    }

    @Test
    public void testFirstMessageData() {
        Message msg1 = new Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        
        assertTrue(Message.checkRecipientCell(msg1.RecipientCell));
        
        assertEquals(1, msg1.returnTotalMessages());

        String prefix = msg1.ID.substring(0, 2);
        String expectedHash = prefix + ":1:HITONIGHT?";
        assertEquals(expectedHash, msg1.Hash);
    }

    @Test
    public void testSecondMessageData() {
        Message msg2 = new Message(2, "08575975889", "Hi kegan, did you decieve the payment?");
        
        assertFalse(Message.checkRecipientCell(msg2.RecipientCell));
        
        assertEquals(2, msg2.returnTotalMessages());

        String prefix = msg2.ID.substring(0, 2);
        String expectedHash = prefix + ":2:HIPAYMENT?";
        assertEquals(expectedHash, msg2.Hash);
    }

    @Test
    public void testFirstMessageInteractiveSend() {
        provideInput("1\n");
        Message msg1 = new Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        
        int result = msg1.SentMessage();
        assertEquals(1, result, "Selecting option 1 should return status code 1 (Sent).");
    }

    /**
     * Helper method to redirect System.in for interactive Scanner testing
     */
    private void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }
}
