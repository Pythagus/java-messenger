package fr.insa.messenger.ui.screens;

import java.awt.*;
import fr.insa.messenger.ui.screens.utils.ContentType;
import fr.insa.messenger.ui.screens.utils.ContentScreen;

/**
 * @author Damien MOLINA
 */
public class SettingsScreen extends ContentScreen {

    /**
     * Make a new screen instance.
     */
    public SettingsScreen() {
        super(ContentType.SETTINGS) ;

        this.initializeGraphics() ;
    }

    /**
     * Graphically initialize the screen.
     */
    private void initializeGraphics() {
        this.setBackground(new Color(255, 0, 0));
    }

}
