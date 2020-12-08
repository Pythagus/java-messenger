package Messenger.GUI.Layout.Concerns.Actions;

import Messenger.GUI.Screens.uiWindow;
import Messenger.GUI.Layout.RightSide;
import Messenger.Foundation.System.Env;
import Messenger.GUI.Subscreens.uiDiscussion;
import Messenger.GUI.Layout.Concerns.VerticalBarType;
import Messenger.GUI.Layout.Items.Discussion.uiDiscussionBar;
import Messenger.GUI.Layout.Items.Discussion.uiDiscussionItem;

/**
 * Handler to the click event in
 * a discussion item.
 *
 * @author Damien MOLINA
 */
public class DiscussionItemClicked extends VerticalItemClicked<uiDiscussionItem> {

    /**
     * Make a new listener instance.
     *
     * @param item : clicked item.
     */
    public DiscussionItemClicked(uiDiscussionItem item) {
        super(item) ;
    }

    /**
     * Handle the event.
     */
    protected void handle() {
        uiWindow uiWindow = (uiWindow) Env.getApplication().getGraphicFrame().getScreen() ;
        uiWindow.getRightSide().activeSubScreen(RightSide.SubScreenType.Discussion) ;

        uiDiscussion discussion = (uiDiscussion) uiWindow.getRightSide().getSubScreen() ;
        discussion.changeActiveConversation(this.item.getConversation()) ;

        uiDiscussionBar bar = (uiDiscussionBar) uiWindow.getVerticalBar(VerticalBarType.DISCUSSION) ;
        bar.setActiveItem(this.item) ;
    }

}
