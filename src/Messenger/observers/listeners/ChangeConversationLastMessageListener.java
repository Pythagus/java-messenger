package Messenger.observers.listeners;

import Messenger.observers.Event;
import Messenger.observers.Listener;
import Messenger.observers.events.SendDiscussionMessageEvent;

/**
 * @author Damien MOLINA
 */
public class ChangeConversationLastMessageListener extends Listener {

    /**
     * Handle the given event.
     *
     * @param event : event to handle.
     */
    public void handle(SendDiscussionMessageEvent event) {
        //TODO
    }

    /**
     * Handle the given event.
     *
     * @param event : event to handle.
     */
    public void handle(Event event) {
        this.handle((SendDiscussionMessageEvent) event) ;
    }


}
