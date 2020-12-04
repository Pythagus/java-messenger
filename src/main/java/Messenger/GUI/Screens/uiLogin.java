package Messenger.GUI.Screens;

import java.awt.*;
import javax.swing.*;

import Messenger.Foundation.Env;
import Messenger.GUI.Layout.Items.Login.uiLoginError;
import Messenger.GUI.Layout.Items.Login.uiLoginInput;
import Messenger.Foundation.System.Assets.ImageAsset;

/**
 * @author Damien MOLINA
 */
public class uiLogin extends Screen {

    /**
     * Login input.
     */
    private final uiLoginInput input ;

    /**
     * Error label.
     */
    private final uiLoginError errorLabel ;

    /**
     * Background image.
     */
    private final Image backgroundImage ;

    /**
     * Make a new uiLogin screen instance.
     */
    public uiLogin() {
        this.errorLabel      = new uiLoginError() ;
        this.input           = new uiLoginInput(this.errorLabel) ;
        this.backgroundImage = ImageAsset.asImageIcon("background/login-background.png").getImage();

        this.initializeComponentGraphics() ;
    }

    /**
     * Initialize graphically the component.
     */
    private void initializeComponentGraphics() {
        this.setBackground(new Color(245, 245, 245)) ;
        this.setLayout(new GridBagLayout()) ;

        this.add(this.graphicContainer(), new GridBagConstraints()) ;
    }


    /**
     * Paint the component adding the background
     * image.
     *
     * @param g : graphic painter.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g) ;

        if(this.getHeight() > 500) {
            g.drawImage(this.backgroundImage, 0, 500, null) ;
        }
    }

    /**
     * Generate the graphic container instance.
     *
     * @return the JPanel container.
     */
    private JPanel graphicContainer() {
        JPanel container = new JPanel() ;
        container.setBackground(null) ;
        container.setPreferredSize(new Dimension(300, 300)) ;
        container.setLayout(new GridLayout(4, 0)) ;

        // Add the components.
        container.add(this.graphicAppLabel()) ;
        container.add(this.graphicDescription()) ;
        container.add(this.graphicInputContainer()) ;
        container.add(this.graphicFooterContainer()) ;

        return container ;
    }

    /**
     * Generate the app name label instance.
     *
     * @return the JLabel container.
     */
    private JLabel graphicAppLabel() {
        String appName = "" ;

        try {
            appName = Env.getApplication().getName() ;
        } catch(Throwable ignored) {}

        JLabel label = new JLabel() ;
        label.setHorizontalAlignment(SwingConstants.CENTER) ;
        label.setText(appName) ;
        label.setHorizontalTextPosition(SwingConstants.CENTER) ;

        Font f = new Font("Rockwell", Font.BOLD, 30) ;
        label.setFont(f.deriveFont(Font.BOLD)) ;

        return label ;
    }

    /**
     * Generate the form description label instance.
     *
     * @return the JLabel container.
     */
    private JLabel graphicDescription() {
        JLabel label = new JLabel() ;
        label.setText("<html>Vous devez choisir un pseudo qui vous servira à contacter les autres utilisateurs de l'application.</html>") ;

        return label ;
    }

    /**
     * Generate the input container.
     *
     * @return the JPanel container.
     */
    private JPanel graphicInputContainer() {
        JPanel container = new JPanel() ;
        container.setBackground(null) ;
        container.setMinimumSize(new Dimension(100, 90)) ;
        container.setPreferredSize(new Dimension(300, 90)) ;
        container.setLayout(new GridBagLayout()) ;

        // Add the input.
        container.add(this.input, new GridBagConstraints()) ;

        return container ;
    }

    /**
     * Generate the form footer instance.
     *
     * @return the JPanel container.
     */
    private JPanel graphicFooterContainer() {
        JPanel container = new JPanel() ;
        container.setBackground(null) ;
        container.setLayout(new GridLayout(2, 1)) ;

        // Add the button.
        container.add(this.errorLabel) ;
        container.add(this.graphicButton()) ;

        return container ;
    }

    /**
     * Generate the button container instance.
     *
     * @return the JPanel container.
     */
    private JPanel graphicButton() {
        // Button container.
        JPanel btnContainer = new JPanel() ;
        btnContainer.setLayout(new GridBagLayout()) ;
        btnContainer.setBackground(null) ;

        // Button instance.
        JButton button = new JButton() ;
        button.setText("Démarrer") ;
        button.setHorizontalTextPosition(SwingConstants.CENTER) ;
        button.setPreferredSize(new Dimension(150, 28)) ;
        button.addActionListener(Unused -> this.input.sendForm());

        // Add the button to the container.
        btnContainer.add(button, new GridBagConstraints()) ;

        return btnContainer ;
    }

}
