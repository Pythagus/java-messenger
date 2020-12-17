package Messenger.GUI.Frames.Screens;

import java.awt.*;
import Messenger.GUI.Frames.Screens.Utils.ContentType;
import Messenger.GUI.Frames.Screens.Utils.ContentScreen;

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
