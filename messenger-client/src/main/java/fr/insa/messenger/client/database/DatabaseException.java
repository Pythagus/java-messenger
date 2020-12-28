package fr.insa.messenger.client.database;

import java.sql.SQLException;

/**
 * @author Damien MOLINA
 */
public class DatabaseException extends SQLException {

    /**
     * Make a new database exception instance.
     *
     * @param reason : reason to explain the exception.
     */
    public DatabaseException(String reason) {
        super(reason) ;
    }

}
