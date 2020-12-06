package Messenger.Database.Connectors;

import java.sql.SQLException;
import com.mysql.cj.conf.HostInfo;
import com.mysql.cj.conf.ConnectionUrl;
import com.mysql.cj.jdbc.ConnectionImpl;

/**
 * @author Damien MOLINA
 */
public class DatabaseConnection extends ConnectionImpl {

    /**
     * Default configuration properties.
     * This value is used to make the default database
     * connection, but can be override using another
     * properties instance.
     */
    private static final DatabaseProperties defaultProperties = DatabaseProperties.fromConfig() ;

    /**
     * Make a new database connection.
     *
     * @param properties : database connection properties.
     * @throws SQLException : connection error.
     */
    public DatabaseConnection(DatabaseProperties properties) throws SQLException {
       super(
           DatabaseConnection.createHostInfo(properties)
       ) ;
    }

    /**
     * Make a new database connection.
     *
     * @throws SQLException : connection error.
     */
    public DatabaseConnection() throws SQLException {
       this(DatabaseConnection.defaultProperties) ;
    }

    /**
     * Make the HostInfo instance regarding the
     * properties.
     *
     * @param properties : database properties.
     * @return the HostInfo instance.
     */
    private static HostInfo createHostInfo(DatabaseProperties properties) {
        return ConnectionUrl.getConnectionUrlInstance(
            properties.getFullURL(), properties
        ).getMainHost() ;
    }

    /**
     * Load the database connection driver.
     */
    public static void loadDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver") ;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
