package Messenger.Foundation.Observers.Listeners;

import Messenger.Foundation.Observers.Events.DiscussionMessageSent;
import Messenger.GUI.Events.DiscussionMessageEntered;
import Messenger.Foundation.Managers.EventManager;
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
        String data = event.getData() ;
        System.out.println("Send the message : " + data) ;

        //TODO : send the message.

        EventManager.fire(new DiscussionMessageSent(data)) ;
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
