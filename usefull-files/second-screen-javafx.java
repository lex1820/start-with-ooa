
//THIS IS THE METHOD TO ADD TO THE CONTROLLER OF THE FIRST SCREEN
private void nextScreen(){
    //Open new screen
    try {
        // Load the FXML file and create a stage for the popup.
        //TODO: Change path of fxml file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MovieReview.fxml"));
        //TODO: CHECK IF THIS IS CORRECT, might be anchorpane instead of Vbox
        VBox root = (VBox) loader.load();
        Stage stage = new Stage();
        //TODO: Change title
        stage.setTitle("Movie Base");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));

        // Set the controller for second screen.
        //TODO: CHANGE CONTROLLER NAME
        MovieReviewController controller = loader.getController();
        controller.setStage(stage);

        // Show the popup.
        stage.showAndWait();

    } catch (IOException e) {
        //TODO: CHANGE LOGGER & EXCEPTION TO YOUR OWN APPLICATION
        LOGGER.log(Level.SEVERE, "Couldn't open movie review screen", e);
        throw new MovieException("Can't open movie review screen");
    }
}

//THIS IS THE METHOD TO ADD TO THE CONTROLLER OF THE SECOND SCREEN

//ADD a new field to the 2nd controller
private Stage stage;

//Add this method to the 2nd controller, it's called by the nextscreenmethod in the first controller
public void setStage(Stage stage) {
    this.stage = stage;
}