package be.howest.ti.data;

import be.howest.ti.domain.User;
import be.howest.ti.util.MySqlConnection;
import be.howest.ti.util.TestProjectException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQLRepository implements TestRepository{

    /**
     * FIELDS
     * */
    private static final Logger LOGGER = Logger.getLogger(MySQLRepository.class.getName());

    /**
     * SQL STATEMENTS
     * */
    private static final String SELECT_ALL_USERS = "SELECT * FROM users";
    private static final String SELECT_USER_BY_USERNAME = "SELECT * FROM users WHERE username = ?";
    private static final String INSERT_USER = "INSERT INTO users (username, password) VALUES (?, ?)";


    /**
     * METHODS
     * */
    @Override
    public void addUser(User user) {
        try(Connection con = MySqlConnection.getConnection();
            PreparedStatement prep = con.prepareStatement(INSERT_USER)
        ){
            LOGGER.log(Level.INFO, "Connection established, preparing statement");
            prep.setString(1, user.getUsername());
            prep.setString(2, user.getPassword());

            LOGGER.log(Level.INFO, "Statement prepared, executing statement");
            prep.executeUpdate();


        } catch (SQLException ex){
            LOGGER.log(Level.SEVERE, "A DB error occurred trying to add a user", ex);
            throw new TestProjectException("Connection failed", ex);
        }

    }
    @Override
    public void addUser(String username, String password) {
        try(Connection con = MySqlConnection.getConnection();
            PreparedStatement prep = con.prepareStatement(INSERT_USER)
        ){
            LOGGER.log(Level.INFO, "Connection established, preparing statement");
            prep.setString(1, username);
            prep.setString(2, password);

            LOGGER.log(Level.INFO, "Statement prepared, executing statement");
            prep.executeUpdate();

        } catch (SQLException ex){
            LOGGER.log(Level.SEVERE, "A DB error occurred trying to add a user", ex);
            throw new TestProjectException("Connection failed", ex);
        }
    }
    @Override
    public List<User> getAllUsers() {
        //MAKE SURE THERE IS SOME MOCK DATA IN THE DB BEFORE RUNNING THIS METHOD
        try(Connection con = MySqlConnection.getConnection();
            PreparedStatement prep = con.prepareStatement(SELECT_ALL_USERS);
            ResultSet rs = prep.executeQuery()
        ){
            List<User> users = new ArrayList<>();
            LOGGER.log(Level.INFO, "Connection established");
            while(rs.next()){
                LOGGER.log(Level.INFO, "Info retrieved from DB");
                users.add(new User(
                        rs.getString("username"),
                        rs.getString("password")
                ));
            }

            return users;

        } catch (SQLException ex){
            LOGGER.log(Level.SEVERE, "A DB error occurred trying to retrieve all users", ex);
            throw new TestProjectException("Connection failed", ex);
        }
    }
    @Override
    public User getUserByUsername(String username) {
        try(Connection con = MySqlConnection.getConnection();
            PreparedStatement prep = con.prepareStatement(SELECT_USER_BY_USERNAME)
        ){
            //SET USERNAME AS PARAMETER IN THE PREPARED STATEMENT
            prep.setString(1, username);

            try(ResultSet rs = prep.executeQuery()){
                if(rs.next()){
                    return new User(
                            rs.getString("username"),
                            rs.getString("password")
                    );
                } else {
                    return null;
                }
            }
        } catch (SQLException ex){
            LOGGER.log(Level.SEVERE, "A DB error occurred trying to retrieve user by username", ex);
            throw new TestProjectException("Connection failed", ex);
        }
    }
}
