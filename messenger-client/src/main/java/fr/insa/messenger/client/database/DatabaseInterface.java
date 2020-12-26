package fr.insa.messenger.client.database;

import java.sql.SQLException;

import fr.insa.messenger.client.database.queries.DatabaseQuery;

/**
 * @author Damien MOLINA
 */
final public class DatabaseInterface {

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
