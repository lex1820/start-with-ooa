public class MySqlConnection {
    //Use the key to get to the value of the key
    private static final String KEY_DB_URL = "db.url";
    private static final String KEY_DB_USERNAME = "db.username";
    private static final String KEY_DB_PASSWORD = "db.password"; // NOSONAR

    private static final String url;
    private static final String username;
    private static final String password;
    
    static {
        String usernameEncrypted = Config.getInstance().readSetting(KEY_DB_USERNAME);
        String passwordEncrypted = Config.getInstance().readSetting(KEY_DB_PASSWORD);
        
        Crypto crypto = Crypto.getInstance();
        
        username = crypto.decrypt(usernameEncrypted);
        password = crypto.decrypt(passwordEncrypted);
        url = Config.getInstance().readSetting(KEY_DB_URL);
    }

    private MySqlConnection() {
    }

    public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(url, username, password);
    }
}