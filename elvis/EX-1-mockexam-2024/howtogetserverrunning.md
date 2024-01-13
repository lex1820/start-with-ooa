# How to get the server running

- Execute the SQL script to create a MySQL database on your localhost called `movies`.
  The script `movies.sql` is for the full database. If this one does not work (due to size), you can use m`ovies-light.sql`, which only contains 10 movies.
- Define a user `movies-user` with password `movies-pwd` having `SELECT` permissions on the database
- Download the JAR file
- Execute it using `java -jar moviebase-server.main.jar`
- It will listen on port `32768`

**Windows users: how to find the java executable to execute the JAR:**

1. Open a PowerShell and navigate to the folder into which you've downloaded the JAR file.
2. In this folder, you will find a subfolder for each JDK you've installed earlier using IntelliJ.
3. Navigate to the bin subfolder of the desired JDK.
4. Execute the JAR using java -jar `C:\path-to-the-jar\moviebase-server.main.jar`
