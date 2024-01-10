package be.howest.ti.data;

import be.howest.ti.domain.User;

import java.util.List;

public interface TestRepository {

    //Write object to file
    void addUser(User user);
    //Write text to a file, append to file if it already exists
    void addUser(String username, String password);

    List<User> getAllUsers();

    User getUserByUsername(String username);
}
