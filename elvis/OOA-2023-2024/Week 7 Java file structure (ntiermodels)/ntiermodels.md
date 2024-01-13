# Java file structure

![ntiermodel-structure](</OOA-2023-2024/Week%207%20Java%20file%20structure%20(ntiermodels)/ntiermodel-structure.png>)

## The the data layer:

- is responsible for the communication with the data source (MySQL, server, file, …)
- contains the repositories for data you are going to store persistently
- contians the mock repositories

## The the domain layer:

- is made-up of POJO’s (Plain Old Java Objects), representing entities from the datasource
- contains the classes where the program is about

## The the service layer

- contains non-UI logic
- sits betwen teh data layer and the UI
- is for all the ‘services’ a class does for another class (in this case authenticating users, creating contacts, … )

## The the user interface (UI) layer

- needs a cli to interact with the user using the CLI
- needs a gui to interact with the user using a GUI
- are controllers for the FXML files (JavaFX)

## In the util layer

- we want a custom exception class
- we want a crypto class to encrypt and decrypt the credentials of the DB connection
- we want a config class to make the connection between the configuration file in the resources

## In the resources

- We want a config file to store the credentials of the DB connection
- We want a FXML folder with all the fxml files for the GUI
