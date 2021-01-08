package fr.insa.messenger.client.network.listeners.handlers;

import fr.insa.messenger.client.models.User;
import fr.insa.messenger.client.ui.GraphicInterface;
import fr.insa.messenger.client.system.console.Console;
import fr.insa.messenger.client.ui.screens.utils.BarType;
import fr.insa.messenger.client.ui.screens.utils.ContentType;
import fr.insa.messenger.client.ui.screens.contacts.ContactBar;
import fr.insa.messenger.client.ui.screens.contacts.ContactList;
import fr.insa.messenger.client.controllers.ConversationController;

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
