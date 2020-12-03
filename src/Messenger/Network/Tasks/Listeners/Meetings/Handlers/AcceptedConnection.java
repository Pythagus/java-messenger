package Messenger.Network.Tasks.Listeners.Meetings.Handlers;

import Messenger.Foundation.Models.User;

/**
 * @author Damien MOLINA
 */
public class AcceptedConnection {

    /**
     * Run the listener.
     */
    public void accepted(User user) {
        System.out.println("Accepted user : " + user.getPseudo()) ;

        // TODO : to do
    }

}
