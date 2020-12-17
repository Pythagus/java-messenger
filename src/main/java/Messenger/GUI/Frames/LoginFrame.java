package Messenger.GUI.Frames;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import Messenger.Utils.ColorUtils;
import java.awt.event.ActionListener;
import Messenger.GUI.Factories.FontFactory;
import Messenger.GUI.Listeners.SentPseudoListener;
import Messenger.Foundation.System.Assets.ImageAsset;
import Messenger.Foundation.Providers.ObserverProvider;
import Messenger.Foundation.Observers.Contracts.Listener;
import Messenger.Foundation.Observers.Contracts.Observable;

/**
 * @author Damien MOLINA
 */
public class LoginFrame extends Frame implements ActionListener, Observable {

    /**
     * Frame size.
     */
    private static final Dimension SIZE = new Dimension(370, 600) ;

    /**
     * Observers provider.
     */
    private final ObserverProvider observers = new ObserverProvider() ;

    /**
     * Pseudo input.
     */
    private final JTextField pseudoInput = new JTextField() ;

    /**
     * Send button.
     */
    private final JButton loginButton = new JButton("Connexion") ;

    /**
     * Make a new LoginFrame instance.
     */
    public LoginFrame() {
        super("Connexion - Messenger") ;

        this.setContentPane(new LoginFrameContent("background/login-background.png")) ;
        this.setResizable(false) ;
        this.center(LoginFrame.SIZE) ;
        this.initializeGraphics() ;

        this.loginButton.addActionListener(this) ;
        this.pseudoInput.addActionListener(this) ;
        this.addListener(new SentPseudoListener()) ;
    }

    /**
     * Graphically initialize the frame.
     */
    private void initializeGraphics() {
        // Title label.
        JLabel title = new JLabel("Messenger", SwingConstants.CENTER) ;
        title.setFont(FontFactory.bold("Arial", 20)) ;
        this.addComponent(title, 40, 30) ;

        // Description label.
        JTextArea description = new JTextArea("Vous devez choisir un pseudo qui vous servira à contacter les autres utilisateurs de l'application.") ;
        description.setBackground(ColorUtils.transparent()) ;
        description.setBorder(
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        );
        description.setEditable(false) ;
        description.setAlignmentX(CENTER_ALIGNMENT);
        description.setLineWrap(true) ;
        description.setWrapStyleWord(true) ;
        description.setFont(FontFactory.normal("Arial", 12)) ;
        this.addComponent(description, 90, 60) ;

        // Add the Pseudo label.
        this.addComponent(new JLabel("Pseudo"), 30, 160, 80, 30) ;
        this.addComponent(this.pseudoInput, 120, 160, 200, 30) ;
        this.addComponent(this.loginButton, 110, 220, 150, 30) ;
    }

    /**
     * Paint the component.
     *
     * @param component : component to paint.
     * @param x : horizontal position.
     * @param y : vertical position.
     * @param width : component width.
     * @param height : component height.
     */
    private void addComponent(JComponent component, int x, int y, int width, int height) {
        component.setBounds(x, y, width, height) ;

        this.getContentPane().add(component) ;
    }

    /**
     * Paint the component.
     *
     * @param component : component to paint.
     * @param y : vertical position.
     * @param height : component height.
     */
    private void addComponent(JComponent component, int y, int height) {
        this.addComponent(component, 0, y, LoginFrame.SIZE.width, height) ;
    }

    /**
     * Executed when an action is performed.
     *
     * @param e : generated event.
     */
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(this.pseudoInput) || e.getSource().equals(this.loginButton)) {
            this.observers.notifyAllListeners(
                this, this.pseudoInput.getText()
            ) ;
        }
    }

    /**
     * Add a listener to the listener provider.
     *
     * @param listener : listener instance.
     */
    public void addListener(Listener listener) {
        this.observers.addListener(listener) ;
    }

    /**
     * Remove the listener from the listener provider.
     *
     * @param listener : listener instance.
     */
    public void removeListener(Listener listener) {
        this.observers.removeListener(listener) ;
    }

    /**
     * Main login frame content pane.
     *
     * @author Damien MOLINA
     */
    static class LoginFrameContent extends JComponent {
        /**
         * Background image.
         */
        private final Image backgroundImage ;

        /**
         * Login frame content instance.
         *
         * @param image : background image.
         */
        LoginFrameContent(String image) {
            this.backgroundImage =  ImageAsset.asImageIcon(image).getImage() ;

            this.setLayout(null) ;
        }

        /**
         * Paint the component adding the background
         * image.
         *
         * @param g : graphic painter.
         */
        public void paintComponent(Graphics g) {
            super.paintComponent(g) ;

            int height = this.backgroundImage.getHeight(null) ;

            g.drawImage(
                this.backgroundImage, 0, LoginFrame.SIZE.height - height / 2,
                LoginFrame.SIZE.width, LoginFrame.SIZE.width * height / this.backgroundImage.getWidth(null), null
            ) ;
        }
    }

}
