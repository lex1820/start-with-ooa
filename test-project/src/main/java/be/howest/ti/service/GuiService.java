package be.howest.ti.service;

import be.howest.ti.data.MySQLRepository;
import be.howest.ti.data.TestRepository;
import be.howest.ti.domain.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.logging.Logger;

public class GuiService {

    //This is the service class, it contains the methods that the GUI can use to interact with the data in the repo's

    /**
     * FIELDS
     * */
    private static final Logger LOGGER = Logger.getLogger(GuiService.class.getName());
    private static final TestRepository REPO = new MySQLRepository();



    /**
     * METHODS
     * */

    public ObservableList<User> getUsers() {
        //FXCollections.ObservableArrayList is a class that implements the ObservableList interface, this way it's not possible to change anything to the list without the GUI knowing about it
        //Call the getAllUsers method from the repository and pass it to the FXCollections.observableArrayList method
        return FXCollections.observableArrayList(REPO.getAllUsers());
    }


    public void addNewUser(String username, String password) {
        //Call the addUser method from the repository
        REPO.addUser(username, password);
    }

    public boolean isInputEmpty(String username, String password) {
        //Check if the username and password are empty
        return username.isEmpty() || password.isEmpty();
    }

    public boolean isUsernameTaken(String username) {
        //Check if the username is already taken
        return REPO.getAllUsers().stream().anyMatch(user -> user.getUsername().equals(username));
    }
}
