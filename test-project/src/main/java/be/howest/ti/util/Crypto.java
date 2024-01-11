package be.howest.ti.util;

import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;

public class Crypto {

    //This class is used to encrypt & decrypt data (password of to establish DB connection)
    //Can be used to store the passwords of users encrypted in the DB

    /**
     * FIELDS
     * */
    private static final String PASSWORD = "hello-from-howest";
    //You can choose the salt yourself
    private static final String SALT = "1BC6F92E13";
    private final TextEncryptor encryptor = Encryptors.text(PASSWORD, SALT);
    private static Crypto INSTANCE = new Crypto();

    /**
     * CONSTRUCTOR
     * */
    public static Crypto getInstance() {
        return INSTANCE;
    }

    /**
     * METHODS
     * */

    public String encrypt(String in) {
        return encryptor.encrypt(in);
    }

    public String decrypt(String in) {
        return encryptor.decrypt(in);
    }


}
