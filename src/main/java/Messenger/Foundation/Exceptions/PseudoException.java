package Messenger.Foundation.Exceptions;

import Messenger.Foundation.Exceptions.Contracts.UserException;

/**
 * @author Damien MOLINA
 */
public class PseudoException extends AppException implements UserException {

    /**
     * Make a new App exception with
     * the given message.
     *
     * @param msg : exception message.
     */
    public PseudoException(String msg) {
        super(msg) ;
    }

}
