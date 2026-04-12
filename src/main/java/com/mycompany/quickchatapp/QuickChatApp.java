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

String userName;
    String password;
    String cellNumber;
    String firstName;
    String lastName;
    
    boolean checkuserName(String userName) {
        if (userName.contains("_") && userName.length() <= 5) {
            System.out.println("Username successfully captured");
            return true;
        } else {
            System.out.println("Username is not correctly formatted; ensure it contains an underscore and is 5 characters or less.");
            return false;
        }
    }
    
    boolean checkpassword(String password) {
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



    public static void main(String[] args) {
        
    }
}
