package be.howest.ti.ui.cli;

import be.howest.ti.util.Crypto;

import java.util.Scanner;

public class EncryptCredentials {

    public static void main(String[] args) {
        //Use this main method to encrypt your password, copy it from the console & paste it in the config.properties file
        // => If you add it to that file don't forget to add the decrypt method in the MySQLConnection class

        // ENCRYPT YOUR PASSWORD HERE
        Scanner in = new Scanner(System.in);
        Crypto crypto = Crypto.getInstance();

        System.out.println("What's your db username?");
        String username = in.nextLine();

    
        System.out.println("What's your db password?");
        String password = in.nextLine();

        System.out.println("Encrypted username: " + crypto.encrypt(username));
        System.out.println("Encrypted password: " + crypto.encrypt(password));
    }
}
