package fr.insa.messenger.client.ui.screens.discussions;

import java.awt.*;
import java.io.File;
import javax.swing.*;
import java.nio.file.Path;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import fr.insa.messenger.client.utils.ColorUtils;
import fr.insa.messenger.client.models.MessageFile;
import fr.insa.messenger.client.ui.utils.GridBagUtil;
import fr.insa.messenger.client.ui.utils.MyJListItem;
import fr.insa.messenger.client.models.AbstractMessage;
import fr.insa.messenger.client.ui.factories.FontFactory;

/**
 * @author Damien MOLINA
 */
public class MessageListItem extends MyJListItem {

    /**
     * Sent message instance.
     */
    private AbstractMessage<?> message ;

    /**
     * Determine whether the item should be
     * rendered as a left or right item.
     */
    private final boolean active ;

    /**
     * Make a new MessageListItem instance.
     *
     * @param message : sent message instance.
     */
    public MessageListItem(AbstractMessage<?> message) {
        this(message.getSender().isEnvUser()) ;

        this.message = message ;

        this.initializeGraphics() ;
    }

    /**
     * Make a new MessageListItem instance.
     *
     * @param active : active message.
     */
    private MessageListItem(boolean active) {
        this.active = active ;
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
     * Get the message content.
     *
     * @return message content.
     */
    public String getContent() {
        if(this.message instanceof MessageFile) {
            MessageFile file = (MessageFile) this.message ;
            String end = ""  ;

            if(! file.canBeDownloaded()) {
                end = " (indisponible)" ;
            }

            return "Fichier envoyé : " + this.message.getContent() + end ;
        }

        return this.message.getContent() ;
    }

    /**
     * When the item is selected.
     */
    public void selected() {
        if(this.message instanceof MessageFile) {
            MessageFile file = (MessageFile) this.message ;

            // if the file can be downloaded.
            if(file.canBeDownloaded()) {
                JFileChooser chooser = new JFileChooser() ;
                chooser.setDialogTitle("Specify a file to save") ;

                /*
                 * Set the suggested file name.
                 * The file doesn't need to exist.
                 */
                chooser.setSelectedFile(new File(file.getOriginalName())) ;

                if(chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                    try {
                        File fileToSave = chooser.getSelectedFile() ;
                        Files.copy(
                            Path.of(file.getFullTemporaryPath()), Path.of(fileToSave.getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING
                        ) ;
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                }
            }
        }
    }

}
