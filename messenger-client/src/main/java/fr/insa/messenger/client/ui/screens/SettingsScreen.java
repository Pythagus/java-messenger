package fr.insa.messenger.client.ui.screens;

import java.awt.*;
import javax.swing.*;
import fr.insa.messenger.client.system.Env;
import fr.insa.messenger.client.ui.factories.FontFactory;
import fr.insa.messenger.client.ui.screens.utils.ContentType;
import fr.insa.messenger.client.ui.screens.utils.ContentScreen;
import fr.insa.messenger.client.observers.UpdatePseudoListener;
import fr.insa.messenger.client.ui.screens.settings.StatusContainer;
import fr.insa.messenger.client.ui.screens.settings.PseudoContainer;

/**
 * @author Damien MOLINA
 */
public class SettingsScreen extends ContentScreen {

    /**
     * Input background color.
     */
    public static final Color inputColor = new Color(240, 240, 240) ;

    /**
     * Pseudo input.
     */
    private final PseudoContainer pseudo ;

    /**
     * Make a new screen instance.
     */
    public SettingsScreen() {
        super(ContentType.SETTINGS) ;

        this.pseudo = new PseudoContainer(
            this, Env.getUser().getPseudo()
        ) ;

        this.initializeGraphics() ;
    }

    /**
     * Graphically initialize the screen.
     */
    private void initializeGraphics() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)) ;

        JPanel container = new JPanel() ;
        container.setBackground(ContentScreen.backgroundColor);
        container.setLayout(new GridBagLayout()) ;

        // Add listeners.
        this.pseudo.addListener(new UpdatePseudoListener()) ;

        GridBagConstraints c = new GridBagConstraints() ;
        c.fill = GridBagConstraints.HORIZONTAL ;
        c.anchor = GridBagConstraints.NORTH ;

        // Label.
        JLabel title = new JLabel("Paramètres") ;
        title.setFont(FontFactory.bold("Arial", 30)) ;
        title.setBorder(
            BorderFactory.createEmptyBorder(20, 0, 20, 0)
        ) ;
        title.setHorizontalAlignment(SwingConstants.CENTER) ;
        this.add(title) ;

        // Pseudo container.
        c.weightx = 0.5 ;
        c.gridx = 0 ;
        c.gridy = 0 ;
        container.add(this.pseudo, c) ;

        // Status container.
        c.weightx = 0.5 ;
        c.gridx = 1 ;
        c.gridy = 0 ;
        container.add(new StatusContainer(this), c) ;

        this.add(container) ;
    }

}
