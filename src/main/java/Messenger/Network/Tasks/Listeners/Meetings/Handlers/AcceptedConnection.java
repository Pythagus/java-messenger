package Messenger.Network.Tasks.Listeners.Meetings.Handlers;

import Messenger.GUI.Screens.uiWindow;
import Messenger.Foundation.System.Env;
import Messenger.Foundation.Models.User;
import Messenger.Foundation.Models.Conversation;
import Messenger.Foundation.System.Console.Console;
import Messenger.Foundation.Controllers.UserController;
import Messenger.GUI.Layout.Items.Discussion.uiDiscussionItem;

/**
 * @author Damien MOLINA
 */
public class AcceptedConnection {

    /**
     * Run the listener.
     */
    public void accepted(User user) {
        if(Env.getApplication().isDebugMode()) {
            Console.comment("=> Accepted user : " + user.getPseudo()) ;
        }

        this.getUserController().addUser(user) ;

        Conversation conversation = new Conversation(user) ;
        uiDiscussionItem conv = new uiDiscussionItem(conversation, "francois_hollande.png") ; // TODO : change the picture

        uiWindow uiWindow = (uiWindow) Env.getApplication().getGraphicFrame().getScreen() ;
        uiWindow.getDiscussionBar().addDiscussionItem(conv) ;
    }

    /**
     * Get the User Controller.
     *
     * @return the UserController instance.
     */
    private UserController getUserController() {
        return (UserController) Env.getController(UserController.class) ;
    }

}
