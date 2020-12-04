package Messenger.GUI.Exceptions;

import Messenger.Foundation.Exceptions.AppException;

/**
 * @author Damien MOLINA
 */
public class UiException extends AppException {

    /**
     * Make a new App exception with
     * the given message.
     *
     * @param msg : exception message.
     */
    public UiException(String msg) {
        super(msg) ;
    }

}
