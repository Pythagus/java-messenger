package Messenger.Network.Tasks.Listeners.Meetings.Handlers;

import Messenger.GUI.GraphicInterface;
import Messenger.Foundation.Models.User;
import Messenger.Foundation.System.Console.Console;
import Messenger.Foundation.Controllers.UserController;

/**
 * @author Damien MOLINA
 */
public class AcceptedConnection {

    /**
     * Run the listener.
     */
    public void accepted(User user) {
        Console.comment("=> Accepted user : " + user.getPseudo()) ;

        UserController.instance().addUser(user) ;

        // Graphic updates.
        GraphicInterface.instance().contactBar().removeItem(user) ;
        GraphicInterface.instance().discussionBar().addItem(user) ;
    }

}
