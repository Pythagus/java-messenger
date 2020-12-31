package fr.insa.messenger.tools.database;

import java.sql.SQLException;
import fr.insa.messenger.tools.database.queries.DeleteQuery;
import fr.insa.messenger.tools.database.queries.InsertQuery;
import fr.insa.messenger.tools.database.queries.SelectQuery;
import fr.insa.messenger.tools.database.queries.UpdateQuery;

/**
 * @author Damien MOLINA
 */
final public class DatabaseInterface {

    /**
     * Make a new database connection.
     *
     * @return the connection instance.
     * @throws SQLException : connection error.
     */
    private static DatabaseConnection connection() throws SQLException {
        return new DatabaseConnection() ;
    }

    /**
     * Make a select query instance.
     *
     * @param table : queried table.
     * @return the query instance.
     */
    public static SelectQuery select(String table, String... fields) throws SQLException {
        return new SelectQuery(DatabaseInterface.connection(), table, fields) ;
    }

    /**
     * Make a insert query instance.
     *
     * @param table : queried table.
     * @return the insert instance.
     */
    public static InsertQuery insert(String table) throws SQLException {
        return new InsertQuery(DatabaseInterface.connection(), table) ;
    }

    /**
     * Make a delete query instance.
     *
     * @return the delete instance.
     */
    public static DeleteQuery delete(String table) throws SQLException {
        return new DeleteQuery(DatabaseInterface.connection(), table) ;
    }

    /**
     * Make an update query instance.
     *
     * @param table : queried table.
     * @return the update instance.
     */
    public static UpdateQuery update(String table) throws SQLException {
        return new UpdateQuery(DatabaseInterface.connection(), table) ;
    }

}
