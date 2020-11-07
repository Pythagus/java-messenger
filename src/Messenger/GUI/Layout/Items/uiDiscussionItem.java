package Messenger.GUI.Layout.Items;

import Messenger.GUI.Factories.GraphicImageFactory;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

    // uiDiscussionItem name.
    private final String conversationName ;

    // uiDiscussionItem short content.
    private final String content ;

    // uiDiscussion identifier.
    private final int discussion_id ;

    /**
     * Make a new graphic instance of conversation.
     *
     * @param picture : file name.
     * @param name : name of the conversation.
     * @param content : short content.
     */
    public uiDiscussionItem(int discussion_id, String picture, String name, String content) {
        this.picture          = picture ;
        this.conversationName = name ;
        this.content          = content ;
        this.discussion_id    = discussion_id ;

        this.initializeComponentGraphics() ;
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
                System.out.println(uiDiscussionItem.this.discussion_id);
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
                GraphicImageFactory.asImageIcon("TODELETE/" + this.picture)
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
        content.add(this.graphicContentLabel()) ;

        return content ;
    }

    /**
     * Generate the name label.
     *
     * @return the JLabel generated.
     */
    private JLabel graphicNameLabel() {
        JLabel label = new JLabel() ;
        label.setText(this.conversationName) ;

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
        String content = this.content ;

        if(content.length() > MAX_CONTENT_SIZE) {
            content = content.substring(0, MAX_CONTENT_SIZE) + "..." ;
        }

        JLabel label = new JLabel() ;
        label.setText("<html>" + content + "</html>") ;

        Font f = new Font(null, Font.PLAIN, 12) ;
        label.setFont(f.deriveFont(Font.PLAIN)) ;

        return label ;
    }

}
