package fr.insa.messenger.client.ui.screens.discussions;

import javax.swing.*;
import fr.insa.messenger.client.models.User;
import fr.insa.messenger.tools.models.UserStatus;
import fr.insa.messenger.client.ui.screens.DiscussionScreen;
import fr.insa.messenger.client.ui.screens.utils.ContentType;
import fr.insa.messenger.client.ui.screens.utils.UserListItem;
import fr.insa.messenger.client.controllers.ConversationController;

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

    /**
     * Generate a additional content label.
     *
     * @return a JLabel instance.
     */
    protected JLabel additionalLabel() {
        return new JLabel(
            UserStatus.translate(UserStatus.CONNECTED)
        ) ;
    }

    /**
     * Set the item status.
     *
     * @param status : User status.
     */
    public void setStatus(UserStatus status) {
        this.additionalLabel.setText(status.toString()) ;
    }

}
