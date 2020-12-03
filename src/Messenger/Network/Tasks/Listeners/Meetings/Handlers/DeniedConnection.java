package Messenger.Network.Tasks.Listeners.Meetings.Handlers;

import java.net.SocketException;
import Messenger.Foundation.Models.User;

/**
 * @author Damien MOLINA
 */
public class DeniedConnection {

    /**
     * The given user is unreachable.
     *
     * @param exception : exception thrown.
     * @param user : unreachable user instance.
     */
    public void unreachableUser(SocketException exception, User user) {
        System.out.println("User unreachable : " + user.getPseudo()) ;

        // TODO : to do
    }

    /**
     * When the connection was simply refused.
     */
    public void denied() {
        System.out.println("Denied") ;

        // TODO : to do
    }

}
