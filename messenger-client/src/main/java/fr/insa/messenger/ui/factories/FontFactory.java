package fr.insa.messenger.ui.factories;

import java.awt.*;

/**
 * @author Damien MOLINA
 */
final public class FontFactory {

    private FontFactory() {}

    /**
     * Generate a bold font.
     *
     * @param name : font name.
     * @param size : font size.
     * @return the generated font.
     */
    public static Font bold(String name, int size) {
        return FontFactory.font(name, Font.BOLD, size) ;
    }

    /**
     * Generate a normal font.
     *
     * @param size : font size.
     * @return the generated font.
     */
    public static Font normal(int size) {
        return FontFactory.normal(null, size) ;
    }

    /**
     * Generate a normal font.
     *
     * @param name : font name.
     * @param size : font size.
     * @return the generated font.
     */
    public static Font normal(String name, int size) {
        return FontFactory.font(name, Font.PLAIN, size) ;
    }

    /**
     * Generate a font.
     *
     * @param name : font name.
     * @param style : style code.
     * @param size : font size.
     * @return the generated font.
     */
    public static Font font(String name, int style, int size) {
        return new Font(name, style, size) ;
    }

}
