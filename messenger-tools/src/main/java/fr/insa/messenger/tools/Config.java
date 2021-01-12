package fr.insa.messenger.tools;

import java.io.InputStream;
import java.util.Properties;
import java.io.FileInputStream;

/**
 * @author Damien MOLINA
 */
final public class Config {

    /**
     * Properties file.
     */
    private static final String FILE = ".properties" ;

    /**
     * Configuration values.
     */
    private static Properties configs ;

    /**
     * Configuration root.
     */
    private static String root = "" ;

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
     * the given key.
     *
     * @param key : configuration key.
     * @return the configuration value.
     */
    public static String getNotNull(String key) {
        String value = Config.get(key, null) ;

        if(value == null) {
            throw new RuntimeException("Null property with key " + key) ;
        }

        return value ;
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
            Config.load() ;
        }

        String value = Config.configs.getProperty(key) ;

        return value == null ? defaultValue : value ;
    }

    /**
     * Set the configuration root.
     *
     * @param root : configuration root.
     */
    public static void setRoot(String root) {
        String r = root ;

        if(r.length() > 0) {
            r = r.endsWith("/") ? r : r + "/" ;
        }

        Config.root = r ;
    }

    /**
     * Load the configuration values.
     */
    public static void load() {
        try {
            String filePath = Config.root + Config.FILE ;

            InputStream stream = Config.class.getResourceAsStream(filePath) ;

            if(stream == null) {
                stream = new FileInputStream(filePath) ;
            }

            Config.configs = new Properties() ;
            Config.configs.load(stream) ;
        } catch (Exception e) {
            throw new RuntimeException(e) ;
        }
    }

}
