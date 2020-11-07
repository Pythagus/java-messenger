package Messenger.GUI.Layout;

import Messenger.GUI.Factories.ButtonFactory;
import Messenger.GUI.Factories.GraphicImageFactory;
import java.util.function.Consumer;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.*;

/**
 * Main navigation bar to create a message,
 * show the contact list, update settings, etc.
 *
 * @author Damien MOLINA
 */
public class SideBar extends JPanel {
    
    // Main sidebar color.
    public static final Color backgroundColor = new Color(255, 255, 255) ;

    /**
     * Make a new instance of the side bar.
     */
    public SideBar() {
        this.initializeComponentGraphics() ;
    }

    /**
     * Initialize graphically the component
     */
    private void initializeComponentGraphics() {
        this.setPreferredSize(new Dimension(80, 787)) ; //TODO
        this.setLayout(new BorderLayout()) ;

        // Put the logo panel
        this.add(this.graphicLogoPanel(), BorderLayout.PAGE_START) ;

        // Add the navigation
        this.add(this.graphicNavigation(), BorderLayout.CENTER) ;

        // Add the settings panel
        this.add(this.graphicSettingsPanel(), BorderLayout.PAGE_END);
    }

    /**
     * Generate the logo panel.
     *
     * @return the JPanel generated.
     */
    private JPanel graphicLogoPanel() {
        JPanel logo = new JPanel() ;

        logo.setBackground(SideBar.backgroundColor) ;
        logo.setPreferredSize(new Dimension(80, 80)) ;
        logo.setLayout(new BorderLayout()) ;

        // Add the icon.
        logo.add(
                this.graphicImagedButton("logo.jpg", this::onMessageButtonClick), BorderLayout.CENTER
        ) ;

        return logo ;
    }

    /**
     * Generate the settings panel.
     *
     * @return the JPanel generated.
     */
    private JPanel graphicSettingsPanel() {
        JPanel settings = new JPanel() ;
        settings.setBackground(SideBar.backgroundColor);
        settings.setPreferredSize(new Dimension(80, 80));
        settings.setLayout(new BorderLayout());

        // Add the icon.
        settings.add(
                this.graphicImagedButton("sidebar/button_settings.png", this::onSettingsButtonClick), BorderLayout.CENTER
        ) ;

        return settings ;
    }

    /**
     * Generate the navigation bar panel.
     *
     * @return the JPanel generated.
     */
    private JPanel graphicNavigation() {
        JPanel nav = new JPanel() ;

        nav.setBackground(SideBar.backgroundColor);
        nav.setPreferredSize(new Dimension(80, 647)) ;
        nav.setLayout(new BoxLayout(nav, BoxLayout.Y_AXIS));

        // Add buttons
        nav.add(this.graphicImagedButton("sidebar/create_message_button.png", this::onCreateMessageButtonClick)) ;
        nav.add(this.graphicImagedButton("sidebar/contact_button.png", this::onContactButtonClick)) ;
        nav.add(this.graphicImagedButton("sidebar/message_button.png", this::onMessageButtonClick)) ;

        return nav ;
    }

    /**
     * Generated an imaged button.
     *
     * @param file : picture name.
     * @param callable : callable ran when the button is clicked.
     * @return the JButton generated.
     */
    private JButton graphicImagedButton(String file, Consumer<ActionEvent> callable) {
        JButton button = ButtonFactory.withoutBorder(SideBar.backgroundColor) ;

        button.setIcon(GraphicImageFactory.asImageIcon(file)) ;
        button.setMaximumSize(new Dimension(80, 80)) ;
        button.setMinimumSize(new Dimension(80, 28)) ;
        button.setPreferredSize(new Dimension(50, 50)) ;
        button.addActionListener(callable::accept) ;

        return button ;
    }

    /**
     * Executed when the CreateMessage button is
     * clicked.
     *
     * @param e : click event.
     */
    private void onCreateMessageButtonClick(ActionEvent e) {
        System.out.println("Create message") ;
    }

    /**
     * Executed when the Contact button is clicked.
     *
     * @param e : click event.
     */
    private void onContactButtonClick(ActionEvent e) {
        System.out.println("Contact") ;
    }

    /**
     * Executed when the Message button is clicked.
     *
     * @param e : click event.
     */
    private void onMessageButtonClick(ActionEvent e) {
        System.out.println("Message") ;
    }

    /**
     * Executed when the Settings button is clicked.
     *
     * @param e : click event.
     */
    private void onSettingsButtonClick(ActionEvent e) {
        System.out.println("Settings") ;
    }

}
