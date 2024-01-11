package be.howest.ti.ui.gui.controllers;

import be.howest.ti.domain.User;
import be.howest.ti.service.GuiService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HomeScreenController {

    /**
     * FIELDS
     * */
    private static final Logger LOGGER = Logger.getLogger(HomeScreenController.class.getName());
    @FXML
    private ListView<User> lstViewUsers;

    private static final GuiService SERVICE = new GuiService();

    /**
     * INITIALIZE METHOD
     * */
    public void initialize() {
        lstViewUsers.setItems(SERVICE.getUsers());
    }


    /**
     * METHODS
     * */
    @FXML void btnFetchUsers(ActionEvent actionEvent) {
        lstViewUsers.setItems(SERVICE.getUsers());
    }

    @FXML void btnCreateUser(ActionEvent actionEvent) {
        //CONNECTION TO OPEN SECOND SCREEN

        try{
            // Load the FXML file and create a stage for the popup.
            //TODO: Change path of fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CreateUser.fxml"));
            //TODO: CHECK IF THIS IS CORRECT, might be anchorpane instead of Vbox => comment one out depending on which one is correct
            AnchorPane root = (AnchorPane) loader.load();
            //VBox root = (VBox) loader.load();
            //HBox root = (HBox) loader.load();
            Stage stage = new Stage();
            //TODO: Change title
            stage.setTitle("Add new user");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));

            // Set the controller for second screen.
            //TODO: CHANGE CONTROLLER NAME
            CreateUserController controller = loader.getController();
            controller.setStage(stage);

            // Show the popup.
            stage.showAndWait();

        } catch (IOException e) {
            //TODO: CHANGE LOGGER & EXCEPTION TO YOUR OWN APPLICATION
            LOGGER.log(Level.SEVERE, "Couldn't open -add new user- screen", e);
            //Use alert to let user know something went wrong
            Alert alert = new Alert(Alert.AlertType.ERROR, "Screen can't be loaded", ButtonType.CLOSE);
            alert.showAndWait();

        }

    }
}
