package Messenger.GUI.Layout.Items.Login;

import java.awt.*;
import javax.swing.*;

/**
 * @author Damien MOLINA
 */
public class uiLoginError extends JLabel {

    /**
     * make a new error label instance.
     */
    public uiLoginError() {
        this.initializeComponentGraphics() ;
    }

    /**
     * Initialize graphically the component.
     */
    private void initializeComponentGraphics() {
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setForeground(new Color(255, 0, 0));
    }

    /**
     * Update the error label content.
     *
     * @param message : message to display.
     */
    public void updateText(String message) {
        this.setText("<html>" + message + "</html>") ;

        this.revalidate() ;
        this.repaint() ;
    }

}
