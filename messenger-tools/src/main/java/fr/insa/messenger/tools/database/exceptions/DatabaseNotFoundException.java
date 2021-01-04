package fr.insa.messenger.tools.database.exceptions;

import fr.insa.messenger.tools.database.queries.DatabaseQuery;

/**
 * @author Damien MOLINA
 */
public class DatabaseNotFoundException extends DatabaseException {

    /**
     * Make a new database exception instance.
     *
     * @param reason : reason to explain the exception.
     */
    public DatabaseNotFoundException(String reason) {
        super(reason) ;
    }

    /**
     * Make a new database exception instance.
     *
     * @param query : query error.
     */
    public DatabaseNotFoundException(DatabaseQuery query) {
        this(query.toString()) ;
    }

}
