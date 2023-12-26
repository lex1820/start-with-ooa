package be.howest.ti.ui.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Program extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        //Change path to the correct path of the fxml file
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/Shop.fxml")));
        Scene scene = new Scene(root);
        //Change title
        primaryStage.setTitle("Shop");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
