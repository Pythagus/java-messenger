package Messenger.Foundation.Observers.Events;

import Messenger.GUI.Events.DiscussionMessageEntered;

/**
 * @author Damien MOLINA
 */
public class DiscussionMessageSent extends DiscussionMessageEntered {

    /**
     * Make a new event instance.
     *
     * @param message : discussion input.
     */
    public DiscussionMessageSent(String message) {
        super(message) ;
    }

}
