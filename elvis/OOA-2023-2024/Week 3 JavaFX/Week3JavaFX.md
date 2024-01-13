# Week 3: JavaFX

Created: November 2, 2023 9:38 AM
Materials: Week%203%20JavaFX%200f0a8cf5dbe046ca84dbeaf4ff5f1e71/03-01_JavaFX.pdf
Reviewed: Yes
Homework: Week%203%20JavaFX%200f0a8cf5dbe046ca84dbeaf4ff5f1e71/Assignment_week_3-2.pdf

# JavaFX

## What is JavaFX?

**JavaFX** is a Java library used to build Rich Internet Applications. The applications built using JavaFX can run across multiple platforms.

The key features of JavaFX include:

- **UI Controls**: A wide range of customizable UI elements.
- **Scene Graph**: Efficient rendering and flexible UI hierarchy.
- **CSS Styling**: UI customization using CSS.
- **Media Support**: Integration of audio and video.
- **Animation**: Smooth transitions and effects.
- **FXML**: Declarative UI design.
- **Integration**: Compatibility with Swing.
- **Platform Independence**: Runs on Windows, macOS, Linux.
- **Open Source**: Part of the OpenJFX project.

**Use Cases**: Business apps, games, multimedia, and more.

## JavaFX Hello World

A simple JavaFX application starts with a class that extends `Application` and overrides the `start` method. Here's the "Hello World" example:

```java
public class HelloApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Label lbl = new Label("Hello World!");
        Scene scene = new Scene(lbl, 200, 100);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

```

## Adding JavaFX Dependencies

For a Gradle project, JavaFX dependencies are added in the `build.gradle.kts` file:

```java
// code from personal computer
plugins {
    id("java")
    id("application")
    id("org.openjfx.javafxplugin") version "0.1.0"
}

javafx {
    version = "21"
    modules("javafx.controls")
}
```

