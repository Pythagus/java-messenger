package fr.insa.messenger.network.listeners.handlers;

import fr.insa.messenger.models.User;
import fr.insa.messenger.system.console.Console;
import fr.insa.messenger.controllers.UserController;
import fr.insa.messenger.controllers.ConversationController;

/**
 * @author Damien MOLINA
 */
public class LogoutHandler {

    /**
     * Run the listener.
     */
    public void handle(User user) {
        Console.comment("=> Logout : " + user.getPseudo()) ;

        UserController.instance().removeUser(user) ;
    }

    /**
     * Handle the user logout from the
     * network side.
     *
     * @param user : logout user.
     */
    public static void handleNetwork(User user) {
        ConversationController.instance().stop(user) ;
    }

}
