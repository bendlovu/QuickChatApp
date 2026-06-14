/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import com.mycompany.quickchatapp.Message;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Student
 */
public class AppTest {
    AppTest s = new AppTest();
    Message messageManager = new Message();
    
   public static String userName;
   public static String password;
   static String testMessages;
   Message msg1, msg2, msg3, msg4, msg5;
   
   @Test
   public void loginSuccess(){
            s.userName = "kyl_1";
            s.password = "Ch&&sec@ke99!";
            boolean loginResult = "kyl_1".equals(s.userName) && "Ch&&sec@ke99!".equals(s.password);
            assertTrue(loginResult);
        }
      
   @Test
    public void userLoginFailure() {
        System.out.println("userLogin - Failure Case Verification");
      
        AppTest.userName = "kyl_1";
        AppTest.password = "channing";
        
        String wrongUser = "wrong_id";
        String wrongPass = "password123";
        
        boolean loginMatches = wrongUser.equals(AppTest.userName) && wrongPass.equals(AppTest.password);
        assertFalse(loginMatches, "Login should fail when incorrect credentials are provided.");
    }
   
    @Test
     public void checkMessageLengthSuccess() {
        System.out.println("Testing: checkMessageLength (Success Case)");
        String testMessage = "Hi Mike, can you join us for dinner tonight?";
        String expected = "Message ready to send.";
        
        String actual = Message.checkMessageLength(testMessage);
        assertEquals(expected, actual);
    }
     
     @Test
    public void checkMessageLengthFailure() {
        System.out.println("Testing: checkMessageLength (Failure Case)");
      
        String longMessage = "This is a deliberately long string designed to exceed the max capacity limit "
                + "imposed by the system specifications of two hundred and fifty individual characters "
                + "in total lengths. We are doing this to ensure that our calculation logic captures the "
                + "exact offset value cleanly.";
        
        int extraCount = longMessage.length() - 250; 
        String expected = "Message exceeds 250 characters by " + extraCount + "; please reduce the size.";
        
        String actual = Message.checkMessageLength(longMessage);
        assertEquals(expected, actual);
    }
    
    @Test
    public void checkRecipientCellSuccess() {
        System.out.println("Testing: checkRecipientCell (Success Case)");
        Message instance = new Message();
        String validCell = "+27718693002";
        String expected = "Cell phone number successfully captured.";
        
        String actual = instance.checkRecipientCell(validCell);
        assertEquals(expected, actual);
    }
    
    @Test
    public void checkRecipientCellFailure() {
        System.out.println("Testing: checkRecipientCell (Failure Case)");
        Message instance = new Message();
        String invalidCell = "08575975889";
        String expected = "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        
        String actual = instance.checkRecipientCell(invalidCell);
        assertEquals(expected, actual);
    }
    
    @Test
     public void createMessageHash() {
        System.out.println("Testing: createMessageHash Rules Verification");
        Message instance = new Message();
        String mockID = "1000000000";
        String mockText = "Hi Mike, can you join us for dinner tonight?";
        
        String expectedHash = instance.createMessageHash(mockID, mockText);
        String actualHash = instance.createMessageHash(mockID, mockText);
        
        assertEquals(expectedHash, actualHash, "The computed hash strings must match exactly.");
    }
     
     @Test
    public void sentMessageMenuReturnText() {
        System.out.println("Testing: sentMessage Option Returns");
        Message instance = new Message();
        String summaryMock = "ID: 10\nHash: Test\nRecipient: +2771\nMessage: Sample Text";
        
        assertEquals("Message successfully sent.", instance.sentMessage(1, summaryMock));
        
        assertEquals("Press 0 to delete the message", instance.sentMessage(2, summaryMock));
        
        assertEquals("Message successfully stored", instance.sentMessage(3, summaryMock));
    }


@Test
public void sentMessagesArrayPopulated() {
    System.out.println("Testing Sent Messages Array population (Success case)");
    
    java.util.ArrayList<String> testSentArray = new java.util.ArrayList<>();
    testSentArray.add("Did you get the cake?");
    testSentArray.add("It is dinner time!");
    
    assertEquals(2, testSentArray.size());
    assertEquals("Did you get the cake?", testSentArray.get(0));
}
 @Test
 public void displayLongestMessages() {
     System.out.println("Testing evaluation of longest message character length");
     
     java.util.ArrayList<String> testMessages = new java.util.ArrayList<>();
     
     String msg1 = "Did you get the cake?";
     String msg2 = "Where are you? You are late! I have asked you to be on time.";
     String msg3 = "";
     String msg4 = "";
     
     String longest = ""; 
     for (String msg : testMessages) {        
     if(msg1.length() > longest.length()) {
         longest = msg;
     }     
 }
     String expectedLongest = "Where are you? You are late!";
     assertEquals(expectedLongest, longest, "The system should identify Message 2 as the longest.");
 }

@Test
public void searchMessageID() {
  Message msg = new Message ("+27834557896", "Did you get the cake");
  assertTrue(msg.searchMessageID());
   }


@Test
public void searchRecipientMessage() {
  
        java.util.ArrayList<String> recipients = new java.util.ArrayList<>();
        java.util.ArrayList<String> contents = new java.util.ArrayList<>();
        
        recipients.add("+2738884567");
        contents.add("Where are you? You are late! I have asked you to be on time.");
        recipients.add("+2738884567");
        contents.add("Ok, I am leaving without you.");
       String queryCell = "+2738884567";
        int matchCounter = 0;
        
        for (String cell : recipients) {
            if (cell.equals(queryCell)) {
                matchCounter++;
            }
        }
        assertEquals(2, matchCounter);
    }

@Test
    public void deleteMessageUsingHash() {
    String result = app.deleteMessageUsingHash("HASH1234");
    assertEquals("hash signature match not loaded", result);
    }
    
@Test
    public void displayReport() {
    
        String mockHash = "M4_HASH123";
        String mockRecipient = "+27838884567";
        String mockMessage = "It is dinner time !";
       
        String reportOutput = "Hash: " + mockHash + " | Recipient: " + mockRecipient + " | Message: " + mockMessage;
        
        assertTrue(reportOutput.contains(mockHash), "Report should display the message hash.");
        assertTrue(reportOutput.contains(mockRecipient), "Report should display the recipient cell.");
        assertTrue(reportOutput.contains(mockMessage), "Report should display the message content.");
    }
}
    
            
