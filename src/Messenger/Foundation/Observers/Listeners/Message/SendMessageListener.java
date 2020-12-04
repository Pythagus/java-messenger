package Messenger.Foundation.Observers.Listeners.Message;

import Messenger.Foundation.Env;
import Messenger.GUI.Layout.RightSide;
import Messenger.GUI.Screens.uiWindow;
import Messenger.GUI.Subscreens.uiDiscussion;
import Messenger.Foundation.Observers.BaseListener;
import Messenger.Foundation.Models.Messages.Message;

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
        Message message = (Message) args[0] ;

        //TODO : send the message
        System.out.println("Message à envoyer : " + message.getData().getText()) ;

        this.updateGraphics(message) ;
    }

    /**
     * Update the graphics regarding the
     * sent data
     *
     * @param message : sent message.
     */
    private void updateGraphics(Message message) {
        try {
            uiWindow uiWindow = (uiWindow) Env.getApplication().getGraphicFrame().getScreen() ;
            uiWindow.getDiscussionBar().updateActiveItem(message.getData()) ;

            uiDiscussion discussion = (uiDiscussion) uiWindow.getRightSide().getSubScreen(RightSide.SubScreenType.Discussion) ;
            discussion.addMessage(message) ;
        } catch (Exception e) {
            e.printStackTrace() ;
        }
    }

}
