package Messenger.gui.layout;

import javax.swing.*;
import java.awt.*;

/**
 * @author Damien MOLINA
 */
public class RightSide extends JPanel {

    /**
     * Make a new instance of the
     * right side panel.
     */
    public RightSide() {
        this.initializeComponentGraphics() ;
    }

    /**
     * Initialize graphically the component
     */
    private void initializeComponentGraphics() {
        this.setLayout(new OverlayLayout(this)) ;

        this.add(new uiDiscussion()) ;
        this.add(this.settings()) ; //TODO : to remove
    }


    //TODO : to remove
    private JPanel settings() {
        JPanel settings = new JPanel() ;
        settings.setBackground(new Color(178, 103, 49));
        settings.setEnabled(false);

        GroupLayout settingsLayout = new GroupLayout(settings);
        settings.setLayout(settingsLayout);
        settingsLayout.setHorizontalGroup(
                settingsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 678, Short.MAX_VALUE)
        );
        settingsLayout.setVerticalGroup(
                settingsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 787, Short.MAX_VALUE)
        );

        return settings ;
    }

}
