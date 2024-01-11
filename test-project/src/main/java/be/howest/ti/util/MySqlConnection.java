package be.howest.ti.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySqlConnection {

    //Use key to get to the value of the key

    /**
     * FIELDS
     * */
    private static final String KEY_DB_URL = "db.url";
    private static final String KEY_USERNAME = "db.username";
    private static final String KEY_PASSWORD = "db.password";
    private static final Logger LOGGER = Logger.getLogger(MySqlConnection.class.getName());

    private static final String db_url;
    private static final String username;
    private static final String password;

    static{
        db_url = Config.getInstance().readSetting(KEY_DB_URL);
        username = Config.getInstance().readSetting(KEY_USERNAME);
        password = Config.getInstance().readSetting(KEY_PASSWORD);
    }

    /**
     * CONSTRUCTOR
     * */
    private MySqlConnection() {}


    //Static block to create the connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(db_url, username, password);
    }
}
