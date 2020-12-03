package Messenger.GUI.Layout.Items.Discussion;

import Messenger.Foundation.System.Assets.ImageAsset;
import Messenger.Foundation.Models.Messages.Message;
import Messenger.GUI.Factories.ButtonFactory;
import Messenger.GUI.Subscreens.uiDiscussion;
import javax.swing.*;
import java.awt.*;

/**
 * @author Damien MOLINA
 */
public class uiDiscussionMessage extends JPanel {

    // Main active color.
    private static final Color backgroundColor = uiDiscussionBar.backgroundColor ;

    // Main active color.
    private static final Color backgroundColorActive = new Color(1, 118, 255) ;

    // Env message side.
    private final Side side ;

    /**
     * Displayed message.
     */
    private final Message message ;

    /**
     * Side of the message.
     */
    public enum Side { LEFT, RIGHT }

    /**
     * Determine whether the current message
     * is in an active mode.
     */
    private final boolean active ;

    /**
     * Make a new instance of a message.
     *
     * @param side : side the message should displayed in.
     */
    public uiDiscussionMessage(Side side, Message message) {
        this.side    = side ;
        this.active  = (side == Side.RIGHT) ;
        this.message = message ;

        this.initializeComponentGraphics() ;
    }

    /**
     * Initialize graphically the component.
     */
    private void initializeComponentGraphics() {
        this.setLayout(new GridLayout(1, 2)) ;

        switch(this.side) {
            case LEFT:
                this.add(this.graphicFullSide()) ;
                this.add(this.graphicEmptySide()) ;
                break ;
            case RIGHT:
                this.add(this.graphicEmptySide()) ;
                this.add(this.graphicFullSide()) ;
                break ;
        }
    }

    /**
     * Get the discussion background color regarding
     * the active value.
     *
     * @return the corresponding color.
     */
    private Color getBackgroundColor() {
        return this.active ? uiDiscussionMessage.backgroundColorActive : uiDiscussionMessage.backgroundColor ;
    }

    /**
     * Get the discussion text color regarding
     * the active value.
     *
     * @return the corresponding color.
     */
    private Color getTextColor() {
        return this.active ? Color.WHITE : Color.BLACK ;
    }

    /**
     * Generate the empty side.
     *
     * @return the JPanel generated.
     */
    private JPanel graphicEmptySide() {
        JPanel column = new JPanel() ;
        column.setBackground(uiDiscussion.backgroundColor) ;

        return column ;
    }

    /**
     * Generate the column containing the message.
     *
     * @return the JPanel generated.
     */
    private JPanel graphicFullSide() {
        JPanel column = new JPanel() ;
        column.setBackground(uiDiscussion.backgroundColor) ;
        column.setLayout(new BoxLayout(column, BoxLayout.X_AXIS)) ;

        // Add the component elements.
        switch(this.side) {
            case LEFT:
                column.add(this.graphicAvatar()) ;
                column.add(this.graphicMessageContainer()) ;
                column.add(this.graphicParameters()) ;
                break;
            case RIGHT:
                column.add(this.graphicParameters()) ;
                column.add(this.graphicMessageContainer()) ;
                column.add(this.graphicAvatar()) ;
                break;
        }

        return column ;
    }

    /**
     * Generate the parameters panel.
     *
     * @return the JPanel generated.
     */
    private JPanel graphicParameters() {
        JPanel container = new JPanel() ;
        container.setBackground(uiDiscussion.backgroundColor) ;
        container.setLayout(new BorderLayout()) ;

        JButton button = ButtonFactory.withoutBorder(uiDiscussion.backgroundColor) ;
        button.setText("+") ;
        container.add(button, BorderLayout.CENTER) ;

        return container ;
    }

    /**
     * generate the message container panel.
     *
     * @return the JPanel generated.
     */
    private JPanel graphicMessageContainer() {
        JPanel container = new JPanel() ;
        container.setBorder(
            BorderFactory.createMatteBorder(10, 10, 10, 10, this.getBackgroundColor())
        ) ;
        container.setBackground(this.getBackgroundColor()) ;
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS)) ;

        // Add the component elements.
        container.add(this.graphicMessage()) ;
        container.add(this.graphicDate()) ;

        return container ;
    }

    /**
     * Generate the message.
     *
     * @return the JPanel generated.
     */
    private JTextArea graphicMessage() {
        JTextArea area = new JTextArea() ;

        // TODO : display the file
        String msg = this.message.getData().getText() ;

        area.setText(msg) ;
        area.setEditable(false) ;
        area.setBackground(null) ;
        area.setLineWrap(true) ;
        area.setForeground(this.getTextColor()) ;

        return area ;
    }

    /**
     * Generate the message date.
     *
     * @return the JPanel generated.
     */
    private JPanel graphicDate() {
        JPanel container = new JPanel();
        container.setLayout(new BorderLayout()) ;
        container.setBackground(new Color(0,0,0,0)) ;

        JLabel label = new JLabel() ;
        label.setBorder(
            BorderFactory.createEmptyBorder(10,0,0,0)
        );
        label.setFont(new Font(null, Font.PLAIN, 9)) ;
        label.setText(this.message.getDateForHuman()) ;
        label.setVerticalTextPosition(SwingConstants.TOP) ;
        label.setForeground(this.getTextColor()) ;

        container.add(label, BorderLayout.PAGE_START) ;

        return container ;
    }

    /**
     * Generate the avatar container.
     *
     * @return the JPanel generated.
     */
    private JPanel graphicAvatar() {
        JPanel container = new JPanel() ;
        container.setBackground(uiDiscussion.backgroundColor) ;
        container.setPreferredSize(new Dimension(50, 50)) ;
        container.setLayout(new BorderLayout()) ;
        container.setBorder(
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ) ;

        JLabel avatar = new JLabel() ;
        ImageIcon imageIcon = new ImageIcon(
            ImageAsset.asImageIcon("TODELETE/francois_hollande.png").getImage().getScaledInstance(30, 30, 1)
        ) ;
        avatar.setIcon(imageIcon) ;
        avatar.setVerticalAlignment(SwingConstants.BOTTOM) ;
        avatar.setVerticalTextPosition(SwingConstants.BOTTOM) ;

        container.add(avatar, BorderLayout.CENTER) ;

        return container ;
    }

}
