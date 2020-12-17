package Messenger.GUI.Frames.Screens.Contacts;

import Messenger.GUI.Frames.MainFrame;
import Messenger.Foundation.Models.User;
import Messenger.GUI.Frames.Screens.Utils.UserList;

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
