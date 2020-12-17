package Messenger.Network.Tasks.Listeners.Meetings.Handlers;

import Messenger.GUI.GraphicInterface;
import Messenger.Foundation.System.Env;
import Messenger.Foundation.Models.User;
import Messenger.Foundation.System.Console.Console;

/**
 * @author Damien MOLINA
 */
public class AcceptedConnection {

    /**
     * Run the listener.
     */
    public void accepted(User user) {
        Console.comment("=> Accepted user : " + user.getPseudo()) ;

        Env.userController().addUser(user) ;

        // Graphic updates.
        GraphicInterface.instance().contactBar().removeItem(user) ;
        GraphicInterface.instance().discussionBar().addItem(user) ;
    }

}
