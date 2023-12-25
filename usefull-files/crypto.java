public class Crypto {
    //set password & salt
    private static final String PASSWORD = "hello-from-howest";
    private static final String SALT = "1AB9F37C2EDA";
    
    private static final Crypto instance = new Crypto();
    
    private final TextEncryptor encryptor;

    public static Crypto getInstance() {
        return instance;
    }
    private Crypto() {
        encryptor = Encryptors.text(PASSWORD, SALT);
    }
    
    public String encrypt(String in) {
        return encryptor.encrypt(in);
    }
    
    public String decrypt(String in) {
        return encryptor.decrypt(in);
    }
}