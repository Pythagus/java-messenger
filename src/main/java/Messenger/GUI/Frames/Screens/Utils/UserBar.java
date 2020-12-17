package Messenger.GUI.Frames.Screens.Utils;

import java.awt.*;
import javax.swing.*;

import Messenger.Foundation.Models.User;
import Messenger.GUI.Frames.MainFrame;
import Messenger.GUI.Factories.FontFactory;

/**
 * @author Damien MOLINA
 */
abstract public class UserBar extends CardPanel {

    /**
     * Bar color.
     */
    public static final Color backgroundColor = new Color(235, 235, 235) ;

    /**
     * Main frame instance.
     */
    private final MainFrame frame ;

    /**
     * Bar list instance.
     */
    protected final UserList list ;

    /**
     * Make a contact bar instance.
     *
     * @param type : bar type.
     * @param frame : main graphic frame.
     * @param list : JList instance.
     * @param title : bar instance.
     */
    public UserBar(BarType type, MainFrame frame, UserList list, String title) {
        super(type.toString()) ;

        this.frame = frame ;
        this.list  = list ;

        this.initializeGraphics(title) ;
    }

    /**
     * Get the frame instance.
     *
     * @return the frame instance.
     */
    public MainFrame getFrame() {
        return this.frame ;
    }

    /**
     * Get the bar list.
     *
     * @return the bar list instance.
     */
    public UserList getList() {
        return this.list ;
    }

    /**
     * Graphically initialize the frame.
     */
    private void initializeGraphics(String title) {
        this.setLayout(new BorderLayout()) ;
        this.setBackground(UserBar.backgroundColor) ;

        // Bar title
        JLabel label = new JLabel(title) ;
        label.setFont(FontFactory.bold("Arial", 20)) ;
        label.setHorizontalAlignment(JLabel.CENTER) ;
        label.setBorder(
            BorderFactory.createEmptyBorder(40, 0, 40, 0)
        ) ;

        // Add the bar components.
        this.add(label, BorderLayout.PAGE_START) ;
        this.add(this.list) ;
    }

    /**
     * Make and add an item with the given
     * user.
     *
     * @param user : user instance.
     */
    public void addItem(User user) {
        this.list.addItem(user, false) ;
    }

    /**
     * Remove the item with the given
     * user.
     *
     * @param user : user instance.
     */
    public void removeItem(User user) {
        this.list.removeItem(user) ;
    }

}
