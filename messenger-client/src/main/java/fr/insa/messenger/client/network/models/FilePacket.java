package fr.insa.messenger.client.network.models;

import java.io.Serializable;
import fr.insa.messenger.client.system.Env;
import fr.insa.messenger.client.models.User;
import fr.insa.messenger.client.utils.FileUtils;
import fr.insa.messenger.client.network.models.basis.UserPacket;

/**
 * @author Maud PENNETIER
 *
 * Packet containing information about a file
 */
public class FilePacket extends UserPacket<Object> implements Serializable {

    /**
     * Serialisation identifier.
     */
    private static final long serialVersionUID = 4242424242424242435L ;

    /**
     * Original file name.
     */
    private final String originalName ;

    /**
     * File formatted name.
     */
    private final String name ;

    /**
     * File size.
     */
    private final long size ;

    /**
     * Make a new file packet.
     *
     * @param target : targeted user.
     * @param originalName : original file name.
     * @param size : file size.
     */
    public FilePacket(User target, String originalName, long size) {
        this(target, originalName, size, FileUtils.formatName(originalName)) ;
    }

    /**
     * Make a new file packet.
     *
     * @param target : targeted user.
     * @param originalName : original file name.
     * @param size : file size.
     * @param name : file name.
     */
    public FilePacket(User target, String originalName, long size, String name) {
        super(Env.getUser(), target) ;

        this.originalName = originalName ;
        this.size         = size ;
        this.name         = name ;
    }

    /**
     * Get the name of the file.
     *
     * @return the name in String.
     */
    public String getOriginalName() {
        return this.originalName ;
    }

    /**
     * Get the file name.
     *
     * @return file name.
     */
    public String getName() {
        return this.name ;
    }

    /**
     * Get the size of the file.
     *
     * @return the size in long.
     */
    public long getSize() {
        return this.size ;
    }

    /**
     * Get the full path.
     *
     * @return full path.
     */
    public String getFullPath() {
        String tmpFolder = System.getProperty("java.io.tmpdir") ;

        return tmpFolder + "/Messenger" + this.getName() ;
    }

}
