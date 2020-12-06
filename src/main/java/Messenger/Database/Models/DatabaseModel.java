package Messenger.Database.Models;

import Messenger.Database.DB;
import java.sql.SQLException;
import Messenger.Database.Queries.DatabaseQuery;

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
        return DB.query() ;
    }

}
