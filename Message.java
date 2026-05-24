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
    static int totalMessages = 0;
    
    private String messageID;
    private String recipientCell;
    private String message;
    
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
            return "Press 0 to delete the message";
            
        case 3:
            return "Message successfully stored"; 
            
        default:
            return "invalid option selected";
    }
}
public String PrintMessages(String id, String hash, String recipient, String msg) {
    return "ID: " + id + "\nHash: " + hash + "\nRecipient: " + recipient + "\nMessage: " + msg;
}
}

