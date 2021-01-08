package fr.insa.messenger.client.ui.screens.discussions;

import java.awt.*;
import javax.swing.*;
import fr.insa.messenger.client.models.Message;
import fr.insa.messenger.client.utils.ColorUtils;
import fr.insa.messenger.client.ui.utils.GridBagUtil;
import fr.insa.messenger.client.ui.utils.MyJListItem;
import fr.insa.messenger.client.ui.factories.FontFactory;
import fr.insa.messenger.client.network.models.FilePacket;

/**
 * @author Damien MOLINA
 */
public class MessageListItem extends MyJListItem {

    /**
     * Message list instance.
     */
    private final MessageList list ;

    /**
     * Sent message instance.
     */
    private Message message ;

    /**
     * File instance.
     */
    private FilePacket file ;

    /**
     * Determine whether the item should be
     * rendered as a left or right item.
     */
    private final boolean active ;

    /**
     * Make a new MessageListItem instance.
     *
     * @param list : parent list.
     * @param message : sent message instance.
     */
    public MessageListItem(MessageList list, Message message) {
        this(list, message.getSender().isEnvUser()) ;

        this.message = message ;

        this.initializeGraphics() ;
    }

    /**
     * Make a new MessageListItem instance.
     *
     * @param list : parent list.
     * @param file : sent file instance.
     */
    public MessageListItem(MessageList list, FilePacket file) {
        this(list, file.getSourceUser().isEnvUser()) ;

        this.file = file ;

        this.initializeGraphics() ;
    }

    /**
     * Make a new MessageListItem instance.
     *
     * @param list : parent list.
     * @param active : active message.
     */
    private MessageListItem(MessageList list, boolean active) {
        this.list    = list ;
        this.active  = active ;
    }

    /**
     * Graphically initialize the screen.
     */
    private void initializeGraphics() {
        this.setLayout(new GridBagLayout()) ;
        this.setBackground(ColorUtils.TRANSPARENT) ;
        this.setBorder(
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ) ;

        // Content panel.
        JPanel container = new JPanel() ;
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS)) ;
        container.setBorder(
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ) ;
        container.setBackground(ColorUtils.TRANSPARENT) ;

        // Message content.
        // TODO : manage too long message problem.
        JLabel content = new JLabel(this.getContent()) ;
        content.setBorder(
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        );
        content.setAlignmentX(
            this.active ? JLabel.RIGHT_ALIGNMENT : JLabel.LEFT_ALIGNMENT
        ) ;
        content.setOpaque(true) ;
        content.setBackground(
            this.active ? new Color(0, 153, 255) : new Color(238, 230, 235)
        ) ;
        content.setFont(FontFactory.normal(14));
        content.setForeground(this.active ? Color.WHITE : Color.BLACK) ;

        // Message date.
        JLabel date = new JLabel(this.message.getDateForHuman()) ;
        date.setFont(FontFactory.normal(10)) ;
        date.setAlignmentX(
            this.active ? JLabel.RIGHT_ALIGNMENT : JLabel.LEFT_ALIGNMENT
        ) ;
        date.setBorder(
            BorderFactory.createEmptyBorder(5, 0, 0, 4)
        ) ;

        // Add message components on content.
        container.add(content) ;
        container.add(date) ;

        // Empty space panel.
        JPanel space = new JPanel() ;
        space.setBackground(ColorUtils.TRANSPARENT) ;

        // Add the panels.
        GridBagUtil.addColumn(this, container, this.active ? 2 : 1, 60) ;
        GridBagUtil.addColumn(this, space, this.active ? 1 : 2, 40) ;
    }

    /**
     * Get the content.
     *
     * @return string content.
     */
    private String getContent() {
        if(this.message != null) {
            return this.message.getText() ;
        }

        if(this.file != null) {
            return this.file.getName() ;
        }

        return "" ;
    }

    /**
     * When the item is selected.
     */
    public void selected() {
        // TODO: update or delete the message.
    }

}
