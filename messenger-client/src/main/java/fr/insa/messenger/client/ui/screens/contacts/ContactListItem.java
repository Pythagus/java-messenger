package fr.insa.messenger.client.ui.screens.contacts;

import fr.insa.messenger.client.models.User;
import fr.insa.messenger.client.ui.screens.utils.UserListItem;
import fr.insa.messenger.client.controllers.ConversationController;

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
