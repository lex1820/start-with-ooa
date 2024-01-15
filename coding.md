# Object Oriented Architectures & Secure Design

## How to start?

1. Read the exercise
2. Write down which classes will be needed
   1. If enough time, write fields & methods
   2. Check if you might need intefaces 
3. If FXML is needed & not provided via source materials, sketch it
4. Start a new project in InteliJ
   1. Language: **Java**
   2. Build system: **Gradle**
   3. JDK: **Zulu-17**
   4. Gradle DSL: **Groovy / Kotlin**
   5. GroupID: be.howest.ti
5. Let the project build
6. Add it to git 
   1. [Link to git](https://gitlab.ti.howest.be/ti/2023-2024/s3/object-oriented-architectures-and-secure-development/exam/01-first-session)
   2. Open git terminal and copy&paste code below to commit & push to git repo (CHANGE LINK TO YOUR LINK)
   ```
    cd existing_folder
    git init --initial-branch=main
    git remote add origin git@gitlab.ti.howest.be:ti/2023-2024/s3/object-oriented-architectures-and-secure-development/exam/01-first-session/lex.vankeerberghen.git
    git add .
    git commit -m "Initial commit"
    git push --set-upstream origin main
   ```
   3. Verify in Git webbrowser if project actually was pushed
7. Add the different layers to the project
   1. Data layer
   2. Domain layer
   3. Network layer
   4. Service layer
   5. Ui layer
   6. Util layer
8. Start coding in the **domain** layer
   1. Check with source materials if you already got some classes, if so add them
9. Fast check your created class via Unit test or in CLI 
10. If time enough, create a program in CLI in which you make sure everything works before adding the FXML stuff
11. If not enough time, Start immediatelly with FXML
12. Open build.gradle file & add the plugins & other stuff for FXML
13. in the resources, create new directory (fxml)
14. In this directory, add a new fxml file & design it OR add if given in the source materials
15. Create a controller in the ui > gui > controllers
16. Add the controller to the FXML file
17. Alt+enter on all the FX:id & onAction in the FXML file (will create public fields & methods in the controller)
18. Go to the contoller, change all the public fields to @FXML private & change the public methods to @FXML
19. Start writing code in controller --> we don't want logic in this class --> Create a GuiService class in the Service layer (create a field for the service)
20. In here you will create methods that contain the logic for the methods in the controller class
    1.  USE A LOGGER TO LOG errors or info for developpers 
    2.  Make a field for the logger `private static final Logger LOGGER = LOGGER.getLogger(CLASSNAME.class.getName());`
    3.  Call the logger `LOGGER.log(Level.INFO, "SOME MESSAGE", exception)` --> exception not necessary
21. While writing the methods you will probably need data from a repository (mock data, real data from a DB or from a file)
22. Therefore create in the data layer an interface called PROJECT-NAMERepository, create also normal classes MockDataRepository, MySqlRepository, make them implement PORJECTNAMERepository. 
    1.  When calling them use `private static final PROJECTNAMERepository REPO = new MockDataRepository()`
    2.  TEST FIRST WITH MOCKDATA
23. Create a connection with your mysql database
24. Add the necessary dependencies to build.gradle & reload gradle
    ```
    // JDBC (Connect with DB, MySQL)
    // https://mvnrepository.com/artifact/com.mysql/mysql-connector-j
    implementation 'com.mysql:mysql-connector-j:8.2.0'

    // CRYPTO (used for hashing passwords)
    // https://mvnrepository.com/artifact/org.springframework.security/spring-security-core
    implementation 'org.springframework.security:spring-security-core:6.2.1'
    ```
25. In the resources, create a directory "config"
26. In that directory, create a config.properties file, here you will store the DB credentials --> you will store them encrypted
    1.  ENCRYPT YOUR USERNAME & PASSWORD using this method, just copy & paste it from the test project
        1.  --> [EncryptCredentials.java](test-project/src/main/java/be/howest/ti/ui/cli/EncryptPassword.java)
27. Create in the Util layer a class Crypto --> copy from test project 
    1.  --> [Crypto class](test-project/src/main/java/be/howest/ti/util/Crypto.java)
28. Create a Config class --> copy from test project 
    1.  --> [Config class](test-project/src/main/java/be/howest/ti/util/Config.java)
29. Create MySqlConnection --> copy from test project
    1.  --> [MySQLConnection class](test-project/src/main/java/be/howest/ti/util/MySqlConnection.java)
30. Create a custom exception class --> copy from the test project --> Change name of class & methods
    1.  --> [Custom exception class](test-project/src/main/java/be/howest/ti/util/TestProjectException.java)
    2.  Use this custom exceptions where ever you need it, use it as much as possible


## Domain layer
> Used to create basic classes which you will need later on in the project
> Eg. [User.java](test-project/src/main/java/be/howest/ti/domain/User.java)


## Ui layer
> This is were you make the intefaces are created
>   - gui --> controllers & [Program.java](test-project/src/main/java/be/howest/ti/ui/gui/Program.java)
>   - cli --> [Main.java](test-project/src/main/java/be/howest/ti/ui/cli/Main.java)

### Controllers
> Controllers are the connections between the FXML files in the resources & the logic from the service classes

## Service layer
> Create a bridge between controller & data of the application
> [Service class of the GUI](test-project/src/main/java/be/howest/ti/service/GuiService.java)

## Data layer
> Creates objects (domain layer) from the data it gets from either mock data, a file or a real DB

> Create an instance of a Repository and implement it into the other classes

## Util layer
> Helper classes that can be used everywhere in your codebase

> - [MySqlConnection class](test-project/src/main/java/be/howest/ti/util/MySqlConnection.java)
>   - uses the crypto & config class from this layer to establish the connection to the MySQL DB
> - [Config class](test-project/src/main/java/be/howest/ti/util/Config.java)
>   - Uses the [config.properties file](test-project/src/main/resources/config/config.properties), in which the DB credentials are stored, but also other credentials can be stored inside here
> - [Crypto class](test-project/src/main/java/be/howest/ti/util/Crypto.java)
>   - Is used in the config class, to decrypt the DB credentials
>   - Used in the [EncryptCredentials class](test-project/src/main/java/be/howest/ti/ui/cli/EncryptCredentials.java), to encrypt the credentials and store the in the config.properties file
>   -  Used in the Data layer to hash passwords & check the hashed passwords

## Resources

### Config directory
#### Config.properties file
> Credentials of DB are stored in here

### fxml directory
> All .fxml files should be placed in here

### Resource bundle
> In the resource bundle you choose the languages so you can make the application multilingual  
> Instead of hardcoding all values of labels, prompts of textfields, store them in the resource bundles

## Unit tests
> Write tests for both CLI & GUI part of the application. (JUnit5)  
> [Test directory](test-project/src/test)

### How to write a UnitTest
> Use the learned AAA methodµ
> - Arrange
>   - Create the variables & objects
> - Act
>   - Execute the method that you want to test & store the result into a variable
> - Assert
>   - Check if the variable from the ACT part is the same as the the variable in the arrange part of this method