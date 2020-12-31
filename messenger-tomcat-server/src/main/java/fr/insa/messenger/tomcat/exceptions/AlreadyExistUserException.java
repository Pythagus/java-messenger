package fr.insa.messenger.tomcat.exceptions;

/**
 * @author Damien MOLINA
 */
public class AlreadyExistUserException extends InternalException {

    /**
     * The incoming request failed. We make
     * an InternalException exception.
     *
     * @param identifier : user identifier.
     */
    public AlreadyExistUserException(String identifier) {
        super("User " + identifier + " already exists.") ;
    }

}
