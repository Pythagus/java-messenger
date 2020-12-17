package Messenger.GUI.Frames.Items;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Objects;
import java.util.function.Consumer;
import Messenger.GUI.Frames.MainFrame;
import Messenger.GUI.GraphicInterface;
import Messenger.GUI.Factories.ButtonFactory;
import Messenger.GUI.Frames.Screens.Utils.BarType;
import Messenger.Foundation.System.Assets.ImageAsset;
import Messenger.GUI.Frames.Screens.Utils.ContentType;

/**
 * @author Damien MOLINA
 */
public class Sidebar extends JPanel {

    // Main sidebar color.
    public static final Color backgroundColor = Color.WHITE ;

    /**
     * Sidebar frame instance.
     */
    private final MainFrame frame ;

    /**
     * Make a new instance of the side bar.
     *
     * @param frame : main frame.
     */
    public Sidebar(MainFrame frame) {
        this.frame = frame ;

        this.initializeComponentGraphics() ;
    }

    /**
     * Initialize graphically the component
     */
    private void initializeComponentGraphics() {
        this.setLayout(new BorderLayout()) ;
        this.setBackground(Sidebar.backgroundColor) ;

        // Navigation panel.
        JPanel nav = new JPanel() ;
        nav.setBackground(Sidebar.backgroundColor) ;
        nav.setPreferredSize(new Dimension(80, 647)) ;
        nav.setLayout(new BoxLayout(nav, BoxLayout.Y_AXIS)) ;
        nav.add(this.graphicImagedButton("logo.jpg", this::onWelcomeButtonClick), BorderLayout.CENTER) ;
        nav.add(this.graphicImagedButton("sidebar/contact_button.png", this::onContactButtonClick), BorderLayout.CENTER) ;
        nav.add(this.graphicImagedButton("sidebar/message_button.png", this::onMessageButtonClick), BorderLayout.CENTER) ;

        // Settings panel.
        JPanel settings = new JPanel() ;
        settings.setBackground(Sidebar.backgroundColor);
        settings.setPreferredSize(new Dimension(80, 80));
        settings.setLayout(new BorderLayout());
        settings.add(this.graphicImagedButton("sidebar/button_settings.png", this::onSettingsButtonClick), BorderLayout.CENTER) ;

        // Add the components.
        this.add(settings, BorderLayout.PAGE_END) ;
        this.add(nav, BorderLayout.PAGE_START) ;
    }

    /**
     * Generated an imaged button.
     *
     * @param file : picture name.
     * @param callable : callable ran when the button is clicked.
     * @return the JButton generated.
     */
    private JButton graphicImagedButton(String file, Consumer<ActionEvent> callable) {
        JButton button = ButtonFactory.withoutBorder(Sidebar.backgroundColor) ;

        button.setIcon(ImageAsset.asImageIcon(file)) ;
        button.setMaximumSize(new Dimension(80, 80)) ;
        button.setMinimumSize(new Dimension(80, 28)) ;
        button.setPreferredSize(new Dimension(50, 50)) ;
        button.addActionListener(callable::accept) ;
        button.setAlignmentX(CENTER_ALIGNMENT);

        return button ;
    }

    /**
     * Executed when the Contact button is clicked.
     *
     * @param e : click event.
     */
    private void onContactButtonClick(ActionEvent e) {
        this.frame.showBar(BarType.CONTACTS) ;
        this.frame.showContent(ContentType.WELCOME) ;
    }

    /**
     * Executed when the Message button is clicked.
     *
     * @param e : click event.
     */
    private void onMessageButtonClick(ActionEvent e) {
        this.frame.showBar(BarType.DISCUSSION) ;

        try {
            if(Objects.requireNonNull(GraphicInterface.instance().discussionScreen()).getConversation() != null) {
                this.frame.showContent(ContentType.DISCUSSION) ;
            }
        } catch(NullPointerException ignored) {}
    }

    /**
     * Executed when the Welcome button is clicked.
     *
     * @param e : click event.
     */
    private void onWelcomeButtonClick(ActionEvent e) {
        this.frame.showBar(BarType.DISCUSSION) ;
        this.frame.showContent(ContentType.WELCOME) ;
    }

    /**
     * Executed when the uiSettings button is clicked.
     *
     * @param e : click event.
     */
    private void onSettingsButtonClick(ActionEvent e) {
        this.frame.showContent(ContentType.SETTINGS) ;
    }

}
