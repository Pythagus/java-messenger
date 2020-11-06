package Messenger.observers.listeners;

import Messenger.observers.Event;
import Messenger.observers.Listener;
import Messenger.observers.events.SendDiscussionMessageEvent;

/**
 * @author Damien MOLINA
 */
public class SendDiscussionMessageListener extends Listener {

    /**
     * Handle the given event.
     *
     * @param event : event to handle.
     */
    public void handle(SendDiscussionMessageEvent event) {
        System.out.println("Send the message : " + event.getData()) ;

    }

    /**
     * Handle the given event.
     *
     * @param event : event to handle.
     */
    @Override
    public void handle(Event event) {
        this.handle((SendDiscussionMessageEvent) event) ;
    }

}
