package fr.insa.messenger.client.ui.screens.utils;

import java.awt.*;
import javax.swing.*;
import fr.insa.messenger.client.models.User;
import fr.insa.messenger.client.utils.ColorUtils;
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
     * Additional label.
     */
    protected final JLabel additionalLabel ;

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
        this.additionalLabel = this.additionalLabel() ;

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
        this.setBackground(ColorUtils.TRANSPARENT) ;
        this.setBorder(
            BorderFactory.createEmptyBorder(15, 0,15, 0)
        ) ;
        this.setCursor(new Cursor(Cursor.HAND_CURSOR)) ;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)) ;

        // Prepare content label.
        this.content.setText(this.user.getPseudo()) ;
        this.content.setHorizontalAlignment(SwingConstants.CENTER) ;
        this.content.setFont(FontFactory.normal("Arial", 20)) ;

        // Add the component.
        this.add(this.content) ;

        if(this.additionalLabel != null) {
            this.additionalLabel.setHorizontalAlignment(SwingConstants.CENTER) ;
            this.add(this.additionalLabel) ;
        }
    }

    /**
     * Generate a additional content label.
     *
     * @return a JLabel instance.
     */
    protected JLabel additionalLabel() {
        return null ;
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
