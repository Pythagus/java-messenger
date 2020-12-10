package Messenger.GUI.Layout.Items;

import java.awt.*;
import javax.swing.*;
import Messenger.Foundation.Models.User;
import Messenger.Foundation.System.Assets.ImageAsset;

/**
 * @author Damien MOLINA
 */
abstract public class uiVerticalBarItem extends JPanel {

    /**
     * Max size of the displayed content.
     */
    private static final int MAX_CONTENT_SIZE = 50 ;

    /**
     * Item picture
     */
    private final String picture ;

    /**
     * User instance.
     */
    private final User user ;

    /**
     * Content label instance.
     */
    protected final JLabel userPseudoLabel ;

    /**
     * Content label instance.
     */
    protected final JLabel contentLabel ;

    /**
     * Make a new graphic instance of conversation.
     *
     * @param picture : file name.
     */
    public uiVerticalBarItem(User user, String picture) {
        this.picture         = picture ;
        this.user            = user ;
        this.userPseudoLabel = this.graphicUserLabel() ;
        this.contentLabel    = this.graphicContentLabel() ;

        this.setContent("") ;
        this.initializeComponentGraphics() ;
    }

    /**
     * Update the item content graphically.
     *
     * @param content : panel to update.
     * @return the updated panel.
     */
    protected JPanel graphicItemContent(JPanel content) {
        content.add(this.userPseudoLabel) ;
        content.add(this.contentLabel) ;

        return content ;
    }

    /**
     * Get the user instance.
     *
     * @return the user instance.
     */
    public User getUser() {
        return this.user ;
    }

    /**
     * Initialize graphically the component
     */
    private void initializeComponentGraphics() {
        this.setPreferredSize(new Dimension(500, 80)) ;
        this.setBackground(new Color(255, 255, 255)) ;
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS)) ;
        this.setBorder(
            BorderFactory.createMatteBorder(0, 15, 0, 15, uiVerticalBar.backgroundColor)
        ) ;
        this.setCursor(new Cursor(Cursor.HAND_CURSOR)) ;

        // Add the picture panel.
        this.add(this.graphicPicture()) ;

        // Add the content container.
        this.add(this.graphicContent()) ;
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

        return this.graphicItemContent(content) ;
    }

    /**
     * Generate the picture graphic container.
     *
     * @return the JPanel generated.
     */
    protected JPanel graphicPicture() {
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
     * Generate the short content label.
     *
     * @return the JLabel generated.
     */
    private JLabel graphicContentLabel() {
        JLabel label = new JLabel() ;
        label.setText("<html>" + this.user.getPseudo() + "</html>") ;

        Font f = new Font(null, Font.PLAIN, 12) ;
        label.setFont(f.deriveFont(Font.PLAIN)) ;

        return label ;
    }

    /**
     * Generate the name label.
     *
     * @return the JLabel generated.
     */
    private JLabel graphicUserLabel() {
        JLabel label = new JLabel() ;
        label.setText("<html>" + this.user.getPseudo() + "</html>") ;

        Font f = new Font(null, Font.PLAIN, 14) ;
        label.setFont(f.deriveFont(Font.BOLD)) ;

        return label ;
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

}
