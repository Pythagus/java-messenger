package fr.insa.messenger.ui.screens.contacts;

import fr.insa.messenger.ui.frames.MainFrame;
import fr.insa.messenger.ui.screens.utils.BarType;
import fr.insa.messenger.ui.screens.utils.UserBar;

/**
 * @author Damien MOLINA
 */
public class ContactBar extends UserBar<ContactList> {

    /**
     * Make a contact bar instance.
     *
     * @param frame : main graphic frame.
     */
    public ContactBar(MainFrame frame) {
        super(BarType.CONTACTS, frame, new ContactList(frame), "Personnes connectées") ;
    }

}
