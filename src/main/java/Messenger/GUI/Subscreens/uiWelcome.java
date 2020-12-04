package Messenger.GUI.Subscreens;

import java.awt.*;

/**
 * @author Damien MOLINA
 */
public class uiWelcome extends SubScreen {

    /**
     * Make a new instance of the
     * uiWelcome sub screen.
     */
    public uiWelcome() {
        this.initializeComponentGraphics() ;
    }

    /**
     * Initialize graphically the component
     */
    private void initializeComponentGraphics() {
        this.setBackground(new Color(200, 255, 100));
        this.setEnabled(false);
    }

}
