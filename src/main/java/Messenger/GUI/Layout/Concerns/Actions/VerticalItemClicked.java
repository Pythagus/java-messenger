package Messenger.GUI.Layout.Concerns.Actions;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import Messenger.GUI.Layout.Items.uiVerticalBarItem;

/**
 * @author Damien MOLINA
 */
abstract public class VerticalItemClicked<T extends uiVerticalBarItem> extends MouseAdapter {

    /**
     * Clicked item.
     */
    protected final T item ;

    /**
     * Make a new listener instance.
     *
     * @param item : clicked item.
     */
    public VerticalItemClicked(T item) {
        this.item = item ;
    }

    /**
     * Handle the event.
     */
    abstract public void handle() ;

    /**
     * When the item is clicked.
     *
     * @param e : click event.
     */
    public void mouseClicked(MouseEvent e) {
        this.handle() ;
    }

}
