package Messenger.Network.Tasks.Listeners.Meetings.Handlers;

import Messenger.GUI.GraphicInterface;
import Messenger.Foundation.Models.User;
import Messenger.GUI.Frames.Screens.Utils.BarType;
import Messenger.Foundation.System.Console.Console;
import Messenger.GUI.Frames.Screens.Utils.ContentType;
import Messenger.GUI.Frames.Screens.Contacts.ContactBar;
import Messenger.GUI.Frames.Screens.Contacts.ContactList;
import Messenger.Foundation.Controllers.ConversationController;

/**
 * @author Damien MOLINA
 */
public class AcceptedConnection {

    /**
     * Run the listener.
     */
    public void accepted(User user) {
        Console.comment("=> Accepted user : " + user.getPseudo()) ;

        ContactBar bar = GraphicInterface.instance().contactBar() ;
        ContactList list = (ContactList) bar.getList() ;

        // Update graphics.
        list.getFrame().showBar(BarType.DISCUSSION) ;
        list.getFrame().showContent(ContentType.DISCUSSION) ;

        // Add the current item to the discussion list.
        ConversationController.instance().addConversation(user) ;
        list.getFrame().getBar(BarType.DISCUSSION).getList().addItem(user, true) ;

        // Remove the current item from the list.
        list.removeItem(user) ;
        list.clearSelection() ;
    }

}
