package Messenger.Network.Models;

import java.io.Serializable;
import Messenger.Foundation.Models.User;
import Messenger.Foundation.Models.Messages.Message;
import Messenger.Network.Models.Concerns.UserPacket;

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
