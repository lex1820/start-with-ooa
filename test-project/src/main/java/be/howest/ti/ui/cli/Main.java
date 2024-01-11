package be.howest.ti.ui.cli;


import be.howest.ti.service.CliService;
import be.howest.ti.util.Crypto;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Use this main method to run the program in the CLI

        CliService SERVICE = new CliService();

        SERVICE.run();
        
    }
}