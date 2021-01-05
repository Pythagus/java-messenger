package fr.insa.messenger.client.network.models.basis;

import java.io.Serializable;

/**
 * @author Maud PENNETIER
 *
 * Packet containing information about a file
 */
public class FilePacket implements Serializable {

    /**
     * Serialisation identifier.
     */
    private static final long serialVersionUID = 4242424242424242435L ;

    private String name;
    private String extension;
    private long size;

    public FilePacket(String nName, String nExt, long nSize) {
        this.name = nName;
        this.extension = nExt;
        this.size = nSize;
    }

    /**
     * Get the name of the file
     * @return the name in String
     */
    public String getName(){ return this.name; }

    /**
     * Get the extension of the file
     * @return the extension in String
     */
    public String getExtension(){ return this.extension; }

    /**
     * Get the size of the file
     * @return the size in long
     */
    public long getSize(){ return this.size; }

}
