package fr.insa.messenger.client.ui.screens;

import java.awt.*;
import java.io.File;
import javax.swing.*;

import fr.insa.messenger.client.system.assets.ImageAsset;
import fr.insa.messenger.client.ui.screens.discussions.DiscussionInput;
import fr.insa.messenger.client.ui.screens.discussions.MessageList;
import fr.insa.messenger.client.ui.screens.utils.ContentScreen;
import fr.insa.messenger.client.ui.screens.utils.ContentType;
import fr.insa.messenger.client.utils.ColorUtils;
import fr.insa.messenger.client.models.Conversation;
import fr.insa.messenger.client.ui.utils.GridBagUtil;
import fr.insa.messenger.client.ui.factories.FontFactory;
import fr.insa.messenger.client.ui.factories.ButtonFactory;
import fr.insa.messenger.client.observers.SentMessageListener;
import fr.insa.messenger.client.controllers.ConversationController;

/**
 * @author Damien MOLINA
 */
public class DiscussionScreen extends ContentScreen {

    /**
     * Header discussion title label.
     */
    private final JLabel title ;

    /**
     * Messages list.
     */
    private final MessageList list ;

    /**
     * List scroll pane.
     */
    private final JScrollPane listScrollPane ;

    /**
     * Conversation instance.
     */
    private Conversation conversation ;

    /**
     * Make a new screen instance.
     */
    public DiscussionScreen() {
        super(ContentType.DISCUSSION) ;

        this.title = new JLabel() ;
        this.list  = new MessageList(this) ;
        this.listScrollPane = new JScrollPane() ;

        this.initializeGraphics() ;
    }

    /**
     * Prepare a panel for the discussion
     * screen.
     *
     * @return the generated JPanel instance.
     */
    private JPanel preparePanel(int padding) {
        JPanel panel = new JPanel() ;
        panel.setBorder(
            BorderFactory.createEmptyBorder(padding, padding, padding, padding)
        ) ;
        panel.setBackground(ColorUtils.TRANSPARENT) ;

        return panel ;
    }

    /**
     * Graphically initialize the screen.
     */
    private void initializeGraphics() {
        this.setLayout(new BorderLayout()) ;

        // Title label.
        this.title.setFont(FontFactory.bold("Arial", 18)) ;
        this.title.setBorder(
            BorderFactory.createEmptyBorder(10, 0, 0, 0)
        ) ;

        // Quit conversation button.
        JButton quitButton = ButtonFactory.withoutBorder("Quitter") ;
        quitButton.setBackground(new Color(199, 0, 57)) ;
        quitButton.setOpaque(true) ;
        quitButton.setForeground(Color.WHITE) ;
        quitButton.addActionListener(Unused -> this.quitConversation()) ;

        // Discussion header.
        JPanel header = this.preparePanel(20) ;
        header.setLayout(new BorderLayout()) ;
        header.add(this.title, BorderLayout.LINE_START) ;
        header.add(quitButton, BorderLayout.LINE_END) ;

        // Discussion footer.
        JPanel footer = this.preparePanel(0) ;
        footer.setLayout(new GridBagLayout()) ;

        // Input.
        JPanel inputContainer = this.preparePanel(20) ;
        DiscussionInput input = new DiscussionInput(this) ;
        input.addListener(new SentMessageListener(this)) ;
        inputContainer.setLayout(new BorderLayout()) ;
        inputContainer.add(input) ;

        // Buttons container.
        JPanel btnContainer = this.preparePanel(0) ;
        btnContainer.setLayout(new BoxLayout(btnContainer, BoxLayout.X_AXIS)) ;

        // Send button.
        JButton btnSend = ButtonFactory.withoutBorder() ;
        btnSend.setIcon(
            ImageAsset.asImageIcon("discussion/send_button.png")
        ) ;
        btnSend.addActionListener(Unused -> input.sendText()) ;

        // File button.
        JButton btnFile = ButtonFactory.withoutBorder() ;
        btnFile.setIcon(
            ImageAsset.asImageIcon("discussion/file_button.png")
        ) ;
        btnFile.addActionListener(Unused -> this.chooseFile()) ;

        // Add buttons.
        btnContainer.add(btnFile) ;
        btnContainer.add(btnSend) ;

        // Add footer components.
        GridBagUtil.addColumn(footer, inputContainer,1, 95) ;
        GridBagUtil.addColumn(footer, btnContainer,2, 5) ;

        // Messages list
        this.listScrollPane.setViewportView(this.list) ;
        this.listScrollPane.setBorder(null) ;

        // Content.
        JPanel content = this.preparePanel(20) ;
        content.setLayout(new BorderLayout()) ;
        content.add(this.listScrollPane) ;

        // Add screen components.
        this.add(header, BorderLayout.PAGE_START) ;
        this.add(content, BorderLayout.CENTER) ;
        this.add(footer, BorderLayout.PAGE_END) ;
    }

    /**
     * Quit the conversation.
     */
    private void quitConversation() {
        if(this.conversation != null) {
            ConversationController.instance().stop(
                this.conversation.getTarget()
            ) ;
        }
    }

    /**
     * Choose a file in the user directory.
     */
    private void chooseFile() {
        JFileChooser chooser = new JFileChooser() ;

        /*
         * Check whether the user chose
         * a file in his system.
         */
        if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile() ;

            // TODO : send file.
            System.out.println("Chosen file : " + file.getName()) ;
        }
    }

    /**
     * Get the message scroll pane.
     *
     * @return scroll pane instance.
     */
    public JScrollPane getListScrollPane() {
        return this.listScrollPane ;
    }

    /**
     * Set a new conversation instance.
     *
     * @param conversation : current conversation instance.
     */
    public void setConversation(Conversation conversation) {
        this.conversation = conversation ;
        this.list.getModel().removeAllElements() ;

        if (conversation != null) {
            this.title.setText(conversation.getTitle()) ;

            // Add all the messages into the screen.
            conversation.getMessages().forEach(DiscussionScreen.this.list::addItem) ;
        } else {
            this.title.setText(null) ;
        }
    }

    /**
     * Get the current conversation instance.
     *
     * @return the conversation instance.
     */
    public Conversation getConversation() {
        return this.conversation ;
    }

    /**
     * Get the messages list.
     *
     * @return the messages list instance.
     */
    public MessageList getList() {
        return this.list ;
    }

}
