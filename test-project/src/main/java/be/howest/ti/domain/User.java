package be.howest.ti.domain;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

//This class is Serializable, so it can be written to a file
public class User implements Serializable {

    /**
     * FIELDS
     * */

    @Serial
    private final String username;

    @Serial
    private final String password;


    /**
     * CONSTRUCTOR
     * */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * METHODS
     * */
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }


    /**
     * HELPER METHODS
     * */
    @Override
    public String toString() {
        return username + "\t" + password;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(password, user.password);
    }
    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }
}
