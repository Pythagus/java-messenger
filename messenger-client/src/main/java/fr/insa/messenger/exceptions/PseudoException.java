package fr.insa.messenger.exceptions;

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
