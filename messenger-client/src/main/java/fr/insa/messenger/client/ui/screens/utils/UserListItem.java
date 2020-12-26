package fr.insa.messenger.client.ui.screens.utils;

import java.awt.*;
import javax.swing.*;

import fr.insa.messenger.client.utils.ColorUtils;
import fr.insa.messenger.client.models.User;
import fr.insa.messenger.client.ui.utils.MyJListItem;
import fr.insa.messenger.client.ui.factories.FontFactory;

/**
 * @author Damien MOLINA
 */
abstract public class UserListItem extends MyJListItem {

    /**
     * Content label.
     */
    private final JLabel content ;

    /**
     * Targeted User instance.
     */
    protected final User user ;

    /**
     * Targeted User instance.
     */
    protected final UserList list ;

    /**
     * Make a new UserListItem instance.
     *
     * @param list : parent list.
     * @param user : user instance.
     */
    public UserListItem(UserList list, User user) {
        this.user    = user ;
        this.list    = list ;
        this.content = new JLabel() ;

        this.initializeGraphics() ;
    }

    /**
     * Get the user instance.
     *
     * @return the user instance.
     */
    public User getUser() {
        return this.user ;
    }

    /**
     * Graphically initialize the frame.
     */
    private void initializeGraphics() {
        this.setBackground(ColorUtils.transparent()) ;
        this.setBorder(
            BorderFactory.createEmptyBorder(15, 0,15, 0)
        ) ;
        this.setCursor(new Cursor(Cursor.HAND_CURSOR)) ;

        // Prepare content label.
        this.content.setText(this.user.getPseudo()) ;
        this.content.setFont(FontFactory.normal("Arial", 20)) ;

        // Add the component.
        this.add(this.content) ;
    }

    /**
     * Determine whether the given object
     * equals the current instance.
     *
     * @param o : object to check.
     * @return true or false.
     */
    public boolean equals(Object o) {
        if(this == o) {
            return true ;
        }

        if(! (o instanceof UserListItem)) {
            return false ;
        }

        UserListItem that = (UserListItem) o ;

        return this.user.equals(that.getUser()) ;
    }

}
