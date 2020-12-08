package Messenger.Foundation.Observers.Listeners.Message;

import Messenger.GUI.Layout.RightSide;
import Messenger.GUI.Screens.uiWindow;
import Messenger.Foundation.System.Env;
import Messenger.GUI.Subscreens.uiDiscussion;
import Messenger.Foundation.Observers.BaseListener;
import Messenger.Foundation.Models.Messages.Message;
import Messenger.GUI.Layout.Concerns.VerticalBarType;
import Messenger.GUI.Layout.Items.Discussion.uiDiscussionBar;
import Messenger.Foundation.Controllers.ConversationController;

/**
 * @author Damien MOLINA
 */
public class SendMessageListener extends BaseListener {

    /**
     * Notify the listener with the given arguments.
     *
     * @param args : notification arguments.
     */
    public void handle(Object... args) {
        Message message = (Message) args[0] ;

        ConversationController controller = (ConversationController) Env.getController(ConversationController.class) ;
        controller.send(message) ;

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
            uiWindow uiWindow   = (uiWindow) Env.getApplication().getGraphicFrame().getScreen() ;
            uiDiscussionBar bar = (uiDiscussionBar) uiWindow.getVerticalBar(VerticalBarType.DISCUSSION) ;
            bar.updateActiveItem(message.getData()) ;

            uiDiscussion discussion = (uiDiscussion) uiWindow.getRightSide().getSubScreen(RightSide.SubScreenType.Discussion) ;
            discussion.addMessage(message) ;
        } catch (Exception e) {
            e.printStackTrace() ;
        }
    }

}
