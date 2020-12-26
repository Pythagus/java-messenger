package fr.insa.messenger.client.ui.screens;

import java.awt.*;
import javax.swing.*;

import fr.insa.messenger.client.observers.contracts.Listener;
import fr.insa.messenger.client.observers.contracts.Observable;
import fr.insa.messenger.client.observers.contracts.ObserverProvider;
import fr.insa.messenger.client.ui.screens.utils.ContentScreen;
import fr.insa.messenger.client.ui.screens.utils.ContentType;
import fr.insa.messenger.client.system.Env;
import fr.insa.messenger.client.observers.UpdatePseudoListener;

/**
 * @author Damien MOLINA
 */
public class SettingsScreen extends ContentScreen implements Observable {

    /**
     * Observers provider.
     */
    private final ObserverProvider observers = new ObserverProvider() ;

    /**
     * Pseudo input.
     */
    private final JTextField pseudoInput ;

    /**
     * Make a new screen instance.
     */
    public SettingsScreen() {
        super(ContentType.SETTINGS) ;

        this.pseudoInput = new JTextField(Env.getUser().getPseudo()) ;
        this.initializeGraphics() ;

        this.addListener(new UpdatePseudoListener()) ;
    }

    /**
     * Graphically initialize the screen.
     */
    private void initializeGraphics() {
        this.setLayout(new GridBagLayout()) ;

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 1 ;
        c.anchor = GridBagConstraints.NORTH;

        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        this.add(this.graphicPseudo(), c) ;

        JButton button = new JButton("Button 2");
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        this.add(button, c) ;
    }

    /**
     * Graphically prepare the pseudo input container.
     *
     * @return the input container instance.
     */
    private JPanel graphicPseudo() {
        // Pseudo container.
        JPanel pseudoContainer = new JPanel() ;
        pseudoContainer.setLayout(new BorderLayout()) ;
        pseudoContainer.setBorder(
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ) ;

        // Pseudo label.
        JLabel pseudo = new JLabel("Votre pseudo") ;
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

        // Pseudo input.
        this.pseudoInput.setBorder(
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ) ;

        // Pseudo button
        JButton pseudoButton = new JButton("Modifier") ;
        pseudoButton.addActionListener(event -> this.updatePseudo()) ;
        pseudoButton.setBorder(
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ) ;

        inputContainer.add(this.pseudoInput, cInput) ;
        cInput.weightx = 0.1 ;
        inputContainer.add(pseudoButton, cInput) ;

        pseudoContainer.add(pseudo, BorderLayout.NORTH) ;
        pseudoContainer.add(inputContainer, BorderLayout.SOUTH) ;

        return pseudoContainer ;
    }

    /**
     * Update the current user's pseudo.
     */
    private void updatePseudo() {
        this.observers.notifyAllListeners(
            this, this.pseudoInput.getText()
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
