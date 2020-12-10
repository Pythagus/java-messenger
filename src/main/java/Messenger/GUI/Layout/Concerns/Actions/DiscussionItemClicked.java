package Messenger.GUI.Layout.Concerns.Actions;

import Messenger.GUI.Layout.Items.Discussion.uiDiscussionMessage;
import Messenger.GUI.Screens.uiWindow;
import Messenger.GUI.Layout.RightSide;
import Messenger.Foundation.System.Env;
import Messenger.GUI.Subscreens.uiDiscussion;
import Messenger.GUI.Layout.Concerns.VerticalBarType;
import Messenger.GUI.Layout.Items.Discussion.uiDiscussionBar;
import Messenger.GUI.Layout.Items.Discussion.uiDiscussionItem;
import java.awt.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
    public void handle() {
        uiWindow uiWindow = (uiWindow) Env.getApplication().getGraphicFrame().getScreen() ;
        uiWindow.getRightSide().activeSubScreen(RightSide.SubScreenType.Discussion) ;

        uiDiscussion discussion = (uiDiscussion) uiWindow.getRightSide().getSubScreen() ;
        discussion.changeActiveConversation(this.item.getConversation()) ;

        uiDiscussionBar bar = (uiDiscussionBar) uiWindow.getVerticalBar(VerticalBarType.DISCUSSION) ;
        bar.setActiveItem(this.item) ;

        this.updateLastMessage(discussion, bar) ;
    }

    /**
     * Update the last message of the given discussion.
     *
     * @param discussion : discussion to update.
     * @param bar : discussion bar.
     */
    private void updateLastMessage(uiDiscussion discussion, uiDiscussionBar bar) {
        List<Component> components = Arrays.asList(
            discussion.getContent().getComponents()
        ) ;

        components.removeIf(c -> ! (c instanceof uiDiscussionMessage)) ;
        Collections.reverse(components) ;

        if(! components.isEmpty()) {
            try {
                uiDiscussionMessage message = (uiDiscussionMessage) components.get(0) ;

                bar.getActiveItem().setContent(
                    message.getMessage().getData().messageToPrint()
                ) ;
            } catch (Exception e) {
                e.printStackTrace() ;
            }
        }
    }

}
