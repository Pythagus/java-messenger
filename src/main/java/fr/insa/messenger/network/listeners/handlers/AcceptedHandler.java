package fr.insa.messenger.network.listeners.handlers;

import fr.insa.messenger.models.User;
import fr.insa.messenger.ui.GraphicInterface;
import fr.insa.messenger.system.console.Console;
import fr.insa.messenger.ui.screens.utils.BarType;
import fr.insa.messenger.ui.screens.utils.ContentType;
import fr.insa.messenger.ui.screens.contacts.ContactBar;
import fr.insa.messenger.ui.screens.contacts.ContactList;
import fr.insa.messenger.controllers.ConversationController;

/**
 * @author Damien MOLINA
 */
public class AcceptedHandler {

    /**
     * Run the listener.
     */
    public void handle(User user) {
        Console.comment("=> Accepted user : " + user.getPseudo()) ;

        ContactBar bar = GraphicInterface.instance().contactBar() ;
        ContactList list = bar.getList() ;

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
