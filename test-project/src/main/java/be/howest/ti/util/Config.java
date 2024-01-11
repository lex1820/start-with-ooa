package be.howest.ti.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Config {

    //This is a class that makes it possible to read & write data to & from a config file in the resources of the project

    /**
     * FIELDS
     * */

    private static final String CONFIG_FILE = "/config/config.properties";
    private static final Config INSTANCE = new Config();
    private final Properties properties = new Properties();
    private static final Logger LOGGER = Logger.getLogger(Config.class.getName());

    private Config(){
        try(InputStream ris = getClass().getResourceAsStream(CONFIG_FILE)){
            properties.load(ris);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Could not read config file " + CONFIG_FILE, e);
            throw new TestProjectException("Problem with the configuration");
        }
    }

    public static Config getInstance(){
        return INSTANCE;
    }

    public String readSetting(String key, String defaultValue){
        return properties.getProperty(key, defaultValue);
    }

    public String readSetting(String key){
        return properties.getProperty(key, null);
    }

    public void writeSetting(String key, String value) {
        properties.setProperty(key, value);
        storeSettingsToFile();
    }

    private void storeSettingsToFile() {
        String path = getClass().getResource(CONFIG_FILE).getPath();

        try(FileOutputStream fos = new FileOutputStream(path)){
            properties.store(fos, null);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Could not write config file " + CONFIG_FILE, e);
            throw new TestProjectException("Problem with the configuration");
        }
    }

}
