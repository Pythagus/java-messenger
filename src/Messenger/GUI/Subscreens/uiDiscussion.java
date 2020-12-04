package Messenger.GUI.Subscreens;

import java.awt.*;
import javax.swing.*;
import Messenger.Foundation.Env;
import Messenger.GUI.Layout.SideBar;
import Messenger.Foundation.Models.User;
import Messenger.Foundation.Models.Conversation;
import Messenger.Foundation.Models.Messages.Message;
import Messenger.Foundation.Controllers.ConversationController;
import Messenger.GUI.Layout.Items.Discussion.uiDiscussionFooter;
import Messenger.GUI.Layout.Items.Discussion.uiDiscussionHeader;
import Messenger.GUI.Layout.Items.Discussion.uiDiscussionMessage;
import Messenger.GUI.Layout.Items.Discussion.uiDiscussionScrollBar;

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
     * Active conversation.
     */
    private Conversation activeConversation ;

    /**
     * Discussion header.
     */
    private final uiDiscussionHeader header ;

    /**
     * Make a new instance of uiDiscussion.
     */
    public uiDiscussion() {
        this.header = new uiDiscussionHeader() ;

        this.initializeComponentGraphics() ;
    }

    /**
     * Add the message.
     *
     * @param message : message instance.
     */
    public void addMessage(Message message) {
        this.addMessage(message, true) ;
    }

    /**
     * Change the active conversation.
     *
     * @param conversation : new active conversation.
     */
    public void changeActiveConversation(Conversation conversation) {
        if(this.activeConversation != null && this.activeConversation.equals(conversation)) {
            return ;
        }

        this.activeConversation = conversation ;
        this.header.updateConversation(conversation) ;

        this.content.removeAll() ;

        this.addMessages(
            this.getDiscussionMessages(conversation.getTarget())
        ) ;

        this.revalidate() ;
        this.repaint() ;
    }

    /**
     * Get the active conversation instance.
     *
     * @return the active conversation instance.
     */
    public Conversation getActiveConversation() {
        return this.activeConversation ;
    }

    /**
     * Add the given message and revalidate the content
     * only if revalidate is true.
     *
     * @param message : message to add.
     * @param revalidate : determine whether the content
     *                     will be repainted.
     */
    private void addMessage(Message message, boolean revalidate) {
        this.content.add(
            new uiDiscussionMessage(
                message.getSender().equals(Env.getUser())
                    ? uiDiscussionMessage.Side.RIGHT
                    : uiDiscussionMessage.Side.LEFT
                , message
            )
        ) ;

        if(revalidate) {
            this.revalidateContent() ;
        }
    }

    /**
     * Add the given messages and repaint
     * the content.
     *
     * @param messages : messages list.
     */
    public void addMessages(Message[] messages) {
        for(Message message : messages) {
            this.addMessage(message, false) ;
        }

        this.revalidateContent() ;
    }

    /**
     * Revalidate and repaint the content.
     */
    public void revalidateContent() {
        this.content.revalidate() ;
        this.content.repaint() ;
    }

    /**
     * Get the discussion message for the given
     * user.
     *
     * @param user : user instance.
     * @return the messages list.
     */
    private Message[] getDiscussionMessages(User user) {
        return new ConversationController().getHistoric(user) ;
    }

    /**
     * Initialize graphically the component
     */
    private void initializeComponentGraphics() {
        this.setLayout(new BorderLayout()) ;

        // Add the component elements.
        this.add(this.header, BorderLayout.PAGE_START) ;
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

        JPanel container = new JPanel(new BorderLayout()) ;
        container.add(this.content, BorderLayout.NORTH) ;
        container.setBackground(uiDiscussion.backgroundColor) ;

        return container ;
    }

}
