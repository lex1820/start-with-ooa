package be.howest.ti.service;

import be.howest.ti.data.FileRepository;
import be.howest.ti.data.MySQLRepository;
import be.howest.ti.data.TestRepository;
import be.howest.ti.domain.User;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CliService {
    /**
     * FIELDS
     * */
    private static final Logger LOGGER = Logger.getLogger(CliService.class.getName());
    private static final Scanner in = new Scanner(System.in);

    //REPO with data stored in FILE
    //private static final TestRepository REPO = new FileRepository();
    //REPO with data stored in MySQL DB
    private static final TestRepository REPO = new MySQLRepository();

    /**
     * METHODS
     * */
    public void run() {
        System.out.println("CLI Service started");

        //ask what user wants to do
        askUserWhatToDo();

        //Receive input from user
        int input = receiveUserInput();

        //Do something with the input
        useUserInput(input);
    }




    /**
     * HELPER METHODS
     * */
    private void askUserWhatToDo() {
        //Helper of run()
        System.out.println("Welcome to the test project:");
        System.out.println("What do you want to do?");
        System.out.println("1. Create a new user & store as text");
        System.out.println("2. Create a new user & store as Object");
        System.out.println("3. Get all users");
        System.out.println("4. Get a specific user by username");
    }
    private int receiveUserInput() {
        //Helper of run()
        System.out.println("Please enter a number:");
        int input = in.nextInt();
        //validate user input
        input = validateUserInput(input);

        return input;
    }
    private int validateUserInput(int input) {
        //Helper of receiveUserInput()
        while(input < 1 || input > 4) {
            System.out.println("Please enter a number between 1 and 4:");
            input = in.nextInt();
        }
        return input;
    }
    private void useUserInput(int input) {
        //Helper of run()
        switch(input) {
            case 1:
                System.out.println("You chose to create a new user and store it as text");
                addNewUserAsText();
                break;
            case 2:
                System.out.println("You chose to create a new user and store it as an object");
                addNewUserAsObject();
                break;
            case 3:
                System.out.println("You chose to get all users");
                getUsers();
                break;
            case 4:
                System.out.println("You chose to get a specific user by username");
                getUserByUsername();
                break;
        }
    }

    private void getUserByUsername() {
        //Helper of useUserInput()
        String username = getUsername();

        //Get user from REPO
        User user = REPO.getUserByUsername(username);

        //Print user
        System.out.println("User:");
        System.out.println(user);
    }

    private void getUsers() {
        //Helper of useUserInput()
        System.out.println("Talking to REPO to get all users");
        List<User> users = REPO.getAllUsers();

        //Print all users
        System.out.println("All users:");
        users.stream().forEach(System.out::println);
    }

    private void addNewUserAsObject() {
        //Helper of useUserInput()
        String username = getUsername();
        String password = getPassword();

        //Create user object
        User user = new User(username, password);

        //Store user in File
        LOGGER.log(Level.INFO, "Talking to REPO to add user");
        REPO.addUser(user);
    }

    private void addNewUserAsText() {
        //Helper of useUserInput()

        //ask for username => validate input
        String username = getUsername();
        String password = getPassword();

        //Store user in File
        LOGGER.log(Level.INFO, "Talking to REPO to add user");
        REPO.addUser(username, password);
    }

    private String getUsername() {
        //Helper of addNewUserAsText() & addNewUserAsObject()
        System.out.println("Please enter a username:");
        String username = in.nextLine();

        while(username.isEmpty()) {
            System.out.println("Please enter a username:");
            username = in.nextLine();
        }
        return username;
    }
    private String getPassword() {
        //Helper of addNewUserAsText() & addNewUserAsObject()
        System.out.println("Please enter a password:");
        String password = in.nextLine();
        while(password.isEmpty()) {
            System.out.println("Please enter a password:");
            password = in.nextLine();
        }
        return password;
    }
}
