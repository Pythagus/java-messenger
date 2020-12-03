package Messenger.GUI.Layout.Items.Discussion;

import Messenger.Foundation.System.Assets.ImageAsset;
import Messenger.Foundation.Models.Conversation;
import Messenger.GUI.Layout.RightSide;
import Messenger.GUI.Screens.uiWindow;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import Messenger.Foundation.Env;
import javax.swing.*;
import java.awt.*;

/**
 * @author Damien MOLINA
 */
public class uiDiscussionItem extends JPanel {

    /**
     * Max size of the displayed content.
     */
    private static final int MAX_CONTENT_SIZE = 50 ;

    // uiDiscussionItem picture.
    private final String picture ;

    // uiDiscussion identifier.
    private final Conversation conversation ;

    /**
     * Content label instance.
     */
    private final JLabel contentLabel ;

    /**
     * Make a new graphic instance of conversation.
     *
     * @param picture : file name.
     */
    public uiDiscussionItem(Conversation conversation, String picture) {
        this.picture      = picture ;
        this.conversation = conversation ;
        this.contentLabel = this.graphicContentLabel() ;
        this.setContent("") ;

        this.initializeComponentGraphics() ;
    }

    /**
     * Set the content of the item.
     *
     * @param content : item content.
     */
    public void setContent(String content) {
        String c = content ;

        if(c.length() > MAX_CONTENT_SIZE) {
            c = c.substring(0, MAX_CONTENT_SIZE) + "..." ;
        }

        this.contentLabel.setText("<html>" + c + "</html>") ;
    }

    /**
     * Get the conversation.
     *
     * @return Conversation instance.
     */
    public Conversation getConversation() {
        return this.conversation ;
    }

    /**
     * Initialize graphically the component
     */
    private void initializeComponentGraphics() {
        this.setPreferredSize(new Dimension(500, 80)) ;
        this.setBackground(new Color(255, 255, 255)) ;
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS)) ;
        this.setBorder(
                BorderFactory.createMatteBorder(0, 15, 0, 15, uiDiscussionBar.backgroundColor)
        ) ;
        this.setCursor(new Cursor(Cursor.HAND_CURSOR)) ;
        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                //TODO : afficher la conversation
                System.out.println("discussion") ;

                uiWindow uiWindow = (uiWindow) Env.getApplication().getGraphicFrame().getScreen() ;
                uiWindow.getRightSide().activeSubScreen(RightSide.SubScreenType.Discussion) ;
            }
        }) ;

        // Add the picture panel.
        this.add(this.graphicPicture()) ;

        // Add the content container.
        this.add(this.graphicContent()) ;
    }

    /**
     * Generate the picture graphic container.
     *
     * @return the JPanel generated.
     */
    private JPanel graphicPicture() {
        JPanel container = new JPanel() ;
        container.setLayout(new BorderLayout()) ;
        container.setPreferredSize(new Dimension(90, 350)) ;
        container.setMaximumSize(new Dimension(90, 350)) ;
        container.setBackground(new Color(255, 255, 255)) ;

        // Label start.
        JLabel label = new JLabel() ;
        label.setHorizontalAlignment(SwingConstants.CENTER) ;
        label.setIcon(
            ImageAsset.asImageIcon("TODELETE/" + this.picture)
        ) ;
        label.setHorizontalTextPosition(SwingConstants.CENTER) ;
        label.setMinimumSize(new Dimension(80, 44)) ;
        label.setMaximumSize(new Dimension(80, 44)) ;
        label.setRequestFocusEnabled(false) ;
        // Label end.

        container.add(label, BorderLayout.CENTER) ;

        return container ;
    }

    /**
     * Generate the content container.
     *
     * @return the JPanel generated.
     */
    private JPanel graphicContent() {
        JPanel content = new JPanel() ;
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS)) ;
        content.setBackground(new Color(255, 255, 255)) ;

        // Add the components.
        content.add(this.graphicNameLabel()) ;
        content.add(this.contentLabel) ;

        return content ;
    }

    /**
     * Generate the name label.
     *
     * @return the JLabel generated.
     */
    private JLabel graphicNameLabel() {
        JLabel label = new JLabel() ;
        label.setText(this.conversation.getTarget().getPseudo()) ;

        Font f = new Font(null, Font.PLAIN, 14) ;
        label.setFont(f.deriveFont(Font.BOLD)) ;

        return label ;
    }

    /**
     * Generate the short content label.
     *
     * @return the JLabel generated.
     */
    private JLabel graphicContentLabel() {
        JLabel label = new JLabel() ;
        label.setText("<html></html>") ;

        Font f = new Font(null, Font.PLAIN, 12) ;
        label.setFont(f.deriveFont(Font.PLAIN)) ;

        return label ;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true ;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false ;
        }

        uiDiscussionItem that = (uiDiscussionItem) o ;

        return this.conversation.equals(that.getConversation()) ;
    }

}
