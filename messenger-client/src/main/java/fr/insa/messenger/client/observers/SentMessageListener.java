package fr.insa.messenger.client.observers;

import fr.insa.messenger.client.models.Message;
import fr.insa.messenger.client.models.Conversation;
import fr.insa.messenger.client.ui.screens.DiscussionScreen;
import fr.insa.messenger.client.observers.contracts.Listener;
import fr.insa.messenger.client.controllers.ConversationController;
import fr.insa.messenger.client.ui.screens.discussions.MessageListItem;

/**
 * @author Damien MOLINA
 */
public class SentMessageListener implements Listener {

    /**
     * Discussion screen instance.
     */
    private final DiscussionScreen screen ;

    /**
     * Make a new listener instance.
     *
     * @param screen : discussion screen instance.
     */
    public SentMessageListener(DiscussionScreen screen) {
        this.screen = screen ;
    }

    /**
     * Notify the listener with the given arguments.
     *
     * @param args : notification arguments.
     */
    public void handle(Object... args) {
        Conversation conversation = (Conversation) args[0] ;
        Message message = (Message) args[1] ;

        // Send the message.
        ConversationController.instance().send(message) ;

        // Add the message.
        ConversationController.instance().addConversationMessage(
            message.getTarget(), message
        ) ;

        // If the conversation is the current active one.
        if(this.screen.getConversation().equals(conversation)) {
            this.screen.getList().addItem(
                new MessageListItem(this.screen.getList(), message)
            ) ;
        }
    }

}
