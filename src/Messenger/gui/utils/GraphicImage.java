package Messenger.gui.utils;

import javax.swing.*;
import java.net.URL;

/**
 * Get in the same class the images'
 * getters.
 *
 * @author Damien MOLINA
 */
public class GraphicImage {

    // Path root to the images folder.
    private static final String FILE_ROOT = "/Messenger/resources/images/" ;

    private GraphicImage() {}

    /**
     * Get the URL of the given file.
     *
     * @param file : file name.
     * @return the URL instance, or NULL.
     */
    public static URL getURL(String file) {
        return GraphicImage.class.getResource(FILE_ROOT + file) ;
    }

    /**
     * Get the logo URL.
     *
     * @return the logo URL.
     */
    public static URL logo() {
        return GraphicImage.getURL("logo.jpg") ;
    }

    /**
     * Generate an ImageIcon instance.
     *
     * @param file : file name.
     * @return an ImageIcon instance.
     */
    public static ImageIcon asImageIcon(String file) {
        return new ImageIcon(
                GraphicImage.getURL(file)
        ) ;
    }

}
