package Messenger.GUI.Subscreens;

import Messenger.Foundation.Env;
import Messenger.Foundation.Models.Messages.Message;
import Messenger.GUI.Layout.Items.Discussion.uiDiscussionScrollBar;
import Messenger.GUI.Layout.Items.Discussion.uiDiscussionFooter;
import Messenger.GUI.Layout.Items.Discussion.uiDiscussionHeader;
import Messenger.GUI.Layout.Items.Discussion.uiDiscussionMessage;
import Messenger.GUI.Layout.SideBar;
import javax.swing.*;
import java.awt.*;

/**
 * @author Damien MOLINA
 */
public class uiDiscussion extends SubScreen {

    // Main discussion color.
    public static final Color backgroundColor = SideBar.backgroundColor ;

    /**
     * Messages content.
     */
    private JPanel content ;

    /**
     * Make a new instance of uiDiscussion.
     */
    public uiDiscussion() {
        this.initializeComponentGraphics() ;
    }

    /**
     * Initialize graphically the component
     */
    private void initializeComponentGraphics() {
        this.setLayout(new BorderLayout()) ;

        // Add the component elements.
        this.add(new uiDiscussionHeader(), BorderLayout.PAGE_START) ;
        this.add(this.graphicContentContainer(), BorderLayout.CENTER) ;
        this.add(new uiDiscussionFooter(), BorderLayout.PAGE_END) ;
    }

    /**
     * Generate the container of the messages.
     *
     * @return a JScrollPane instance.
     */
    private JScrollPane graphicContentContainer() {
        JScrollPane scroll = new JScrollPane(this.graphicContent()) ;
        scroll.setBorder(null) ;
        scroll.setBackground(null) ;

        JScrollBar sb = scroll.getVerticalScrollBar();
        sb.setUI(new uiDiscussionScrollBar());

        // Reduce the bar width.
        sb.setPreferredSize(new Dimension(7, Integer.MAX_VALUE));

        // Increase speed.
        sb.setUnitIncrement(16) ;

        return scroll ;
    }

    /**
     * Generate a JPanel instance containing
     * the discussion messages.
     *
     * @return the JPanel generated.
     */
    private JPanel graphicContent() {
        this.content = new JPanel(new GridLayout(0, 1, 5, 5)) ;
        this.content.setBackground(uiDiscussion.backgroundColor) ;

        uiDiscussionMessage[] list = this.getDiscussionMessages() ;
        for(uiDiscussionMessage msg : list) {
            this.content.add(msg) ;
        }

        JPanel container = new JPanel(new BorderLayout()) ;
        container.add(this.content, BorderLayout.NORTH) ;
        container.setBackground(uiDiscussion.backgroundColor) ;

        return container ;
    }

    /**
     * Add the message.
     *
     * @param message : message instance.
     */
    public void addMessage(Message message) {
        this.content.add(
            new uiDiscussionMessage(
                message.getSender().equals(Env.getUser()) ? uiDiscussionMessage.Side.RIGHT : uiDiscussionMessage.Side.LEFT, message
            )
        ) ;

        this.content.revalidate() ;
        this.content.repaint() ;
    }


    // TODO
    private uiDiscussionMessage[] getDiscussionMessages() {
        return new uiDiscussionMessage[0] ;
    }

}
