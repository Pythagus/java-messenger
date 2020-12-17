package fr.insa.messenger.ui.utils;

import javax.swing.*;
import java.awt.event.MouseEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * @author Damien MOLINA
 */
abstract public class MyJList<T extends MyJListItem> extends JList<T> {

    /**
     * List model.
     */
    protected final DefaultListModel<T> model ;

    /**
     * Make a contact list instance.
     */
    public MyJList() {
        this.model = new DefaultListModel<>() ;

        // Add the selection listener.
        this.addListSelectionListener(new ItemClickedHandler()) ;
    }

    /**
     * Get the list model.
     *
     * @return the model instance.
     */
    public DefaultListModel<T> getModel() {
        return this.model ;
    }

    /**
     * Add the given item to the list.
     *
     * @param item : item to add.
     */
    public void addItem(T item) {
        this.model.addElement(item) ;
    }

    /**
     * Remove the given item from the list.
     */
    public void removeItemAt(int rank) {
        this.model.removeElementAt(rank) ;
    }

    /**
     * Determine whether the given event
     * should be managed
     *
     * @param e : incoming event.
     * @return True or False
     */
    private boolean processEvent(MouseEvent e) {
        int index = this.locationToIndex(e.getPoint()) ;

        return index > -1 && this.getCellBounds(index, index).contains(e.getPoint()) ;
    }

    /**
     * Process a click event.
     *
     * @param e : event
     */
    protected void processMouseEvent(MouseEvent e) {
        if(processEvent(e)) {
            super.processMouseEvent(e) ;
        }
    }

    /**
     * Process a motion event.
     *
     * @param e : event
     */
    protected void processMouseMotionEvent(MouseEvent e) {
        if(processEvent(e)) {
            super.processMouseMotionEvent(e) ;
        }
    }

    /**
     * Selected item event handler.
     *
     * @author Damien MOLINA
     */
    private class ItemClickedHandler implements ListSelectionListener {

        /**
         * Handle a changed value event.
         * An item has been selected.
         *
         * @param event : generated event.
         */
        public void valueChanged(ListSelectionEvent event) {
            if(! event.getValueIsAdjusting()) {
                T item = MyJList.this.getSelectedValue() ;

                /*
                 * This test solves the multiple-time
                 * generated event.
                 */
                if(item != null) {
                    item.selected() ;
                }
            }
        }
    }

}
