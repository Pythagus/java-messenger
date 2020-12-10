package Messenger.GUI.Layout.Concerns.Actions;

import Messenger.GUI.Layout.RightSide;
import Messenger.GUI.Screens.uiWindow;
import Messenger.Foundation.System.Env;
import Messenger.Foundation.Models.Conversation;
import Messenger.GUI.Layout.Concerns.VerticalBarType;
import Messenger.GUI.Layout.Items.Contact.uiContactItem;
import Messenger.GUI.Layout.Items.Discussion.uiDiscussionBar;
import Messenger.GUI.Layout.Items.Discussion.uiDiscussionItem;

/**
 * Handler to the click event in
 * a discussion item.
 *
 * @author Damien MOLINA
 */
public class ContactItemClicked extends VerticalItemClicked<uiContactItem> {

    /**
     * Make a new listener instance.
     *
     * @param item : clicked item.
     */
    public ContactItemClicked(uiContactItem item) {
        super(item) ;
    }

    /**
     * Handle the event.
     */
    protected void handle() {
        uiWindow uiWindow = (uiWindow) Env.getApplication().getGraphicFrame().getScreen() ;
        uiWindow.getRightSide().activeSubScreen(RightSide.SubScreenType.Discussion) ;

       /* uiDiscussion discussion = (uiDiscussion) uiWindow.getRightSide().getSubScreen() ;
        discussion.changeActiveConversation(
            new Conversation(this.item.getUser())
        ) ;*/

        uiDiscussionBar bar = (uiDiscussionBar) uiWindow.getVerticalBar(VerticalBarType.DISCUSSION) ;
        uiWindow.activeBar(VerticalBarType.DISCUSSION) ;
        uiDiscussionItem conversation = new uiDiscussionItem(
            new Conversation(this.item.getUser())
        ) ;
        bar.addItem(conversation) ;
        bar.setActiveItem(conversation) ;
    }

}
