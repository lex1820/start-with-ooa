package be.howest.ti.data;

import be.howest.ti.domain.User;
import be.howest.ti.util.TestProjectException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileRepository implements TestRepository{
    /**
     * FIELDS
     * */
    private static final Logger LOGGER = Logger.getLogger(FileRepository.class.getName());
    private static final String FILE_PATH_TEXT = "./fileIO/users.txt";
    private static final String FILE_PATH_OBJECT = "./fileIO/user_object.txt";

    /**
     * METHODS
     * */
    //Write object to file --> object must be serializable (implement Serializable)
    @Override
    public void addUser(User user) {
        try(ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(FILE_PATH_OBJECT)
        )){
            oos.writeObject(user);
            LOGGER.log(Level.INFO, "User added to file");
        } catch (IOException ex){
            LOGGER.log(Level.SEVERE, "File was not found", ex);
            throw new TestProjectException("File not found", ex);
        }
    }

    //Write text to a file, append to file if it already exists
    @Override
    public void addUser(String username, String password) {
        try(PrintStream ps = new PrintStream(
                //true = append to file, false = overwrite file (default)
                new FileOutputStream(FILE_PATH_TEXT, true)
        )){
            ps.println(username + "\t" + password);
            LOGGER.log(Level.INFO, "User added to file");
        } catch (FileNotFoundException ex){
            LOGGER.log(Level.SEVERE, "File was not found", ex);
            throw new TestProjectException("File not found", ex);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try(Scanner scanner = new Scanner(
                new File(FILE_PATH_TEXT)
        )){
            while(scanner.hasNext()){
                String line = scanner.nextLine();
                String[] parts = line.split("\t");

                User user = new User(parts[0], parts[1]);

                users.add(user);
                LOGGER.log(Level.INFO, "User added to list: " + user.toString());
            }
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "File was not found", e);
            throw new TestProjectException("File not found", e);
        }

        return users;
    }

    @Override
    public User getUserByUsername(String username) {
        User user;
        try(Scanner scanner = new Scanner(
                new File(FILE_PATH_TEXT)
        )){
            while(scanner.hasNext()){
                String line = scanner.nextLine();
                String[] parts = line.split("\t");

                if(parts[0].equals(username)){
                    user = createUser(parts);
                    LOGGER.log(Level.INFO, "User found: " + user.toString());
                    return user;
                }
            }
            //User is not found
            LOGGER.log(Level.INFO, "User not found");
            throw new TestProjectException("User not found");

        } catch (FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "File was not found", e);
            throw new TestProjectException("File not found", e);
        }
    }

    /**
     * HELPER METHODS
     * */
    private User createUser(String[] parts) {
        return new User(parts[0], parts[1]);
    }


}
