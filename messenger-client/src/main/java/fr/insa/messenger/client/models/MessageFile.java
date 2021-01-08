package fr.insa.messenger.client.models;

import fr.insa.messenger.client.utils.FileUtils;

import java.io.File;
import java.io.Serializable;
import java.sql.SQLException;

/**
 * Message sent between two users.
 *
 * @author Maud PENNETIER, Damien MOLINA
 */
public class MessageFile extends AbstractMessage<String> implements Serializable {

    /**
     * Serialisation identifier.
     */
    private static final long serialVersionUID = 4242424242424242411L ;

    /**
     * Temporary file name.
     */
    private String tmpFileName ;

    /**
     * File size.
     */
    private long size ;

    /**
     * Make a new Message instance.
     *
     * @param target : targeted user.
     * @param fileName : message text.
     */
    public MessageFile(User target, String fileName, String name, long size) {
        super(target, fileName) ;

        this.tmpFileName = name ;
        this.size        = size ;
    }

    /**
     * Make a new Message instance.
     *
     * @param target : targeted user.
     * @param file : file instance.
     */
    public MessageFile(User target, File file) {
        this(
            target, file.getName(), FileUtils.formatName(file.getName()), file.length()
        ) ;
    }

    /**
     * Get the temporary file name.
     *
     * @return temporary name.
     */
    public String getTemporaryName() {
        return this.tmpFileName ;
    }

    /**
     * Get the temporary file name.
     *
     * @return temporary name.
     */
    public String getOriginalName() {
        return this.getContent() ;
    }

    /**
     * Get the file size.
     *
     * @return the file size.
     */
    public long getSize() {
        return this.size ;
    }

    /**
     * Get the full path.
     *
     * @return full path.
     */
    public String getFullTemporaryPath() {
        String tmpFolder = System.getProperty("java.io.tmpdir") ;

        return tmpFolder + "/" + this.getTemporaryName() ;
    }

    /**
     * Insert the given file into the database.
     *
     * @throws SQLException : sql error.
     */
    public void databaseInsert() throws SQLException {
        this.databaseInsert(Type.FILE, this.content) ;
    }

}
