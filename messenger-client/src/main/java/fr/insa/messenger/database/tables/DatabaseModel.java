package fr.insa.messenger.database.tables;

import java.sql.SQLException;
import fr.insa.messenger.database.DatabaseInterface;
import fr.insa.messenger.database.queries.DatabaseQuery;

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
