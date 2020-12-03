package Messenger.GUI.Layout.Items.Discussion;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import Messenger.Foundation.Models.User;
import Messenger.Foundation.Models.Messages.MessageData;
import Messenger.GUI.Exceptions.ConversationItemNotFoundException;

/**
 * @author Damien MOLINA
 */
public class uiDiscussionBar extends JPanel {

    // Bar main color.
    public static final Color backgroundColor = new Color(245, 246, 245) ;

    // List of the discussions.
    private final JPanel list ;

    /**
     * Discussion UI list.
     */
    private final ArrayList<uiDiscussionItem> items ;

    /**
     * Active item instance.
     */
    private uiDiscussionItem activeItem ;

    /**
     * Make a new instance of the conversation bar.
     */
    public uiDiscussionBar() {
        this.items      = new ArrayList<>() ;
        this.list       = this.graphicList() ;
        this.activeItem = null ;

        this.initializeComponentGraphics() ;
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
     * Add the given item to the discussion items list.
     *
     * @param item : uiDiscussionItem instance.
     */
    public void addDiscussionItem(uiDiscussionItem item) {
        if(this.activeItem == null) {
            this.activeItem = item ;
        }

        this.items.add(item) ;
        this.list.add(item) ;
    }

    /**
     * Update the active item.
     *
     * @param data : sent data.
     */
    public void updateActiveItem(MessageData data) {
        try {
            uiDiscussionItem item = this.getActiveItem() ;

            if(data.hasFile()) {
                item.setContent("Vous avez envoyé un fichier") ;
            } else {
                item.setContent(data.getText()) ;
            }
        } catch (ConversationItemNotFoundException e) {
            e.printStackTrace() ;
        }
    }

    /**
     * Get the active item.
     *
     * @return the UI discussion instance.
     * @throws ConversationItemNotFoundException : not found item.
     */
    public uiDiscussionItem getActiveItem() throws ConversationItemNotFoundException {
        for(uiDiscussionItem item : this.items) {
            if(item.getConversation().equals(this.activeItem.getConversation())) {
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
    public uiDiscussionItem getFromUser(User user) throws ConversationItemNotFoundException {
        /*for(uiDiscussionItem item : this.items) {
            if(item.getConversation().getTarget().equals(user)) {
                return item ;
            }
        }*/

        throw new ConversationItemNotFoundException(user) ;
    }

    /**
     * Make the conversation header.
     *
     * @return the JPanel generated.
     */
    private JPanel graphicHeader() {
        JPanel header = new JPanel() ;

        header.setBackground(uiDiscussionBar.backgroundColor) ;
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
        title.setText("Discussions") ;
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
        pane.setBackground(uiDiscussionBar.backgroundColor) ;

        // Add the panel.
        pane.setViewportView(this.list) ;

        return pane ;
    }

    /**
     * Make the conversation list.
     *
     * @return the JPanel generated.
     */
    private JPanel graphicList() {
        JPanel list = new JPanel() ;

        list.setPreferredSize(new Dimension(42, 705)) ;
        list.setBorder(null) ;
        list.setBackground(uiDiscussionBar.backgroundColor) ;

        return list ;
    }

}
