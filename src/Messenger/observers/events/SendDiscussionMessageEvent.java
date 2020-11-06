package Messenger.observers.events;

import Messenger.observers.Event;

/**
 * @author Damien MOLINA
 */
public class SendDiscussionMessageEvent extends Event {

    /**
     * uiDiscussion input.
     */
    private final String message ;

    /**
     * Make a new event instance.
     *
     * @param message : discussion input.
     */
    public SendDiscussionMessageEvent(String message) {
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
