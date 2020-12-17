package Messenger.GUI.Frames.Screens.Contacts;

import Messenger.GUI.Frames.MainFrame;
import java.beans.PropertyChangeListener;
import Messenger.GUI.Frames.Screens.Utils.BarType;
import Messenger.GUI.Frames.Screens.Utils.UserBar;
import Messenger.Foundation.Observers.Listeners.UserListUpdated;

/**
 * @author Damien MOLINA
 */
public class ContactBar extends UserBar {

    private PropertyChangeListener loadedListener ;

    /**
     * Make a contact bar instance.
     *
     * @param frame : main graphic frame.
     */
    public ContactBar(MainFrame frame) {
        super(BarType.CONTACTS, frame, new ContactList(frame), "Personnes connectées") ;

       // this.loadListeners() ;
    }

    /**
     * Load the contact bar listeners.
     */
    private void loadListeners() {
        this.loadedListener = event -> {
            System.out.println(event) ;
            UserListUpdated.updateUI() ;
            ContactBar.this.removePropertyChangeListener(ContactBar.this.loadedListener) ;
        } ;

        this.addPropertyChangeListener(this.loadedListener) ;
    }


}
