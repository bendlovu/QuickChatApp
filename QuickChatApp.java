/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.quickchatapp;
import java.util.Scanner;




/**
 *
 * @author Ezinhle
 */
public class QuickChatApp {
Scanner input = new Scanner(System.in);

   public String userName;
   public String password;
   public String cellNumber;
   public String firstName;
   public String lastName;
    
     public boolean checkuserName(String userName) {
        if (userName.contains("_") && userName.length() <= 5) {
            System.out.println("Username successfully captured");
            return true;
        } else {
            System.out.println("Username is not correctly formatted; ensure it contains an underscore and is 5 characters or less.");
            return false;
        }
    }
    
   public boolean checkpassword(String password) {
        boolean hasCap = !password.equals(password.toLowerCase());
        boolean hasNum = password.matches(".*\\d.*");
        boolean hasSpec = password.matches(".*[!@#$%^&*()].*");
        
        if (password.length() >= 8 && hasCap && hasNum && hasSpec) {
            System.out.println("Password successfully captured");
            return true;
        } else {
            System.out.println("Password must be 8+ characters, with a capital letter, a number, and a special character.");
            return false;
        }
    }

     
  public  boolean checkcellNumber(String cellNumber) {
        if (cellNumber.startsWith("+27") && cellNumber.length() == 12) {
            return true;
        } else {
            System.out.println("Cellphone number must start with +27 and be 12 characters long.");
            return false;
        }
    }
    
    void registerUser() {
        System.out.println("\n===== REGISTER =====");
        
        // Fix for "nullnull": Capture names first
        System.out.print("Enter First Name: ");
        this.firstName = input.nextLine();
        
        System.out.print("Enter Last Name: ");
        this.lastName = input.nextLine();
        
         while (true) {
            System.out.print("Enter Username: ");
            String tempUser = input.nextLine();
            if (checkuserName(tempUser)) {
                this.userName = tempUser;
                break;
            }
        }
         
         while (true) {
            System.out.print("Enter Password: ");
            String tempPass = input.nextLine();
            if (checkpassword(tempPass)) {
                this.password = tempPass;
                break;
            }
        }
         
         
        while (true) {
            System.out.print("Enter CellPhone Number: ");
            String tempCell = input.nextLine();
            if (checkcellNumber(tempCell)) {
                this.cellNumber = tempCell;
                break;
            }
        }
     
        System.out.println("Registration Complete!");
    }
     
    void userLogin() {
       
        if (this.userName == null) {
            System.out.println("No user registered yet.");
            return;
        }

        System.out.println("\n===== LOGIN =====");
        System.out.print("Enter Username: ");
        String loginUser = input.nextLine(); 

        System.out.print("Enter Password: ");
        String loginPass = input.nextLine(); 
        
        if (loginUser.equals(this.userName) && loginPass.equals(this.password)) {
          System.out.println("\nWelcome " + firstName + " " + lastName + "! It is great to see you again.");
        } else {
            System.out.println("Username or password incorrect. Please try again.");
        }
     }
    public static void MessageSystem(){
        Scanner input = new Scanner(System.in);
        Message messageManager = new Message();
        
        System.out.println("\n ======QUICKCHAT MESSAGING SYSTEM=====");
        System.out.println("Enter number of message you want to send");
        int element = input.nextInt();
        input.nextLine();
        
        for (int i=0; i< element; i++) {
            System.out.println(" \n Entering Message" + (i + 1) + " of " + element + "---");
            
            System.out.println("Enter Recipient Phone Number (+27...): ");
            String phoneNumber = input.nextLine();
            
            System.out.println("Enter Message Text: ");
            String messageText = input.nextLine();
            
           String msgID = messageManager.getMessageID(); 
           if (messageManager.checkMessageID()) {
               System.out.println("Message ID Generated: " + msgID);
           } else{
               System.out.println("ID is too long");
           }
           
           String phoneResult = messageManager.checkRecipientCell(phoneNumber);
            System.out.println(phoneResult);
            
            String lengthStatus = messageManager.checkMessageLength(messageText);
            System.out.println(lengthStatus);
            
            String messageHash = messageManager.createMessageHash(msgID,messageText);
            String fullSummaryLine = messageManager.PrintMessages(msgID, messageHash, phoneNumber, messageText);
            
            System.out.println("\nChoose an option:");
            System.out.println("1) Send Message");
            System.out.println("2) Disregard Message");
            System.out.println("3) Store Message to send later");
            System.out.println(" Your Choice: ");
            int statusChoice = input.nextInt();
            input.nextLine();
            
            String finalActionStatus = messageManager.sentMessage(statusChoice, fullSummaryLine);
            System.out.println(finalActionStatus);
            
            System.out.println("\n ===MESSAGE SUMMARY===");
            System.out.println();
            System.out.println("========================");
        }
        
      for (int y=0; y<Message.totalMessages; y++) {
          System.out.println("Stored Record [" + y + "] ->" + Message.sentMessages[y]);
      }
    }


    public static void main(String[] args) {
         QuickChatApp loginapp = new QuickChatApp();
        Scanner menuScanner = new Scanner(System.in);
        String choice = "";

        
        while (!choice.equals("3")) { 
             System.out.println("\n====== MENU ======");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choice: ");
            
            choice = menuScanner.nextLine();
            
            switch (choice) {
                case "1":
                    loginapp.registerUser();
                    break;
                     case "2":
                    loginapp.userLogin();
                    break;
                    case "3":
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
        menuScanner.close();
    }
}
      
