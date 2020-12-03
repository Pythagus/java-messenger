package Messenger.Foundation.Observers.Listeners.Message;

import Messenger.GUI.Screens.uiWindow;
import Messenger.Foundation.Environment;
import Messenger.Foundation.Observers.BaseListener;
import Messenger.Foundation.Models.Messages.Concerns.MessageSound;

/**
 * @author Damien MOLINA
 */
public class SendMessageListener extends BaseListener {

    /**
     * Notify the listener with the given arguments.
     *
     * @param args : notification arguments.
     */
    @Override
    public void handle(Object... args) {
        String data = (String) args[0];

        //TODO : send the message
        System.out.println("Message à envoyer : " + data) ;

        this.updateGraphics(data) ;
        MessageSound.play() ;
    }

    /**
     * Update the graphics regarding the
     * sent data
     *
     * @param data : sent data.
     */
    private void updateGraphics(String data) {
        try {
            uiWindow uiWindow = (uiWindow) Environment.getApplication().getGraphicFrame().getScreen() ;
            //uiWindow.getDiscussionBar().updateItem() ;
            //TODO : continue
        } catch (Exception e) {
            e.printStackTrace() ;
        }
    }

}
