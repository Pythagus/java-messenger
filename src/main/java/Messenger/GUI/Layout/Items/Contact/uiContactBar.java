package Messenger.GUI.Layout.Items.Contact;

import Messenger.Foundation.Models.User;
import Messenger.GUI.Layout.Items.uiVerticalBar;

/**
 * @author Damien MOLINA
 */
public class uiContactBar extends uiVerticalBar<uiContactItem> {

    /**
     * Make a new instance of the contact bar.
     */
    public uiContactBar() {
        super("Utilisateurs joignables") ;
    }

    /**
     * Determine whether the given items
     * are equal.
     *
     * @param o1 : first item.
     * @param o2 : second item.
     * @return True or False.
     */
    protected boolean equalItems(uiContactItem o1, uiContactItem o2) {
        return this.fromUser(o1, o2.getUser()) ;
    }

    /**
     * Determine whether the given item
     * was made by the given user.
     *
     * @param o1   : first item.
     * @param user : user instance.
     * @return True or False.
     */
    protected boolean fromUser(uiContactItem o1, User user) {
        return o1.getUser().equals(user) ;
    }

}
