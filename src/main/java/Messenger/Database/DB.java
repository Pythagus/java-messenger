package Messenger.Database;

import java.sql.SQLException;
import Messenger.Database.Queries.DatabaseQuery;
import Messenger.Database.Connectors.DatabaseConnection;

/**
 * @author Damien MOLINA
 */
public class DB {

    /**
     * Load the database drivers and set
     * the properties once and for all.
     */
    public static void load() {
        // Load the MySQL driver.
        DatabaseConnection.loadDriver() ;
    }

    /**
     * Make a new database query.
     *
     * @return the query instance.
     * @throws SQLException : connection error.
     */
    public static DatabaseQuery query() throws SQLException {
        return new DatabaseQuery(new DatabaseConnection()) ;
    }

}
