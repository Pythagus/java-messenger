package Messenger.GUI.Listeners;

import Messenger.Foundation.Models.Conversation;
import Messenger.Foundation.Models.Messages.Message;
import Messenger.GUI.Frames.Screens.DiscussionScreen;
import Messenger.Foundation.Observers.Contracts.Listener;
import Messenger.Foundation.Controllers.ConversationController;
import Messenger.GUI.Frames.Screens.Discussions.MessageListItem;

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
