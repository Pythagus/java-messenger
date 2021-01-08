package fr.insa.messenger.client.network.models;

import java.io.File;
import java.io.Serializable;
import fr.insa.messenger.client.system.Env;
import fr.insa.messenger.client.models.User;
import fr.insa.messenger.client.models.MessageFile;
import fr.insa.messenger.client.network.models.basis.UserPacket;

/**
 * @author Maud PENNETIER
 *
 * Packet containing information about a file
 */
public class FilePacket extends UserPacket<MessageFile> implements Serializable {

    /**
     * Serialisation identifier.
     */
    private static final long serialVersionUID = 4242424242424242435L ;

    /**
     * Make a new file packet.
     *
     * @param target : targeted user.
     * @param file : file to send.
     */
    public FilePacket(User target, File file) {
        super(Env.getUser(), target) ;

        this.setData(new MessageFile(target, file));
    }

}
