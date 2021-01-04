package fr.insa.messenger.tomcat.exceptions;

/**
 * @author Damien MOLINA
 */
public class UnknownUserException extends InternalException {

    /**
     * The incoming request failed. We make
     * an InternalException exception.
     *
     * @param identifier : user identifier.
     */
    public UnknownUserException(String identifier) {
        super("User " + identifier + " is unknown.") ;
    }

}
