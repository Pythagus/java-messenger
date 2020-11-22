package Messenger.Foundation.Observers.Listeners;

import Messenger.GUI.Events.DiscussionMessageEntered;
import Messenger.Foundation.Observers.Listener;
import Messenger.Foundation.Observers.Event;
import Messenger.Foundation.Environment;
import Messenger.GUI.Screens.uiWindow;

/**
 * @author Damien MOLINA
 */
public class ManageDiscussionMessageSent extends Listener {

    /**
     * Data sent.
     */
    private String data ;

    /**
     * Handle the given event.
     *
     * @param event : event to handle.
     */
    public void handle(DiscussionMessageEntered event) {
        this.data = event.getData() ;

        System.out.println("Message sent : " + this.data) ;

        //TODO
        this.changeDiscussionItem() ;
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


    private void changeDiscussionItem() {
        try {
            uiWindow uiWindow = (uiWindow) Environment.getApplication().getGraphicFrame().getScreen() ;
            //uiWindow.getDiscussionBar().updateItem() ;
            //TODO : continue
        } catch (Exception e) {
            e.printStackTrace() ;
        }
    }

}
