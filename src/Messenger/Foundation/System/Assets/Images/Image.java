package Messenger.Foundation.System.Assets.Images;

import Messenger.Foundation.System.Assets.Asset;
import javax.swing.*;
import java.net.URL;

/**
 * @author Damien MOLINA
 */
public class Image {

    /**
     * Get the logo URL.
     *
     * @return the logo URL.
     */
    public static URL logo() {
        return Image.resource("logo.jpg") ;
    }

    /**
     * Get the file complete URL.
     *
     * @param file : file path from the asset folder.
     * @return the entire URL.
     */
    public static URL resource(String file) {
        return Asset.resource("Images/" + file) ;
    }

    /**
     * Get the logo as a ImageIcon instance.
     *
     * @return the ImageIcon instance.
     */
    public static ImageIcon logoAsImageIcon() {
        return Image.asImageIcon(Image.logo());
    }

    /**
     * Generate an ImageIcon instance.
     *
     * @param file : file name.
     * @return an ImageIcon instance.
     */
    public static ImageIcon asImageIcon(String file) {
        return new ImageIcon(Image.resource(file)) ;
    }

    /**
     * Generate an ImageIcon instance.
     *
     * @param file : file URL.
     * @return an ImageIcon instance.
     */
    public static ImageIcon asImageIcon(URL file) {
        return new ImageIcon(file) ;
    }

}
