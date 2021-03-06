package fr.insa.messenger.client.ui.screens.contacts;

import fr.insa.messenger.client.models.User;
import fr.insa.messenger.client.ui.frames.MainFrame;
import fr.insa.messenger.client.ui.screens.utils.UserList;

/**
 * @author Damien MOLINA
 */
public class ContactList extends UserList {

    /**
     * Make a contact list instance.
     *
     * @param frame : main frame.
     */
    public ContactList(MainFrame frame) {
        super(frame) ;
    }

    /**
     * Make a new item instance regarding
     * the given user instance.
     *
     * @param user : user instance.
     */
    protected ContactListItem makeItem(User user) {
        return new ContactListItem(this, user) ;
    }

}
