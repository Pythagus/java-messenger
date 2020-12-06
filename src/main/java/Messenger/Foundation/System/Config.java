package Messenger.Foundation.System;

import java.util.Properties;
import java.io.FileInputStream;

/**
 * @author Damien MOLINA
 */
public class Config {

    /**
     * Properties file.
     */
    private static final String FILE = ".properties" ;

    /**
     * Configuration values.
     */
    private static Properties configs ;

    /**
     * Get the configuration value of
     * the given key.
     *
     * @param key : configuration key.
     * @return the configuration value.
     */
    public static String get(String key) {
        return Config.get(key, null) ;
    }

    /**
     * Get the configuration value of
     * the given key. If the value is
     * null, the default value will be
     * returned.
     *
     * @param key : configuration key.
     * @param defaultValue : default value if
     *                     the config one is null.
     * @return the configuration value.
     */
    public static String get(String key, String defaultValue) {
        if(Config.configs == null) {
            return defaultValue ;
        }

        String value = Config.configs.getProperty(key) ;

        return value == null ? defaultValue : value ;
    }

    /**
     * Load the configuration values.
     */
    public static void load() {
        try {
            FileInputStream in = new FileInputStream("src/main/" + Config.FILE) ;

            Properties properties = new Properties() ;
            properties.load(in) ;

            Config.configs = properties ;
        } catch (Exception e) {
            e.printStackTrace() ;
        }
    }

}
