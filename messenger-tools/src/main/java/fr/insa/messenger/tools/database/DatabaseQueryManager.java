package fr.insa.messenger.tools.database;

import fr.insa.messenger.tools.database.queries.DeleteQuery;
import fr.insa.messenger.tools.database.queries.SelectQuery;
import fr.insa.messenger.tools.database.queries.InsertQuery;

/**
 * @author Damien MOLINA
 */
public class DatabaseQueryManager {

    /**
     * Connection instance.
     */
    private final DatabaseConnection connection ;

    /**
     * Queried table.
     */
    private final String table ;

    /**
     * Make a query manager instance.
     *
     * @param connection : connection instance.
     * @param table : queried table.
     */
    public DatabaseQueryManager(DatabaseConnection connection, String table) {
        this.connection = connection ;
        this.table      = table ;
    }

    /**
     * Make a select query instance.
     *
     * @return the query instance.
     */
    public SelectQuery select(String... fields) {
        return new SelectQuery(this.connection, this.table, fields) ;
    }

    /**
     * Make a insert query instance.
     *
     * @return the insert instance.
     */
    public InsertQuery insert() {
        return new InsertQuery(this.connection, this.table) ;
    }

    /**
     * Make a delete query instance.
     *
     * @return the delete instance.
     */
    public DeleteQuery delete() {
        return new DeleteQuery(this.connection, this.table) ;
    }

}
