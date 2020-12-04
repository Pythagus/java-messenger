package Messenger.Network.Tasks.Listeners.Meetings.Handlers;

import java.net.SocketException;

import Messenger.Foundation.Env;
import Messenger.Foundation.Models.User;
import Messenger.Foundation.System.Console.Console;

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
        // TODO : to do
    }

    /**
     * When the connection was simply refused.
     */
    public void denied() {
        // TODO : to do
    }

}
