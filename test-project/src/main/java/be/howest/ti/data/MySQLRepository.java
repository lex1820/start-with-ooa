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
     * STATEMENTS
     * */
    private static final String SELECT_ALL_USERS = "SELECT * FROM users";
    /**
     * METHODS
     * */

    @Override
    public void addUser(User user) {

    }

    @Override
    public void addUser(String username, String password) {

    }

    @Override
    public List<User> getAllUsers() {
        try(Connection con = MySqlConnection.getConnection();
            PreparedStatement prep = con.prepareStatement(SELECT_ALL_USERS);
            ResultSet rs = prep.executeQuery()
        ){
            List<User> users = new ArrayList<>();

            while(rs.next()){
                System.out.println(rs.getString("username"));
                System.out.println(rs.getString("password"));
            }

            return users;

        } catch (SQLException ex){
            LOGGER.log(Level.SEVERE, "A DB error occurred trying to retrieve all users", ex);
            throw new TestProjectException("Connection failed", ex);
        }
    }

    @Override
    public User getUserByUsername(String username) {
        return null;
    }
}
