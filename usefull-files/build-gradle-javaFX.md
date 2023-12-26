# BUILD.GRADLE FOR JavaFX
```gradle
    //This is how your build.gradle file should look like after adding all necessary stuff
    plugins {
        id 'java'
        id 'application'
        id 'org.openjfx.javafxplugin' version '0.1.0'
    }

    group = 'org.example'
    version = '1.0-SNAPSHOT'

    repositories {
        mavenCentral()
    }

    dependencies {
        testImplementation platform('org.junit:junit-bom:5.9.1')
        testImplementation 'org.junit.jupiter:junit-jupiter'
    }

    test {
        useJUnitPlatform()
    }

    //THIS ARE THE 2 BLOCKS YOU NEED TO ADD
    javafx{
        //For newest version check: https://openjfx.io/openjfx-docs/#gradle
        version = "21"
        //Import the modules that are necessary for your project 
        modules = [ 'javafx.controls', 'javafx.fxml' ]
    }

    application{
        //Path to class in which you will use to call your FXML
        mainClass = 'be.howest.ti.ui.gui.Program'
    }
```