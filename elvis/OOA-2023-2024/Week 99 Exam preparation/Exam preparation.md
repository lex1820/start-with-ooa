# Exam preparation

Created: November 20, 2023 12:31 PM
Reviewed: Yes

1. Create proper file structure
2. Add util classes

# Exam

1. You need to know your technicals:
   1. Know your read write files IO
   2. Know your server connection / sockets
   3. Know your JavaFX …
   4. Be able to make connection to
      1. database
      2. in memory
      3. in file
      4. the network
   5. And use the correct `try-with-resources` blocks when needing automatic closure for file streams, database connections, or network connections to free system resources (connection slots, file handlers, …)
2. Put the code where it should be put!
   1. Everything that has to do with the user inter face → userinterface layer
   2. with data and data persistency → data layer
   3. write service/domain or combination of both to separate the two above!
   4. WHY? → clean code, common practice, allows you to easily change your datastorage or other things are changed very easily

### Tips

- have your helper functions ready somewhere (connection with database, config, … )
- Never ever give sensitive data to your user but just log it.
- When you need to iterate over a list → use .stream as much as possible
- **FXML files**
  - → there will be the viewboxes, buttons, listviews etc, but there will not be the ‘fx:controller= …’, ‘onAction=…’ & ‘fx:id= …’.
  - you don’t have to do design
  - The controller ‘does it’ and the guiManager ‘says what it should be’
- You get the select statements on the exam for the database
- Abstract class is a regular class designed to be subclassed, an abstract method will be implemented in the subclass
- If the assignment doesn’t say you need to do things → DON’T!
  - putting sensitive information in .env file, encrypt or decrypt it, making custom exception, …
  - If it does → EXTRA POINTS
- If you are stuck on something → SKIP IT FOR NOW & work on something else
- Don’t worry about <combobox elements> → they don’t ask for perfect software → worry about this the last 5 minutes
- If you are running out of time:
  - Check what you’ve shown you can, what do i still need to do? → choose the thing you know about and make it quickly (easy points)

### Start with exam

1. Add dependencies
   1. gradle mysql database dependency
   2. javaFX dependencies
2. Create proper file structure
3. Add util classes
4. Create javafx startup class that extends application
   1. primarystage → set new scene
   2. fxml load → get class
   3. show primaryStage
5. Controller & FXML

   1. Add `fx:controller=”pathToController”` to fxml file & add id’s to the elements that need an id in your fxml file
   2. Create controller & add every element with an id as a private field in controller with `@FXML`
   3. Initialize controller

      ![Untitled](/OOA-2023-2024/Week%2099%20Exam%20preparation/Untitled.png)

6. Create user interface → GUI manager
7. Check what entity you need to get out of the database → create a class + a enitityDatabaseRepository class for this entity
8. Make database connection
   1. with an user with as few privileges as possible! → you need to make screenshot on exam
   2. put the connection in a try-with-resources for ddos security
   3. Don’t give sensitive errors to the user, keep it as simple as possible without too much info. Do log the errors to developper
   4. (if assignment asks) custom exceptions
9. Create service classes (middleman) to get the ‘entities’ from the enitityDatabaseRepository to the GUI manager

   1. Add these service classes to you GUI Manager. bv: `private final 'entityService service = new 'entitiy'Service()`
   2. Use them like this:

      ![Untitled](/OOA-2023-2024/Week%2099%20Exam%20preparation/Untitled%201.png)

10. Do all the logic of your application
11. Be sure to create one good test for application→ AAA (act, … , assert) → test database connection is a good one

### Mock-exam 2022

How do you start?

- Analyse everything → what’s given and what do we want

Server/sockets

- extra package network with class ServerConnection
- you make a connection with try-with-resources

  ```java
  public void(Message message){
  	try (
  		Socket socket = new Socket("localhost", port: 12345);
  		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
  		Scanner in = new Scanner(socket.getInputStream());
  	)

  	{
  out.printf("what you need to send");
  	}
  ```
