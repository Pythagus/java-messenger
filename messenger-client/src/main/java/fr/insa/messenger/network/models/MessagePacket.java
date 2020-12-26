package fr.insa.messenger.network.models;

import java.io.Serializable;
import fr.insa.messenger.models.User;
import fr.insa.messenger.models.messages.Message;
import fr.insa.messenger.network.models.basis.UserPacket;

/**
 * @author Damien MOLINA
 */
public class MessagePacket extends UserPacket<Message> implements Serializable {

    /**
     * Serialisation identifier.
     */
    private static final long serialVersionUID = 4242424242424242433L ;

    /**
     * Make a new packet instance.
     *
     * @param srcUser  : source user instance.
     * @param destUser : destination user instance.
     */
    public MessagePacket(User srcUser, User destUser) {
        super(srcUser, destUser);
    }

}
