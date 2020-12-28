package fr.insa.messenger.client.database;

import java.sql.SQLException;

/**
 * @author Damien MOLINA
 */
final public class DatabaseInterface {

    /**
     * Make a new query manager instance.
     *
     * @param table : queried table.
     * @return the manager instance.
     * @throws SQLException : connection error.
     */
    public static DatabaseQueryManager query(String table) throws SQLException {
        return new DatabaseQueryManager(
            new DatabaseConnection(), table
        ) ;
    }

}
