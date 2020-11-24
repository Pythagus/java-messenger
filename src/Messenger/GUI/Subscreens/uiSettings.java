package Messenger.GUI.Subscreens;

import javax.swing.*;
import java.awt.*;

/**
 * @author Damien MOLINA
 */
public class uiSettings extends SubScreen {

    //TODO : to do !

    /**
     * Make a new instance of the
     * uiSettings sub screen.
     */
    public uiSettings() {
        this.initializeComponentGraphics() ;
    }

    /**
     * Initialize graphically the component
     */
    private void initializeComponentGraphics() {
        this.setBackground(new Color(178, 103, 49));
        this.setEnabled(false);

        /*GroupLayout settingsLayout = new GroupLayout(this) ;
        this.setLayout(settingsLayout);
        settingsLayout.setHorizontalGroup(
            settingsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGap(0, 678, Short.MAX_VALUE)
        );
        settingsLayout.setVerticalGroup(
            settingsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGap(0, 787, Short.MAX_VALUE)
        );*/
    }

}
