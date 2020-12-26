package fr.insa.messenger.client.ui.factories;

import java.awt.*;
import javax.swing.*;

import fr.insa.messenger.client.utils.ColorUtils;

/**
 * @author Damien MOLINA
 */
final public class ButtonFactory {

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

    /**
     * Generate a button without any borders.
     *
     * @return the changed button.
     */
    public static JButton withoutBorder() {
        return ButtonFactory.withoutBorder(
            new JButton(), ColorUtils.transparent()
        ) ;
    }

    /**
     * Generate a button without any borders.
     *
     * @param text : button text.
     * @return the changed button.
     */
    public static JButton withoutBorder(String text) {
        return ButtonFactory.withoutBorder(
            new JButton(text), ColorUtils.transparent()
        ) ;
    }

}
