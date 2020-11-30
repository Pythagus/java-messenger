package Messenger.Foundation.Exceptions.Pseudo;

import Messenger.Foundation.Exceptions.AppException;
import Messenger.Foundation.Exceptions.Contracts.UserException;

/**
 * @author Damien MOLINA
 */
abstract public class PseudoException extends AppException implements UserException {

    /**
     * Make a new Pseudo exception with
     * the given message.
     *
     * @param msg : exception message.
     */
    public PseudoException(String msg) {
        super(msg) ;
    }

}
