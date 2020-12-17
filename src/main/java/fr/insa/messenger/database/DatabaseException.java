package fr.insa.messenger.database;

import fr.insa.messenger.system.console.Console;
import fr.insa.messenger.exceptions.AppException;

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
