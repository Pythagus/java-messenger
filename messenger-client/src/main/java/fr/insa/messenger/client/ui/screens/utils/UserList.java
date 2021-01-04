package fr.insa.messenger.client.ui.screens.utils;

import java.awt.*;
import javax.swing.*;
import fr.insa.messenger.client.models.User;
import fr.insa.messenger.client.utils.ColorUtils;
import fr.insa.messenger.client.ui.utils.MyJList;
import fr.insa.messenger.client.ui.frames.MainFrame;
import fr.insa.messenger.client.ui.utils.MyJListItem;

/**
 * @author Damien MOLINA
 */
abstract public class UserList extends MyJList<UserListItem> {

    /**
     * Main frame instance.
     */
    private final MainFrame frame ;

    /**
     * Make a contact list instance.
     *
     * @param frame : main frame.
     */
    public UserList(MainFrame frame) {
        super() ;

        this.frame = frame ;

        this.initializeGraphics() ;
    }

    /**
     * Make a new item instance regarding
     * the given user instance.
     *
     * @param user : user instance.
     */
    abstract protected UserListItem makeItem(User user) ;

    /**
     * Get the main frame instance.
     *
     * @return the frame instance.
     */
    public MainFrame getFrame() {
        return this.frame ;
    }

    /**
     * Make and add an item with the given
     * user.
     *
     * @param user : user instance.
     * @param select : whether the item should be selected.
     */
    public void addItem(User user, boolean select) {
        UserListItem item = this.makeItem(user) ;

        this.addItem(item) ;

        if(select) {
            try {
                this.setSelectedValue(item, true) ;
            } catch (Exception e) {
                e.printStackTrace() ;
            }
        }
    }

    /**
     * Remove the item identified by
     * the given user instance.
     *
     * @param user : user instance.
     */
    public void removeItem(User user) {
        int index = this.indexOf(user) ;

        if(index >= 0) {
            this.removeItemAt(index) ;
        }
    }

    /**
     * Get the index of the given item.
     *
     * @param user : user to find.
     * @return the index, -1 if the user wasn't find.
     */
    public int indexOf(User user) {
        for (int a = 0; a < this.getModel().getSize(); a++) {
            UserListItem item2 = this.getModel().getElementAt(a) ;
            if (item2.getUser().equals(user))  {
                return a ;
            }
        }

        return -1 ;
    }

    /**
     * Graphically initialize the frame.
     */
    private void initializeGraphics() {
        this.setBackground(ColorUtils.TRANSPARENT) ;
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION) ;
        this.setLayoutOrientation(JList.VERTICAL) ;
        this.setVisibleRowCount(10) ;
        this.setModel(this.model) ;

        // Add the rendering listener.
        this.setCellRenderer(new ItemRenderer()) ;
    }

    /**
     * UserListItem renderer. This is responsible
     * of the rendering of the item.
     *
     * @author Damien MOLINA
     */
    private static class ItemRenderer implements ListCellRenderer<MyJListItem> {

        /**
         * Render the panel.
         *
         * @param list : list.
         * @param o : object to render.
         * @param i : object's index in the list.
         * @param selected : is selected.
         * @param b1 : is focused.
         * @return the rendered element.
         */
        public Component getListCellRendererComponent(JList<? extends MyJListItem> list, MyJListItem o, int i, boolean selected, boolean b1) {
            o.setBackground(selected ? ContentScreen.backgroundColor : UserBar.backgroundColor) ;

            return o ;
        }
    }

}
