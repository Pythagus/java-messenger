package fr.insa.messenger.ui.screens.discussions;

import fr.insa.messenger.models.User;
import fr.insa.messenger.ui.screens.DiscussionScreen;
import fr.insa.messenger.ui.screens.utils.ContentType;
import fr.insa.messenger.ui.screens.utils.UserListItem;
import fr.insa.messenger.controllers.ConversationController;

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
