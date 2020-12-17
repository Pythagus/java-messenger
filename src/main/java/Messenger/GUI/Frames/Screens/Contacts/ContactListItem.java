package Messenger.GUI.Frames.Screens.Contacts;

import Messenger.Foundation.Models.User;
import Messenger.GUI.Frames.Screens.Utils.UserListItem;
import Messenger.Foundation.Controllers.ConversationController;

/**
 * @author Damien MOLINA
 */
public class ContactListItem extends UserListItem {

    /**
     * Make a new UserListItem instance.
     *
     * @param list : parent list.
     * @param user : user instance.
     */
    public ContactListItem(ContactList list, User user) {
        super(list, user) ;
    }

    /**
     * When the item is selected.
     */
    public void selected() {
        ConversationController.instance().start(this.user) ;
    }

}
