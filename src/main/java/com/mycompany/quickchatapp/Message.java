/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quickchatapp;
 
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Random;
import java.util.Scanner;
/**
 *
 * @author Student
 */
public class Message {
   
    static String[] sentMessages = new String[100];
    static String[] disregardedMessages = new String[100];
    static String[] storedMessages = new String[100];
    static String[] messageHashes = new String[100];
    static String[] messageIDs = new String[100];
    static String[] recipientSent = new String[100];
   
    static int disregardedCount = 0;
    static int storedCount =0;
    static int sentMessagesCount = 0;
    static int totalMessages = 0;
   
    static Scanner input = new Scanner(System.in);
    private static String recipients;

    private static void searchByMessageID() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    private String messageID;
    private String recipientCell;
    private String message;
    private String messageText;
    private String messageHash;
    private String status;
    private String recipient;
    
    public Message() {
        this.messageID = "";
        this.recipientCell = "";
        this.message = "";
    }
    public Message(String recipientCell, String message) {
        this.messageID = generateMessageID();
        this.recipientCell = recipientCell;
        this.message = message;
    }
    public String getMessageID() {
        return this.messageID;
    }
    public String generateMessageID() {
        Random random = new Random();
        long number = 1000000000L + (long) + (random.nextDouble() * 9000000000L);
        return String.valueOf(number);
    }
    public boolean checkMessageID() {
        return this.messageID.length() <= 10;
    }
    public String checkRecipientCell(String cellNumber) {
        if (cellNumber.length()== 12 && cellNumber.startsWith("+27")) {
            return "Cell number successfully captured.";
        } else {
            return "Cell number is incorretly formatted or does not contain an international code.";
        }
    }
    public static String checkMessageLength(String messageText) {
        if (messageText.length() < 250){
        return "Message ready to send";
    } else {
        int extraChars = messageText.length() -250;
        return "Message exceeds 250 characters by" + extraChars + " ; please reduce the size.";
  }
}
public String createMessageHash(String msgID, String msgText) {
    String[] words = msgText.split(" ");
    String firstWord = words [0];
    String lastWord = words[words.length -1];
    
    return msgID.substring(0,  2) + ":" + msgText.length()+ ":" + firstWord + lastWord;
 }


public String sentMessage(int choice, String formattedMessageSummary) {
    Scanner input = new Scanner(System.in);
    
    switch (choice) {
        case 1:
            sentMessages[totalMessages] = formattedMessageSummary;
            totalMessages++;
            return "Message successfully sent.";
            
        case 2:
            disregardedMessages[disregardedCount] = formattedMessageSummary;
            disregardedCount++;
            return "Press 0 to delete the message";
            
        case 3:
            storedMessages[storedCount] = formattedMessageSummary;
            storedCount++;
            return "Message successfully stored"; 
            
        default:
            return "invalid option selected";
    }
}

public void storeMessage() {
    JSONObject messageDetails = new JSONObject();
    messageDetails.put("MessageID", messageID);
    messageDetails.put("Recipient", recipientCell);
    messageDetails.put("Message", message);
    messageDetails.put("Message Hash", messageHashes);
    
    JSONArray messageArray = new JSONArray();
    messageArray.add(messageDetails);
    
    System.out.println("\nStored JSON Message:");
    System.out.println(messageArray.toJSONString());
}

public static void searchByMessageID(String id) { 
    for(int i=0; i< sentMessagesCount; i++) {
        if(messageIDs[i].equalsIgnoreCase(id)) {
         System.out.println("Recipient" + recipientSent[i]);
            System.out.println("Message" + storedMessages[i]);
        }
     }
   }

public static void searchByRecipient() {
    boolean found=false;
    for(int i=0; i< sentMessagesCount; i++) {
        if(recipientSent[i].equalsIgnoreCase(recipients)){
            System.out.println("\nID" + messageIDs[i]);
            System.out.println("Recipient" +recipientSent[i]);
            System.out.println("Message"+ storedMessages[i]);
            found=true;
    }
    if(!found) {
        System.out.println("No Messages found");
    }
}
}

public static void disregardedMessage() {
    System.out.println("Enter message to disregard:");
    String msg = input.nextLine();
    disregardedMessages[disregardedCount++] = msg;
    System.out.println("Message Disregarded");
}

public static void storedMessagesMenu(){
    int option;
    do{
        System.out.println("\n==========STORED MESSAGE======");
        System.out.println("1. Display sender and recipient");
        System.out.println("2. Display longest message");
        System.out.println("3. Search by message ID");
        System.out.println("4. Search by recipient");
        System.out.println("5. Delete message by hash");
        System.out.println("6. Display full report");
        option = input.nextInt();
        input.nextLine();
      switch(option) {
          case 1:
              displayRecipient();
              break;
          case 2:
              displayLongestMessage();
              break;
          case 3:
              System.out.println("Enter Mesasge ID");
              searchByMessageID();
              break;
          case 4:
              System.out.println("Enter Message Recipient");
              searchByRecipient();
              break;
          case 5:
           displayFullReport();
          default:
        System.out.println("Select an option: ");
      }
    }while (option !=5);
 }
    

public static void displayRecipient() {
    if (sentMessagesCount==0) {
        System.out.println("No stored messages");
    }
    for (int i=0; i<sentMessagesCount; i++) {
        System.out.println("Recipient" + recipientSent[i]);
    }
}

public static void displayLongestMessage() {
  if (sentMessagesCount==0) {
      System.out.println("No stored Messages");
}
  int longest=0;
  for (int i=0; i< sentMessagesCount; i++) {
     if (storedMessages[i].length() > storedMessages[longest].length()) {
         longest = i;
     }
  }
    System.out.println("\nLongest Message");
    System.out.println("ID"+ messageIDs[longest]);
    System.out.println("Recipient" + recipientSent[longest]);
    System.out.println("Message" + storedMessages[longest]);
}

public static void deleteMessageByHash(String Hash) {
   for (int i=0; i<sentMessagesCount; i++) {
       if(messageHashes[i].equalsIgnoreCase(Hash)) {
           for(int j=i; j<sentMessagesCount; j++) {
               messageHashes[j] = messageHashes[j+1];
               messageIDs[j] = messageIDs[j+1];
              recipientSent[j] = recipientSent[j+1];
              storedMessages[j] = storedMessages[j+1];
           }
           sentMessagesCount --;
           System.out.println("Message deleted successfully");
       }
   }
}

public static void displayFullReport() {
    System.out.println("\n=======FULL REPORT====");
    for (int i=0; i<sentMessagesCount; i++) {
        System.out.println("\nID" + messageIDs[i]);
        System.out.println("\n Recipient" + recipientSent[i]);
        System.out.println("\n Message"+ storedMessages[i]);
        
    }
}

public String PrintMessages(String id, String hash, String recipient, String msg) {
    return "ID: " + id + "\nHash: " + hash + "\nRecipient: " + recipient + "\nMessage: " + msg;
}
}
