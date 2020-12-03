package Messenger.GUI.Screens;

import java.awt.*;
import javax.swing.*;
import Messenger.GUI.Layout.*;
import Messenger.LauncherTest;
import Messenger.Foundation.Models.Conversation;
import Messenger.GUI.Layout.Items.Discussion.uiDiscussionBar;
import Messenger.GUI.Layout.Items.Discussion.uiDiscussionItem;

/**
 * @author Damien MOLINA
 */
public class uiWindow extends Screen {

    /**
     * Right side instance.
     */
    private final RightSide rightSide ;

    /**
     * Discussion bar.
     */
    private final uiDiscussionBar discussionBar ;

    /**
     * Create a graphical instance
     * for the Messenger.
     */
    public uiWindow() {
        // Initialise the graphic components.
        this.discussionBar = new uiDiscussionBar() ;
        this.rightSide     = new RightSide() ;

        this.initializeComponentGraphics() ;
        this.setAllDiscussions() ;
    }

    /**
     * Set the discussions into the discussion bar.
     */
    private void setAllDiscussions() {
        // TODO : make the DB call to get all discussions.

        Conversation conversation = new Conversation(LauncherTest.getUser()) ;
        uiDiscussionItem conv = new uiDiscussionItem(conversation, "francois_hollande.png") ;

        this.discussionBar.addDiscussionItem(conv) ;
    }

    /**
     * Initialize graphically the component.
     */
    private void initializeComponentGraphics() {
        JPanel container = new JPanel() ;
        container.setLayout(new BorderLayout()) ;

        // Add the components.
        container.add(new SideBar(this.rightSide), BorderLayout.WEST) ;
        container.add(this.discussionBar, BorderLayout.CENTER) ;

        this.setLayout(new BorderLayout()) ;
        this.add(container, BorderLayout.LINE_START) ;
        this.add(this.rightSide, BorderLayout.CENTER) ;
    }

    /**
     * Get the right side instance.
     *
     * @return the right side instance.
     */
    public RightSide getRightSide() {
        return this.rightSide ;
    }

    /**
     * Get the discussion bar instance.
     *
     * @return the uiDiscussionBar instance.
     */
    public uiDiscussionBar getDiscussionBar() {
        return this.discussionBar ;
    }

}
