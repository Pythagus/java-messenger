package fr.insa.messenger.tomcat.utils.servlets;

import javax.servlet.ServletException;

/**
 * @author Damien MOLINA
 */
public class MyServletException extends ServletException {

    /**
     * Constructs a new servlet exception when the servlet
     * needs to throw an exception and include a message
     * about the "root cause" exception that interfered with its
     * normal operation.  The exception's message is based on the localized
     * message of the underlying exception.
     *
     * <p>This method calls the <code>getLocalizedMessage</code> method
     * on the <code>Throwable</code> exception to get a localized exception
     * message. When subclassing <code>ServletException</code>,
     * this method can be overridden to create an exception message
     * designed for a specific locale.
     *
     * @param rootCause 	the <code>Throwable</code> exception
     * 				that interfered with the servlet's
     *				normal operation, making the servlet exception
     *				necessary
     *
     */
    public MyServletException(Throwable rootCause) {
        super(rootCause) ;
    }

    /**
     * Make a new servlet exception.
     *
     * @param message : servlet error.
     */
    public MyServletException(String message) {
        super(message) ;
    }

}
