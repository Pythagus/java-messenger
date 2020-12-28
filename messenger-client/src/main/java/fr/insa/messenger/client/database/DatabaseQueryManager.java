package fr.insa.messenger.client.database;

import fr.insa.messenger.client.database.queries.InsertQuery;
import fr.insa.messenger.client.database.queries.SelectQuery;

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

}
