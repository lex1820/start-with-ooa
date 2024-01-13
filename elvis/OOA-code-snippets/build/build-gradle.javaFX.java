 // This is how your build.gradle file should look like after adding all necessary stuff

plugins {
    id("java")
    id ("application")
    id("org.openjfx.javafxplugin") version "0.1.0" // or version("0.1.0")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

javafx {
    // https://openjfx.io/openjfx-docs/#gradle
    version = "21.0.1"
    //Import the modules that are necessary for your project 
    modules("javafx.controls", "javafx.fxml")
}

application {
    // Path to class in which you will use to call your FXML
    mainClass.set("be.howest.ti.ui.gui.[name of your class]")
}



dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    // https://mvnrepository.com/artifact/com.mysql/mysql-connector-j
    implementation("mysql:mysql-connector-java:8.0.33")

    // https://mvnrepository.com/artifact/org.springframework.security/spring-security-core
    implementation("org.springframework.security:spring-security-core:6.2.1")

}

test {
        useJUnitPlatform()
}
