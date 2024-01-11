package be.howest.ti.ui.gui.controllers;

import be.howest.ti.service.GuiService;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXML;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreateUserController {
    //This is the controller class
    //It is the connection between the FXML file and the actual Java code (the service class)
    // --> Each FXML file has a controller file, but all controller files use the same service class

    /**
     * FIELDS
     * */
    private static final GuiService SERVICE = new GuiService();
    private static final Logger LOGGER = Logger.getLogger(CreateUserController.class.getName());

    @FXML private Label lblError;
    @FXML private TextField txtFieldUsername;
    @FXML private TextField txtFieldPassword;

    //FIELD FOR CONNECTION TO OPEN SECOND SCREEN
    private Stage stage;

    /**
     * METHODS
     * */
    @FXML void btnAddUser(ActionEvent actionEvent) {
        String username = txtFieldUsername.getText();
        String password = txtFieldPassword.getText();

        List<String> validatedInput = validateInput(username, password);

        if(validatedInput != null){
            SERVICE.addNewUser(validatedInput.get(0), validatedInput.get(1));
            LOGGER.log(Level.INFO, "User added");
            stage.close();
        }
    }



    //METHOD FOR CONNECTION TO OPEN SECOND SCREEN
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * HELPER METHODS
     * */
    private List<String> validateInput(String username, String password) {
        //CHECK IF INPUT IS EMPTY
        if (SERVICE.isInputEmpty(username, password)){
            LOGGER.log(Level.WARNING, "Empty input");
            clearInput();
            lblError.setText("Please fill in all fields");
            return null;
        }

        //CHECK IF USERNAME ALREADY EXISTS
        if(SERVICE.isUsernameTaken(username)){
            LOGGER.log(Level.WARNING, "Username already exists");
            clearInput();
            lblError.setText("Username already exists");
            return null;
        }

        //Everything is OK => return validated input as List
        return List.of(username, password);
    }

    private void clearInput() {
        txtFieldUsername.clear();
        txtFieldPassword.clear();
    }
}
