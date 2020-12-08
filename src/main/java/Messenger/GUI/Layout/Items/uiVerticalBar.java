package Messenger.GUI.Layout.Items;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import Messenger.Foundation.Models.User;
import Messenger.GUI.Exceptions.ConversationItemNotFoundException;

/**
 * @author Damien MOLINA
 */
abstract public class uiVerticalBar<T extends uiVerticalBarItem> extends JPanel {

    /**
     * Bar main color
     */
    public static final Color backgroundColor = new Color(245, 246, 245) ;

    /**
     * Discussion UI list.
     */
    protected final ArrayList<T> items ;

    /**
     * Active item instance.
     */
    protected T activeItem ;

    /**
     * The discussions list panel.
     */
    protected final JPanel list ;

    /**
     * Bar title.
     */
    private final String title ;

    /**
     * Make a new instance of the vertical bar.
     */
    public uiVerticalBar(String title) {
        this.title      = title ;
        this.items      = new ArrayList<>() ;
        this.list       = this.graphicList() ;
        this.activeItem = null ;

        this.initializeComponentGraphics() ;
    }

    /**
     * Determine whether the given items
     * are equal.
     *
     * @param o1 : first item.
     * @param o2 : second item.
     * @return True or False.
     */
    abstract protected boolean equalItems(T o1, T o2) ;

    /**
     * Determine whether the given item
     * was made by the given user.
     *
     * @param o1 : first item.
     * @param user : user instance.
     * @return True or False.
     */
    abstract protected boolean fromUser(T o1, User user) ;

    /**
     * Make the conversation list.
     *
     * @return the JPanel generated.
     */
    private JPanel graphicList() {
        JPanel list = new JPanel() ;

        list.setPreferredSize(new Dimension(42, 700)) ;
        list.setBorder(null) ;
        list.setBackground(uiVerticalBar.backgroundColor) ;

        return list ;
    }

    /**
     * Make the conversation header.
     *
     * @return the JPanel generated.
     */
    protected JPanel graphicHeader() {
        JPanel header = new JPanel() ;

        header.setBackground(uiVerticalBar.backgroundColor) ;
        header.setPreferredSize(new Dimension(300, 80)) ;
        header.setLayout(new GridLayout(1, 0)) ;

        // Add the label.
        header.add(this.graphicHeaderTitle()) ;

        return header ;
    }

    /**
     * Make the conversation header title.
     *
     * @return the JLabel generated.
     */
    private JLabel graphicHeaderTitle() {
        JLabel title = new JLabel() ;

        title.setHorizontalAlignment(SwingConstants.CENTER) ;
        title.setText(this.title) ;
        title.setHorizontalTextPosition(SwingConstants.CENTER) ;
        title.setPreferredSize(new Dimension(490, 50)) ;

        Font f = new Font(null, Font.PLAIN, 24) ;
        title.setFont(f.deriveFont(Font.BOLD)) ;

        return title ;
    }

    /**
     * Make the conversation scroll pane.
     *
     * @return the JScrollPane generated.
     */
    private JScrollPane graphicScrollPane() {
        JScrollPane pane = new JScrollPane() ;

        pane.setAutoscrolls(true) ;
        pane.setPreferredSize(new Dimension(490, 100)) ;
        pane.setBorder(null) ;
        pane.setBackground(uiVerticalBar.backgroundColor) ;

        // Add the panel.
        pane.setViewportView(this.list) ;

        return pane ;
    }

    /**
     * Initialize graphically the component
     */
    private void initializeComponentGraphics() {
        this.setLayout(new BorderLayout()) ;

        // Add the header.
        this.add(this.graphicHeader(), BorderLayout.PAGE_START) ;

        // Add the list.
        this.add(this.graphicScrollPane(), BorderLayout.CENTER) ;
    }

    /**
     * Add the given item to the items list.
     *
     * @param item : T instance.
     */
    public void addItem(T item) {
        if(! this.items.contains(item)) {
            if(this.activeItem == null) {
                this.activeItem = item ;
            }

            this.items.add(item) ;
            this.list.add(item) ;
        }
    }

    /**
     * Remove the given item from the list.
     *
     * @param item : item instance.
     */
    public void removeItem(T item) {
        if(! this.items.contains(item)) {
            if(this.equalItems(this.activeItem, item)) {
                this.activeItem = null ;
            }

            this.items.remove(item) ;

            // TODO : remove the items from the DOM
        }
    }

    /**
     * Set the active item.
     *
     * @param item : active item.
     */
    public void setActiveItem(T item) {
        this.activeItem = this.items.get(
            this.items.indexOf(item)
        ) ;
    }

    /**
     * Get the active item.
     *
     * @return the UI discussion instance.
     * @throws ConversationItemNotFoundException : not found item.
     */
    public T getActiveItem() throws ConversationItemNotFoundException {
        for(T item : this.items) {
            if(this.equalItems(item, this.activeItem)) {
                return item ;
            }
        }

        throw new ConversationItemNotFoundException(
            ConversationItemNotFoundException.Type.ACTIVE
        ) ;
    }

    /**
     * Get the conversation UI instance.
     *
     * @param user : targeted user instance.
     * @return the UI instance.
     * @throws ConversationItemNotFoundException : no element found.
     */
    protected T getFromUser(User user) throws ConversationItemNotFoundException {
        for(T item : this.items) {
            if(this.fromUser(item, user)) {
                return item ;
            }
        }

        throw new ConversationItemNotFoundException(user) ;
    }

}
