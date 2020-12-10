package Messenger.GUI.Layout.Items.Contact;

import Messenger.Foundation.Models.User;
import Messenger.GUI.Layout.Items.uiVerticalBarItem;
import Messenger.GUI.Layout.Concerns.Actions.ContactItemClicked;

/**
 * @author Damien MOLINA
 */
public class uiContactItem extends uiVerticalBarItem {


    /**
     * Make a new graphic instance of conversation.
     *
     * @param user : user instance.
     * @param picture : file name.
     */
    public uiContactItem(User user, String picture) {
        super(user, picture) ;

        this.addMouseListener(new ContactItemClicked(this)) ;
    }

    /**
     * Make a new graphic instance of conversation.
     *
     * @param user : user instance.
     */
    public uiContactItem(User user) {
        this(user, "francois_hollande.png") ;
    }

}
