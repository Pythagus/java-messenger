package fr.insa.messenger.tomcat.exceptions;

import fr.insa.messenger.tomcat.utils.servlets.MyServletException;

/**
 * @author Damien MOLINA
 */
public class InternalException extends MyServletException {

    /**
     * The incoming request failed. We make
     * an InternalException exception.
     *
     * @param message : exception message.
     */
    public InternalException(String message) {
        super(message) ;
    }

}
