package fr.insa.messenger.client.models;

import java.io.File;

/**
 * @author Damien MOLINA
 */
public class MessageFile extends File {

    /**
     * Serialisation identifier.
     */
    private static final long serialVersionUID = 4242424242424242412L ;

    /**
     * Default constructor.
     *
     * @param pathname : path to the file.
     */
    public MessageFile(String pathname) {
        super(pathname) ;
    }

    /**
     * Get the file length.
     *
     * @return the file length.
     */
    public long getLength() {
        return this.length() ;
    }

    /**
     * Get the full path of the file.
     *
     * @return file path.
     */
    public String getFullPath() {
        // TODO !
        return "" ;
    }

}
