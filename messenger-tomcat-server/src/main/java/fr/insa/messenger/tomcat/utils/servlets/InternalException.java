package fr.insa.messenger.tomcat.utils.servlets;

import javax.servlet.ServletException;

/**
 * @author Damien MOLINA
 */
public class InternalException extends ServletException {

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
