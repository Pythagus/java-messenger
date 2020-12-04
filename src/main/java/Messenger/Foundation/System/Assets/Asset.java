package Messenger.Foundation.System.Assets;

import java.io.InputStream;
import java.net.URL;

/**
 * @author Damien MOLINA
 */
abstract public class Asset {

    // Path root to the assets folder.
    protected static final String ROOT = "/" ;

    /**
     * Get the file complete URL.
     *
     * @param file : file path from the asset folder.
     * @return the entire URL.
     */
    public static URL resource(String file) {
        return Asset.class.getResource(Asset.ROOT + file) ;
    }

    /**
     * Get the file resource as a stream.
     *
     * @param file : file path from the asset folder.
     * @return the file stream.
     */
    public static InputStream resourceAsStream(String file) {
        return Asset.class.getResourceAsStream(Asset.ROOT + file) ;
    }

}
