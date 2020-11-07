package Messenger.Foundation.Observers.Listeners;

import Messenger.GUI.Events.DiscussionMessageEntered;
import Messenger.Foundation.Observers.Listener;
import Messenger.Foundation.Observers.Event;

/**
 * @author Damien MOLINA
 */
public class SendDiscussionMessage extends Listener {

    /**
     * Handle the given event.
     *
     * @param event : event to handle.
     */
    public void handle(DiscussionMessageEntered event) {
        System.out.println("Send the message : " + event.getData()) ;

        //TODO
    }

    /**
     * Handle the given event.
     *
     * @param event : event to handle.
     */
    @Override
    public void handle(Event event) {
        this.handle((DiscussionMessageEntered) event) ;
    }

}
