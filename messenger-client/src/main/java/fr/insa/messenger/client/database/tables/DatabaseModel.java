package fr.insa.messenger.client.database.tables;

import java.sql.SQLException;

import fr.insa.messenger.client.database.queries.DatabaseQuery;
import fr.insa.messenger.client.database.DatabaseInterface;

/**
 * @author Damien MOLINA
 */
abstract public class DatabaseModel {

    /**
     * Prepare a new query
     * manager.
     *
     * @return the query manager.
     * @throws SQLException : connection error.
     */
    public DatabaseQuery query() throws SQLException {
        return DatabaseInterface.query() ;
    }

}
