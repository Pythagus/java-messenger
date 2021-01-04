package fr.insa.messenger.client.http;

/**
 * @author Damien MOLINA
 */
public class PresenceServerException extends RuntimeException {

    /**
     * Make a new presence exception.
     *
     * @param route : HTTP route failing.
     */
    public PresenceServerException(String route) {
        super("Presence server failed on " + route + " route.") ;
    }

}
