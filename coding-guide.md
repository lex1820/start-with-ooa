# Coding guide for a Java project

Project: 
- We will create a small project in which we can create users.  
- To make it more visual, we will use JavaFX to create a GUI.
- We will use a MySQL DB to store the users


## Table of Contents



## Setup of the project:
- **Language:** Java
- **Build-system:** Gradle
- **JDK:** zulu-17
- **Gradle DSL:** Groovy
- **GroupId:** be.howest.ti

## Start the project:

1. Read the exercise 
   1. Think of which classes will be needed
      1. Think which fields each of these classes will need
      2. Think what should be in the constructor
      3. Think of which methods you will need in the class
   2. Do we need FXML?
   3. Do we need a DB?
   4. Do we need a client-server connection?
2. Start with creating the different layers of the project
   1. Data layer
   2. Domain layer
   3. Network layer
   4. Service layer
   5. UI layer
      1. cli
      2. gui
         1. controllers
   6. util layer
3. Start in the domain layer
   1. Create the classes that will be needed
   2. If needed create interfaces (eg. person is an interface for student & teacher, they have most of their fields the same)
4. Start the project in the CLI layer
   1. Create a simple psvm method which calls the run-method in the service layer
5. Start the project in the service layer
   1. Make a simple CLI application & make sure every method is covered
6. Data is needed fot the service layer
   1. Start with making a mockdata repository (can be stored to a file or just mockdata inside this repo)
   2. If all of this works start working with JDBC