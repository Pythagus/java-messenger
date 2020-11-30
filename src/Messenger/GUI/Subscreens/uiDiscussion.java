package Messenger.GUI.Subscreens;

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
        JPanel panel = new JPanel(new GridLayout(0, 1, 5, 5)) ;
        panel.setBackground(uiDiscussion.backgroundColor) ;

        uiDiscussionMessage[] list = this.getDiscussionMessages() ;
        for(uiDiscussionMessage msg : list) {
            panel.add(msg) ;
        }

        JPanel container = new JPanel(new BorderLayout()) ;
        container.add(panel, BorderLayout.NORTH) ;
        container.setBackground(uiDiscussion.backgroundColor) ;

        return container ;
    }




    // TODO : rechercher les messages dans l'historique
    private uiDiscussionMessage[] getDiscussionMessages() {
        //TODO : à refaire
        int nbr = 10 ;
        uiDiscussionMessage[] list = new uiDiscussionMessage[nbr] ;

        int i = 0 ;
        while(i < nbr) {
            uiDiscussionMessage msg = new uiDiscussionMessage(
                    i % 3 == 0 ? uiDiscussionMessage.Side.RIGHT : uiDiscussionMessage.Side.LEFT, i % 3 == 0
            ) ;

            list[i] = msg ;

            i++ ;
        }

        return list ;
    }

}
