package be.howest.ti.data;

import be.howest.ti.domain.User;

import java.util.List;

public interface TestRepository {
    //This is the interface of the different repositories, it contains the methods that all repositories should implement
    //How to use any repository that implements this interface:
    //TestRepository repo = new MySQLRepository();
    //TestRepository repo = new FileRepository();

    //Write object to file
    void addUser(User user);
    //Write text to a file, append to file if it already exists
    void addUser(String username, String password);

    List<User> getAllUsers();

    User getUserByUsername(String username);
}
