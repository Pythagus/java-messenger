package Messenger.GUI.Events;

import Messenger.Foundation.Observers.Event;

/**
 * @author Damien MOLINA
 */
public class DiscussionMessageEntered extends Event {

    /**
     * uiDiscussion input.
     */
    private final String data ;

    /**
     * Make a new event instance.
     *
     * @param data : discussion input.
     */
    public DiscussionMessageEntered(String data) {
        this.data = data ;
    }

    /**
     * Get the discussion message.
     *
     * @return the string.
     */
    public String getData() {
        return this.data ;
    }

}
