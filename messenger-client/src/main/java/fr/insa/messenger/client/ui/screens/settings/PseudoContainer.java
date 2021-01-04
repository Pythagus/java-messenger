package fr.insa.messenger.client.ui.screens.settings;

import java.awt.*;
import javax.swing.*;
import fr.insa.messenger.client.utils.ColorUtils;
import fr.insa.messenger.client.ui.factories.FontFactory;
import fr.insa.messenger.client.ui.screens.SettingsScreen;
import fr.insa.messenger.client.observers.contracts.Listener;
import fr.insa.messenger.client.observers.contracts.Observable;
import fr.insa.messenger.client.observers.contracts.ObserverProvider;

/**
 * @author Damien MOLINA
 */
public class PseudoContainer extends JPanel implements Observable {

    /**
     * Observers provider.
     */
    private final ObserverProvider observers = new ObserverProvider() ;

    /**
     * Pseudo input instance.
     */
    private final JTextField input ;

    /**
     * Settings screen.
     */
    private final SettingsScreen screen ;

    /**
     * Make a new screen instance.
     *
     * @param screen : parent screen.
     * @param value : default input value.
     */
    public PseudoContainer(SettingsScreen screen, String value) {
        this.input  = new JTextField(value) ;
        this.screen = screen ;

        this.initializeGraphics() ;
    }

    /**
     * Graphically initialize the screen.
     */
    private void initializeGraphics() {
        this.setLayout(new BorderLayout()) ;
        this.setBackground(ColorUtils.TRANSPARENT) ;
        this.setBorder(
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ) ;

        this.input.setBorder(
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ) ;
        this.input.setBackground(SettingsScreen.inputColor) ;

        // Pseudo label.
        JLabel pseudo = new JLabel("Votre pseudo") ;
        pseudo.setFont(FontFactory.normal(14)) ;
        pseudo.setBorder(
            BorderFactory.createEmptyBorder(0, 0, 10, 0)
        ) ;

        // Pseudo input container.
        JPanel inputContainer = new JPanel() ;
        inputContainer.setLayout(new GridBagLayout()) ;
        GridBagConstraints cInput = new GridBagConstraints();
        cInput.fill = GridBagConstraints.HORIZONTAL;
        cInput.weightx = 0.9 ;
        cInput.weighty = 1 ;
        cInput.anchor = GridBagConstraints.CENTER;

        // Pseudo button
        JButton pseudoButton = new JButton("Modifier") ;
        pseudoButton.addActionListener(event -> this.updatePseudo()) ;
        pseudoButton.setBorder(
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ) ;

        inputContainer.add(this.input, cInput) ;
        cInput.weightx = 0.1 ;
        inputContainer.add(pseudoButton, cInput) ;

        // Add the container components.
        this.add(pseudo, BorderLayout.NORTH) ;
        this.add(inputContainer, BorderLayout.SOUTH) ;
    }

    /**
     * Update the current user's pseudo.
     */
    private void updatePseudo() {
        this.observers.notifyAllListeners(
            this.screen, this.input.getText()
        ) ;
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

}
