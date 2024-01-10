package be.howest.ti.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {

    //Use key to get to the value of the key

    /**
     * FIELDS
     * */
    private static final String URL = "jdbc:mysql://localhost:3306/test-project";
    private static final String USERNAME = "test-project-username";
    private static final String PASSWORD = "test-project-pwd";

    /**
     * CONSTRUCTOR
     * */
    private MySqlConnection() {}


    //Static block to create the connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
