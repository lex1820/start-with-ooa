package be.howest.ti.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySqlConnection {
    // This class makes it possible to create a connection to the DB
    // By creating this class & calling it you don't have to write the code to create a connection every time you want to connect to the DB
    // You can just call this class & it will create the connection for you
    /**
     * FIELDS
     * */
    private static final String KEY_DB_URL = "db.url";
    private static final String KEY_USERNAME = "db.username";
    private static final String KEY_PASSWORD = "db.password";

    private static final String db_url;
    private static final String username;
    private static final String password;

    static{
        db_url = Config.getInstance().readSetting(KEY_DB_URL);
        username = Config.getInstance().readSetting(KEY_USERNAME);
        // use the password that's not encrypted
        //password = Config.getInstance().readSetting(KEY_PASSWORD);
        // Decrypt the password
        password = Crypto.getInstance().decrypt(Config.getInstance().readSetting(KEY_PASSWORD));
    }

    /**
     * CONSTRUCTOR
     * */
    private MySqlConnection() {}

    /**
     * METHODS
     * */

    //Static block to create the connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(db_url, username, password);
    }
}
