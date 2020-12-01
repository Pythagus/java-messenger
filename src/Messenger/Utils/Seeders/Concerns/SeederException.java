package Messenger.Utils.Seeders.Concerns;

import Messenger.Foundation.Exceptions.AppException;

/**
 * @author Damien MOLINA
 */
public class SeederException extends AppException {

    /**
     * Make a new App exception with
     * the given message.
     *
     * @param msg : exception message.
     */
    public SeederException(String msg) {
        super("Seeder : " + msg) ;
    }

}
