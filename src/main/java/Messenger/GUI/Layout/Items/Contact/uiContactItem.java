package Messenger.GUI.Layout.Items.Contact;

import Messenger.Foundation.Models.User;
import Messenger.GUI.Layout.Items.uiVerticalBarItem;

/**
 * @author Damien MOLINA
 */
public class uiContactItem extends uiVerticalBarItem {

    /**
     * User instance.
     */
    private final User user ;

    /**
     * Make a new graphic instance of conversation.
     *
     * @param user : user instance.
     * @param picture : file name.
     */
    public uiContactItem(User user, String picture) {
        super(picture) ;

        this.user = user ;
        this.setContent(user.getIdentifier()) ;
    }

    /**
     * Get the user instance.
     *
     * @return the user instance.
     */
    public User getUser() {
        return this.user ;
    }

}
