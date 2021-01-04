package fr.insa.messenger.client.network.listeners.handlers;

import java.net.SocketException;
import fr.insa.messenger.client.models.User;

/**
 * @author Damien MOLINA
 */
public class DeniedHandler {

    /**
     * The given user is unreachable.
     *
     * @param exception : exception thrown.
     * @param user : unreachable user instance.
     */
    public void unreachableUser(SocketException exception, User user) {
        // TODO : to do
    }

    /**
     * When the connection was simply refused.
     */
    public void denied() {
        // TODO : to do
    }

}
