package fr.insa.messenger.tools.database;

import java.util.Properties;
import fr.insa.messenger.tools.Config;

/**
 * @author Damien MOLINA
 */
public class DatabaseProperties extends Properties {

    /**
     * Default database driver.
     */
    private static final String DRIVER = Config.get("DB_CONNECTION") ;

    /**
     * Make a DatabaseProperties instance
     * regarding the configuration file
     * value.
     *
     * @return the generated instance.
     */
    public static DatabaseProperties fromConfig() {
        DatabaseProperties p = new DatabaseProperties() ;

        // Set the values.
        p.setHost(Config.get("DB_HOST")) ;
        p.setPort(Config.get("DB_PORT")) ;
        p.setDatabase(Config.get("DB_DATABASE")) ;
        p.setUser(Config.get("DB_USER")) ;
        p.setPassword(Config.get("DB_PASSWORD")) ;

        return p ;
    }

    /**
     * Set the user property.
     *
     * @param user : user loggable to the database.
     */
    public void setUser(String user) {
        this.setProperty("user", user) ;
    }

    /**
     * Set the user's password.
     *
     * @param password : password to connect to the database.
     */
    public void setPassword(String password) {
        this.setProperty("password", password) ;
    }

    /**
     * Set the connection port value.
     *
     * @param port : port number.
     */
    public void setPort(String port) {
        this.setProperty("port", port) ;
    }

    /**
     * Set the database name.
     *
     * @param database name.
     */
    public void setDatabase(String database) {
        this.setProperty("database", database) ;
    }

    /**
     * Set the host url.
     *
     * @param host : host url.
     */
    public void setHost(String host) {
        this.setProperty("host", host) ;
    }

    /**
     * Generate the full connection
     * URL.
     *
     * @return a full URL.
     */
    public String getFullURL() {
        /*
         * This will generate a string like:
         * jdbc:mysql://localhost:3306/fr.insa.messenger
         */
        return String.format(
            "jdbc:%s://%s:%s/%s", DRIVER, this.getProperty("host"), this.getProperty("port"), this.getProperty("database")
        ) ;
    }

}
