package Messenger.gui.utils;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ThreadFactory;

/**
 * @author Damien MOLINA
 */
public class ButtonFactory {

    private ButtonFactory() {}

    /**
     * Generate a button without any borders.
     *
     * @param button : button to update.
     * @param color : background button color.
     * @return the changed button.
     */
    public static JButton withoutBorder(JButton button, Color color) {
        button.setHorizontalTextPosition(SwingConstants.CENTER) ;
        button.setBackground(color) ;
        button.setFocusPainted(false) ;
        button.setBorderPainted(false) ;
        button.setContentAreaFilled(false) ;
        button.setCursor(new Cursor(Cursor.HAND_CURSOR)) ;

        return button ;
    }

    /**
     * Generate a button without any borders.
     *
     * @param color : background button color.
     * @return the changed button.
     */
    public static JButton withoutBorder(Color color) {
        return ButtonFactory.withoutBorder(new JButton(), color) ;
    }

}
