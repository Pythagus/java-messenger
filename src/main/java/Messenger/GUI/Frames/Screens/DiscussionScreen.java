package Messenger.GUI.Frames.Screens;

import java.awt.*;
import javax.swing.*;
import Messenger.Utils.ColorUtils;
import Messenger.GUI.Factories.FontFactory;
import Messenger.GUI.Factories.ButtonFactory;
import Messenger.GUI.Frames.Utils.GridBagUtil;
import Messenger.Foundation.Models.Conversation;
import Messenger.GUI.Listeners.SentMessageListener;
import Messenger.Foundation.System.Assets.ImageAsset;
import Messenger.GUI.Frames.Screens.Utils.ContentType;
import Messenger.GUI.Frames.Screens.Utils.ContentScreen;
import Messenger.GUI.Frames.Screens.Discussions.MessageList;
import Messenger.GUI.Frames.Screens.Discussions.DiscussionInput;

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
        panel.setBackground(ColorUtils.transparent()) ;

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
        );

        // Discussion header.
        JPanel header = this.preparePanel(20) ;
        header.add(this.title) ;

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
     * Choose a file in the user directory.
     */
    private void chooseFile() {
        System.out.println("FILE") ;
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

        if (conversation != null) {
            this.title.setText(conversation.getTitle()) ;
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
