package fr.insa.messenger.database.queries;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This interface is like an iterator
 * for each ResultSet elements.
 *
 * @author Damien MOLINA
 */
public interface ResultCallback {

    /**
     * Manage the given result element.
     *
     * @param result : element.
     * @return True if the iteration should continue,
     * False otherwise.
     * @throws SQLException : result error.
     */
    boolean manage(ResultSet result) throws SQLException;

}
