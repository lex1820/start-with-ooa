# Run movies server locally


## How to get the server running?
1. Download **sql script & .jar** file from LEHO
1. Execute the SQL script to create a MySQL database on your localhost called movies [SQL SCRIPT](movies.sql)
2. Define a user **movies-user** with password **movies-pwd** having **SELECT permissions** on the database
3. Execute the [.jar file](moviebase-server.main.jar) using `java -jar moviebase-server.main.jar`
4. It will listen on port **32768**
 

## Windows users: how to find the java executable to execute the JAR:

1. Open a PowerShell and navigate to the folder into which you've downloaded the JAR file.
2. In this folder, you will find a subfolder for each JDK you've installed earlier using IntelliJ.
3. Navigate to the bin subfolder of the desired JDK.
4. Execute the JAR using java -jar C:\path-to-the-jar\moviebase-server.main.