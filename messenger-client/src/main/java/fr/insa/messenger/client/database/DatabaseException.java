package fr.insa.messenger.client.database;

import fr.insa.messenger.client.exceptions.AppException;
import fr.insa.messenger.client.system.console.Console;

/**
 * @author Damien MOLINA
 */
public class DatabaseException extends AppException {

    /**
     * Make a new App exception with
     * the given message.
     *
     * @param msg : exception message.
     */
    public DatabaseException(String msg) {
        super(msg) ;

        Console.danger(msg) ;
    }

}
