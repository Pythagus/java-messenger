package Messenger.GUI.Events;

import Messenger.Foundation.Observers.Event;

/**
 * @author Damien MOLINA
 */
public class DiscussionMessageEntered extends Event {

    /**
     * uiDiscussion input.
     */
    private final String message ;

    /**
     * Make a new event instance.
     *
     * @param message : discussion input.
     */
    public DiscussionMessageEntered(String message) {
        this.message = message ;
    }

    /**
     * Get the discussion message.
     *
     * @return the string.
     */
    public String getData() {
        return this.message ;
    }

}
