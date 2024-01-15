package be.howest.ti.ui.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class Program extends Application {
    //This class needs to extend Application to be able to run the program in the GUI (JavaFX)
    //Only usable after adding the JavaFX library to the project (build.gradle)
    // If you want to run the program in the CLI, GO via the side panel of Gradle to Tasks -> application -> run

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        //Application takes the language of the computer only works when there is a default language file in the resources bundle
        //ResourceBundle bundle = ResourceBundle.getBundle("test-project");

        //Specify which language to use
        ResourceBundle bundle = ResourceBundle.getBundle("test-project", new Locale("en", "US"));

        //Change path to the correct path of the fxml file
        Parent root = FXMLLoader.load(
                Objects.requireNonNull(getClass().getResource("/fxml/HomeScreen.fxml")),
                bundle
        );
        Scene scene = new Scene(root);
        //Change title
        primaryStage.setTitle("User Management System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
