package Messenger.GUI.Factories;

import javax.swing.*;
import java.net.URL;

/**
 * Get in the same class the images'
 * getters.
 *
 * @author Damien MOLINA
 */
public class GraphicImageFactory {

    // Path root to the images folder.
    private static final String FILE_ROOT = "/Messenger/Resources/Images/";

    private GraphicImageFactory() {}

    /**
     * Get the URL of the given file.
     *
     * @param file : file name.
     * @return the URL instance, or NULL.
     */
    public static URL getURL(String file) {
        return GraphicImageFactory.class.getResource(FILE_ROOT + file) ;
    }

    /**
     * Get the logo URL.
     *
     * @return the logo URL.
     */
    public static URL logo() {
        return GraphicImageFactory.getURL("logo.jpg") ;
    }

    /**
     * Generate an ImageIcon instance.
     *
     * @param file : file name.
     * @return an ImageIcon instance.
     */
    public static ImageIcon asImageIcon(String file) {
        return new ImageIcon(
                GraphicImageFactory.getURL(file)
        ) ;
    }

}
