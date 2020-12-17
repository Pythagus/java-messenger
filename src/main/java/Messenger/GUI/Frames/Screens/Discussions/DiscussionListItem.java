package Messenger.GUI.Frames.Screens.Discussions;

import Messenger.Foundation.Models.User;
import Messenger.GUI.Frames.Screens.DiscussionScreen;
import Messenger.GUI.Frames.Screens.Utils.ContentType;
import Messenger.GUI.Frames.Screens.Utils.UserListItem;
import Messenger.Foundation.Controllers.ConversationController;

/**
 * @author Damien MOLINA
 */
public class DiscussionListItem extends UserListItem {

    /**
     * Make a new DiscussionListItem instance.
     *
     * @param list : parent list.
     * @param user : user instance.
     */
    public DiscussionListItem(DiscussionList list, User user) {
        super(list, user) ;
    }

    /**
     * When the item is selected.
     */
    public void selected() {
        DiscussionScreen screen = (DiscussionScreen) this.list.getFrame().getContent(ContentType.DISCUSSION) ;
        screen.setConversation(
            ConversationController.instance().getConversation(this.user)
        ) ;
    }

}
