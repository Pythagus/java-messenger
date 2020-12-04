package Messenger.GUI.Layout.Items.Discussion;

import java.awt.*;
import javax.swing.*;
import Messenger.GUI.Subscreens.uiDiscussion;
import Messenger.Foundation.Models.Conversation;
import Messenger.Foundation.System.Assets.ImageAsset;

/**
 * @author Damien MOLINA
 */
public class uiDiscussionHeader extends JPanel {

    /**
     * Conversation instance.
     */
    private Conversation conversation ;

    /**
     * Label name.
     */
    private final JLabel nameLabel ;

    /**
     * Make a new instance of uiDiscussionHeader.
     */
    public uiDiscussionHeader() {
        this.nameLabel = this.graphicName() ;

        this.initializeComponentGraphics() ;
    }

    /**
     * Update the conversation.
     *
     * @param conversation : conversation instance.
     */
    public void updateConversation(Conversation conversation) {
        this.conversation = conversation ;

        this.nameLabel.setText(
            conversation.getTarget().getPseudo()
        ) ;
    }

    /**
     * Initialize graphically the component
     */
    private void initializeComponentGraphics() {
        this.setBackground(uiDiscussion.backgroundColor) ;
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setPreferredSize(new Dimension(127, 80));
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS)) ;

        // Add the component elements.
        this.add(this.graphicLogoContainer()) ;
        this.add(this.graphicNameContainer()) ;
    }

    /**
     * Generate the logo container instance.
     *
     * @return the JPanel generated.
     */
    private JPanel graphicLogoContainer() {
        JPanel container = new JPanel() ;
        container.setBackground(uiDiscussion.backgroundColor) ;
        container.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)) ;
        container.setPreferredSize(new Dimension(58, 100)) ;
        container.setLayout(new BoxLayout(container, BoxLayout.LINE_AXIS)) ;

        container.add(this.graphicLogo()) ;

        return container ;
    }

    /**
     * Generate the logo label.
     *
     * @return the JLabel generated.
     */
    private JLabel graphicLogo() {
        JLabel logo = new JLabel() ;
        logo.setHorizontalAlignment(SwingConstants.CENTER) ;
        logo.setIcon(
            ImageAsset.asImageIcon("TODELETE/francois_hollande.png")
        ) ;
        logo.setHorizontalTextPosition(SwingConstants.CENTER) ;

        return logo ;
    }

    /**
     * Generate the name container instance.
     *
     * @return the JPanel generated.
     */
    private JPanel graphicNameContainer() {
        JPanel container = new JPanel() ;
        container.setBackground(uiDiscussion.backgroundColor) ;
        container.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)) ;
        container.setPreferredSize(new Dimension(514, 60)) ;
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS)) ;

        // Add the component elements.
        container.add(this.nameLabel) ;
        container.add(this.graphicStatus()) ;

        return container ;
    }

    /**
     * Generate the name label.
     *
     * @return the JLabel generated.
     */
    private JLabel graphicName() {
        JLabel name = new JLabel() ;
        name.setText("") ;
        name.setPreferredSize(new Dimension(490, 18)) ;
        name.setRequestFocusEnabled(false) ;

        Font f = new Font(null, Font.PLAIN, 14) ;
        name.setFont(f.deriveFont(Font.BOLD)) ;

        return name ;
    }

    /**
     * Generate the status label.
     *
     * @return the JLabel generated.
     */
    private JLabel graphicStatus() {
        JLabel status = new JLabel() ;
        status.setText("En ligne") ;
        status.setPreferredSize(new Dimension(490, 18)) ;
        status.setRequestFocusEnabled(false) ;
        status.setFont(new Font(null, Font.PLAIN, 11)) ;

        return status ;
    }

}
