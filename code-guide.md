# Coding guide Java

## Table of Contents
- [Coding guide Java](#coding-guide-java)
  - [Table of Contents](#table-of-contents)
  - [How to start programming?](#how-to-start-programming)
  - [Data layer](#data-layer)
    - [Database](#database)
    - [Mock data class](#mock-data-class)
    - [MySQL](#mysql)
  - [Domain layer](#domain-layer)
  - [Network layer](#network-layer)
  - [Service layer](#service-layer)
    - [Service class](#service-class)
  - [UI layer](#ui-layer)
    - [Controller class](#controller-class)
  - [Util layer](#util-layer)
    - [Custom exceptions](#custom-exceptions)
    - [Config utility class](#config-utility-class)
    - [Crypto utility class](#crypto-utility-class)
  - [Logger](#logger)
    - [Logger levels](#logger-levels)
  - [FXML](#fxml)
    - [JavaFX](#javafx)
  - [Unit testing](#unit-testing)
    - [Arrange](#arrange)
    - [Act](#act)
    - [Assert](#assert)
    - [Example](#example)

## How to start programming?

1. Open InteliJ 
2. Start a new project
   1. Language: **Java**
   2. Build system: **Gradle**
   3. JDK: **Zulu-17**
   4. Gradle DSL: **Groovy / Kotlin**
3. Read exercise
4. Check the source materials for which classes/data you already get
5. Write on paper which classes & fields will be needed
6. Create all necessary layers  
    1. [Data layer](#data-layer)  
    2. [Domain layer](#domain-layer)
    3. [Network layer](#network-layer)  
    4. [Service layer](#service-layer)  
    5. [UI layer](#ui-layer)  
    6. [Util layer](#util-layer)  
7. Start with writing the classes in the **domain layer** -> if there are classes from the **source materials**, use them!
8. Test your classes in the cli (**UI layer > cli**) with mockdata
   1. Create [**mock data**](#mock-data-class)
9. Write tests along the coding road, [Unit testing](#unit-testing)
10. Don't forget to make use of the [**Logger**](#logger)
11. Create a directory 'fxml' in the resources => [**FXML**](#fxml)
12. Go on to the controller class
13. After controller, go further with the service layer
14. Service layer connects to domain/data layer
15. To make sure it is easy to change the code when eg. db name changes make sure you use [Configuration files](#configuration-files)
16. 

## Data layer
> Contains everything regarding communication to the DB  
> Contains mock data for testing of the project

### Database
1. Create a new **schema** in **datagrip**
   - `create schema <SCHEMA_NAME>`
2. use this schema
   - `use <SCHEMA_NAME>`
3. Create the necessary **tables & columns**
4. Add some mock data
5. Create a new user with minimal rights

### Mock data class
> Contains all the mock data for testing of the project  
> Has a mockrepository

### MySQL
> Check this link for up-to-date information [mvnrepository](https://mvnrepository.com/artifact/com.mysql/mysql-connector-j)

>1.  Add dependencies to `**build.gradle**` file  
`// https://mvnrepository.com/artifact/com.mysql/mysql-connector-j` 
`implementation 'com.mysql:mysql-connector-j:8.2.0'`

>2. Create a Connection instance (**try-with-resources-block**)
>       - To easily make this accessible, create a separate class, [MySQLConnection Class](/usefull-files/MySQLConnection.java)

>3. Execute a query  
>       - Select statement with(out) parameters [select-query ](/usefull-files/execute-select-statement.java)
>       - Insert statement [INSERT-query](/usefull-files/execute-insert-statement.java)

> **IMPORTANT!!**
>   - Never store the connection url, username & password in the mysqlConnection class => store them in the resources in a **`.properties file`**
>   -  Store them encrypted in the config files 

## Domain layer
> Contains all the classes that are used in the application  
> Can contain enums, abstract classes, extended classes, ...
> Use the [Logger](#logger)

## Network layer
> This layer is used to send & receive messages to the server

## Service layer
> Connects controllers to the domain layer, making sure the UI layer can't change lists, make sure everything you return is unmodifiable

### Service class
> - Contains all the methods to connect the controller with the domain layer.
> - Make sure each method returns an unmodifiable result, otherwhise it's not securely designed

## UI layer

> Split into 2 layers:  
1. CLI layer -> Main.java
   - Make sure domain layer classes work in cli first
2. GUI layer -> Program.java
   - Has method to start FXML 
   - Extends application
   - has a directory for the **controllers**

> **Controllers** => connection between FXML file in the resources & the java code

### Controller class 
1. Add the elements which have a **`FX:id`** in the FXML file as **fields** in the controller class
2. Add the elements which have **`OnAction:"#<SOME_NAME>"`** from the FXML file as methods in the controller class
3. Instead of keeping these fields & methods public, which is not secure architecture
    > - change the **`public`** to **`@FXML private`** 
4. If you need certain fields filled in when starting up the application
    > - Create **`Initialize`** method
    > - Add **`@FXML`** before the method 
5. Create a [**logger**](#logger) to the file to log important stuff, this way the users can't access the logs & you're able to log more important information
6. Start writing code for the methods  
   - -> When you need information from the code, database, ... 
     - -> Get it from the [**service class**](#service-class) in the service Layer
     - `private static final <NAME_OF_SERVICE_CLASS> service = new <NAME_OF_SERVICE_CLASS>();`


## Util layer
> Contains all utility classes  
> Can be for exceptions, config, encryption/decryption, ...

### Custom exceptions
> Custom exceptions are needed so you can show the user specifically what's he doing wrong & specify it for your application  
> Custom exception class ALWAYS has to `extends RuntimeException`  
> Check examples here: [custom excpetions.java file](/usefull-files/custom%20exceptions.java)

### Config utility class
> This file loads, reads & writes the the configurations for the application (eg. `.properties files`, ...)
> Check this [Config.java file](/usefull-files/config.java)

### Crypto utility class
> Need to add to build.gradle file, add to the dependencies block:  
> `implementation 'org.springframework.security:spring-security-crypto:6.1.4'`  
>
> Check this [crypto.java file](/usefull-files/crypto.java)

## Logger
> Used for logging data/errors/messages, the user can't access these logs but the developpers can

1. Create a logger in a file
   > `private static final Logger LOGGER = Logger.getLogger(<CLASS_NAME>.class.getName());`
2. Use the logger
   > `LOGGER.log(Level.<LEVEL>, <MESSAGE>)`  
   > `LOGGER.log(Level.<LEVEL>, <MESSAGE>, <THROWABLE>)`

throwable can be a build-in exception or a [**custom exception**](#custom-exceptions)

### Logger levels
- SEVERE
- WARNING
- INFO
- CONFIG
- FINE
- FINER
- FINEST

## FXML
> FXML is a XML based language for defining user interfaces in JavaFX.

To use FXML in a Java project => [JavaFX](#javafx)

### JavaFX
1. Go to the resources of the project
2. Add a directory called 'fxml'
3. Add FXML file in the directory  
   -> **New** FXML file OR FXML file from source materials
4. Add the necessary dependencies to the `build.gradle` file
   - [build.gradle file for JavaFX](../usefull-files/build.gradle.md)
   > - Check the path in the application block!
   > - Check newest version of JavaFX via link in the build.gradle file
5. Reload Gradle & wait
6. Go further depending on a new file or an existing FXML file
   1. **New** FXML file
     - Write your FXML code, you can either write it yourself or use (the build-in) scenebuilder
   2. FXML file from source materials
7. Create a controller class in the [UI layer](#ui-layer)
8. Connect the controller to the FXML file
    - In the top line (mostly Vbox, Hbox, AnchorPane, ...) add it in between the brackets `<Hbox>`
    > - `fx:controller="be.howest.ti.ui.gui.controllers.<NAME_OF_CONTROLLER_CLASS>"`
9. Go further in the [Controller class](#controller-class)

## Unit testing

**4 pillars of a good unit test:**
1. Protection against regressions
2. Resistance to refactoring
3. Fast feedback
4. Maintainability

**AAA-patern**
### Arrange
> bring the SUT (System Under Test) & it's dependencies to **desired state**

### Act
> Call methods on SUT, pass prepared dependecies & capture output value

### Assert
> Verify the outcome

### Example
> Check this [java file](/usefull-files/unit-test.java) to see an example of a unit test