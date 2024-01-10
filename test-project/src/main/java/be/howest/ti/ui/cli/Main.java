package be.howest.ti.ui.cli;


import be.howest.ti.service.CliService;

public class Main {
    public static void main(String[] args) {

        CliService SERVICE = new CliService();

        SERVICE.run();
        
    }
}