_For the latest versions: [https://openjfx.io/openjfx-docs/#gradle](https://openjfx.io/openjfx-docs/#gradle)_

## Running JavaFX Applications

When running a JavaFX application from an IDE like IntelliJ, it's **important not to launch it directly** as it can cause errors.

```java
> Task :HelloApp.main() FAILED
Error: JavaFX runtime components are missing, and are required to run this application

FAILURE: Build failed with an exception.

* What went wrong:
Execution failed for task ':HelloApp.main()'.
> Process 'command '/Users/fredericvlummens/Library/Java/JavaVirtualMachines/azul-
11.0.8/Contents/Home/bin/java'' finished with non-zero exit value 1
```

Instead, configure the `build.gradle.kts` file to specify the main class:

```groovy
// code from personal computer
plugins {
    id("java")
    id("application")
    id("org.openjfx.javafxplugin") version "0.1.0"
}

application {
    mainClass.set("org.be.ti.howest.ui.gui.**FxProgram**");
}
```

Adding the application plugin, allows you specify a main class, And it **creates a run task**. (double click it to start the app)

![Untitled](/OOA-2023-2024/Week%203%20JavaFX%20/Untitled.png)

## Building an Actual UI with FXML

FXML is used to define the user interface of a JavaFX application. Make sure to add the necessary dependency to `build.gradle.kts`

```java
javafx {
    version = "21"
    modules("javafx.controls"**, "javafx.fxml"**)
}
```

Stored in `/resources/fxml` _(=convention),_ an FXML file might look like this:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<?import javafx.scene.control.Button?>
<?import javafx.scene.control.Label?>
<?import javafx.scene.layout.VBox?>
<VBox maxHeight="-Infinity" maxWidth="-Infinity"
minHeight="-Infinity" minWidth="-Infinity"
prefHeight="100.0" prefWidth="200.0"
xmlns="<http://javafx.com/javafx/18>">
    <Label>Hello</Label>
    <Label>World</Label>
    <Button>Do nothing</Button>
</VBox>

```

## SceneBuilder

SceneBuilder is a tool that simplifies the creation of FXML files. It allows for a drag-and-drop interface to design the UI.

_Free download: [https://gluonhq.com/products/scene-builder/](https://gluonhq.com/products/scene-builder/)_

### Creating an FXML file with SceneBuilder

![Untitled](/OOA-2023-2024/Week%203%20JavaFX%20/Untitled%201.png)

![Untitled](/OOA-2023-2024/Week%203%20JavaFX%20/Untitled%202.png)

## Loading FXML in Application

To load an FXML file in the JavaFX application, use the `FXMLLoader`:

```java
public class FxApplication extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
      Parent root = **FXMLLoader.load**(getClass().getResource("/fxml/demo.fxl"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
```

## The FXML file, with controller

```xml
<?xml version="1.0" encoding="UTF-8"?>

<?import javafx.scene.control.Button?>
<?import javafx.scene.control.Label?>
<?import javafx.scene.layout.VBox?>

<VBox maxHeight="-Infinity" maxWidth="-Infinity" minHeight="-Infinity" minWidth="-Infinity"
prefHeight="100.0" prefWidth="200.0"
	xmlns="http://javafx.com/javafx/18"
	xmlns:fx="http://javafx.com/fxml/1"
	**fx:controller="be.howest.ti.shop.ui.fx.DemoController">**
	<Label>Hello</Label>
	<Label fx:id="lblWord">World</Label>
	<Button onAction="#doSomething">Do something</Button>
</VBox>
```

## The Controller

Controllers are used to handle user interaction with the UI elements defined in FXML:

```java
public class DemoController {
    @FXML private Label lblWord;

    public void doSomething(ActionEvent actionEvent) {
        lblWord.setText("user interaction");
    }
}

```

The controls in the the fxml-file need an `fx:id` , if you want to access them as a **field** in the controller. Use the `@FXML` annotation, to make your fields private in the controller.

Some controls allow you to specify a handler **method** in the controller, using the #-symbol. Buttons, for instance, have the onAction-property.

Usually, you do not need to access the button itself, then you should not provide it with an `fx:id`. In case you do want to access the button, then you need the `fx:id` of course, but only add it if needed.

## Project Structure and Data Passing

The lesson also covers how to structure a project and pass data between screens, using controllers and FXML loaders.

```xml
@FXML private ListView<String> someLinesOfText;

public void doSomething(ActionEvent actionEvent) {
	lblWord.setText("user interaction");

	List<String> myClassicList = new ArrayList<>();
	myClassicList.add("a");
	myClassicList.add("b");
	myClassicList.add("c");

	someLinesOfText.setItems(FXCollections.observableList(
			myClassicList
	));
```

## Second Screen

In JavaFX, creating a second screen involves opening a new window, which is represented by a new `Stage`. This can be useful for showing additional information or for navigating to different views within the application.

### Opening a Second Screen

To open a second screen, you typically listen for an event (like a mouse click) and then execute a method to create and display the new stage. Here's an example of how to open a second screen when an item in a `ListView` is clicked:

```java
listView.setOnMouseClicked(event -> {
    String selectedItem = listView.getSelectionModel().getSelectedItem();
    if (selectedItem != null) {
        openSecondScreen(selectedItem);
    }
});

private void openSecondScreen(String selectedItem) {
    Stage secondStage = new Stage();
    secondStage.setTitle("Second Screen");
    StackPane secondScreenLayout = new StackPane();
    secondScreenLayout.getChildren().add(new Label("Selected Item: " + selectedItem));
    Scene secondScreenScene = new Scene(secondScreenLayout, 300, 150);
    secondStage.setScene(secondScreenScene);
    secondStage.show();
}

```

### Passing Data to Second Screen

When opening a new window, you often want to pass some information to that window's controller. However, using the traditional `FXMLLoader.load()` method doesn't allow for easy access to the controller instance to pass data. To overcome this, you can use one of two techniques:

1. **Ask the FXMLLoader to give you the controller it created**:

   ```java
   FXMLLoader loader = new FXMLLoader(getClass().getResource("/path/to/some/file.fxml"));
   Parent parent = loader.load(); // Load FXML first
   SomeDedicatedController controller = loader.getController(); // Then retrieve controller
   controller.doWhateverIsNeeded(extraData); // Pass data

   ```

2. **Create a controller yourself and ask the FXMLLoader to use that one**:

   ```java
   FXMLLoader loader = new FXMLLoader(getClass().getResource("/path/to/some/file.fxml"));
   SomeDedicatedController controller = new SomeDedicatedController(initData);
   loader.setController(controller); // Create and set the controller
   Parent parent = loader.load(); // Then load the FXML
   controller.doWhateverIsNeeded(extraData); // Pass extra data

   ```

### Stage and Scene Management

A `Stage` in JavaFX represents a **window**, and a `Scene` holds the content inside the stage. You can manage stages and scenes to show and hide windows, or to transition between different views.

- **Showing a Stage**: `stage.show()` makes the stage visible.
- **Waiting for a Stage**: `stage.showAndWait()` shows the stage and waits until it is closed before continuing.
- **Closing a Stage**: `stage.close()` closes the stage.

You can access the current stage from any control within the scene graph:

```java
Stage currentStage = (Stage) anyElement.getScene().getWindow();
```

### Embedding FXML in Existing Containers

Instead of always creating a new stage for your FXML views, you can also embed them into existing containers within the current scene. This is useful for updating parts of your UI without opening a new window:

```java
FXMLLoader loader = new FXMLLoader(ChatListController.class.getResource("/fxml/ChatListEntryView.fxml"));
Parent node = loader.load();
someVBox.getChildren().add(node);

```

## Internationalization (i18n)

JavaFX supports internationalization, which allows applications to adapt to different languages and regions without code changes.

### Resource Bundles and Locale-class

Resource bundles are used to store locale-specific resources, such as strings and formatting data. You can switch between different locales to change the language and regional settings displayed by the application.

### Example

Certainly! Internationalization in JavaFX is typically handled using resource bundles. These are `.properties` files that contain locale-specific strings. You can have multiple resource bundles for different locales, and JavaFX will choose the appropriate one based on the user's locale settings.

Here's an example of how you might set up and use resource bundles for internationalization in a JavaFX application:

### Step 1: Create Resource Bundle Files

Create properties files for each locale you want to support. For example, if you want to support English and French, you would create two files:

`MessagesBundle_en_US.properties` for English:

```
greeting = Hello
farewell = Goodbye
```

`MessagesBundle_fr_FR.properties` for French:

```
greeting = Bonjour
farewell = Au revoir
```

Place these files in the same package as your JavaFX application code.

### Step 2: Load the Resource Bundle

In your JavaFX application, you can load the appropriate resource bundle based on the user's locale like this:

```java
import java.util.Locale;
import java.util.ResourceBundle;

// ...

Locale locale = new Locale("en", "US");
ResourceBundle bundle = ResourceBundle.getBundle("MessagesBundle", locale);

// ...

// Now you can use the bundle to get localized strings
String greeting = bundle.getString("greeting");
String farewell = bundle.getString("farewell");

```

### Step 3: Use the Resource Bundle in FXML

You can also use resource bundles directly in your FXML files. Here's how you might reference the localized strings in an FXML file:

```xml
<?import javafx.scene.control.Label?>
<?import javafx.scene.layout.VBox?>

<VBox fx:controller="your.package.YourController"
      xmlns:fx="<http://javafx.com/fxml>"
      resources="#bundle">
    <Label text="%greeting"/>
    <Label text="%farewell"/>
</VBox>

```

In the controller for your FXML file, you would load the resource bundle and set it on the `FXMLLoader`:

```java
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

// ...

FXMLLoader loader = new FXMLLoader(getClass().getResource("YourView.fxml"));
loader.setResources(ResourceBundle.getBundle("MessagesBundle", locale));
Parent root = loader.load();

```

When you run this application, JavaFX will automatically use the correct strings from the resource bundle based on the specified locale. If you change the locale to French (`fr_FR`), the labels in your FXML file will display "Bonjour" and "Au revoir" instead of "Hello" and "Goodbye".

This approach allows you to easily adapt your application for different languages and regions without changing your codebase. You only need to provide the appropriate resource bundle files for each locale you want to support.

## Alert Dialogs

Alert dialogs are used to inform the user of events or to capture user input.

### Showing an Alert Dialog

Here's an example of how to display an alert dialog in response to an event, such as an error in user input:

```java
@FXML
void doAdd(ActionEvent event) {
    try {
        // ... attempt to add a product
    } catch (NumberFormatException ex) {
        Alert al = new Alert(Alert.AlertType.ERROR, "Invalid price specified.", ButtonType.CLOSE);
        al.showAndWait();
    } catch (ProductsException ex) {
        **Alert al = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.CLOSE);
        al.showAndWait();**
    }
}
// You can choose here wich one you want
```

## Common Errors and Solutions

### LoadException

These exceptions often occur due to issues with loading FXML files or incorrect paths to resources. Solutions involve ensuring the correct path is specified and that the FXML files are properly structured.

```xml
- javafx.fxml.LoadException: Root hasn't been set. Use method setRoot() before load.
- Cause: sometimes SceneBuilder creates the following FXML:
	<fx:root ..... type="VBox" ...>
	</fx:root>
- Solution: replace by:
	<VBox ... >
	</VBox>
```

### NullPointerException: Location is required

![Untitled](/OOA-2023-2024/Week%203%20JavaFX%20/Untitled%203.png)

### Controller Definition Errors

Errors related to controller definitions in FXML files can cause the application to fail at runtime. Solutions include checking for typos, ensuring the controller class exists, and that it is properly referenced in the FXML file.

- `Java.lang.ClassNotFoundException`
  - Typo or non-existing controller class specified
- `javafx.fxml.LoadException`: No controller specified
  - No controller specified
- Initialize method is not executed
  - No controller specified

### UnsupportedOperationException

This exception can occur when trying to modify an unmodifiable collection. The solution is to ensure that you are using a modifiable version of the collection when attempting to make changes.

This detailed explanation covers the advanced topics of managing multiple screens, passing data between controllers, internationalization, and handling common errors in JavaFX. Each code snippet provides a practical example of how these concepts are applied in a JavaFX application. If you need more information or further examples on any of these topics, please let me know!

```java
List<Products> getAllProducts() {
	return Collections.unmodifiableList(allProducts);
}
FXCollections.observableList(getAllProducts());
FXCollections.observableList(new ArrayList<>(getAllProducts()));
FXCollections.observableArrayList(getAllProducts());
```

### FX:ids on Buttons

- For most buttons you will implement an ”onAction” in the FXML and the
  corresponding method in the controller.
- If this is the use-case, you should not add an fx:id for this button, it is not
  needed!
