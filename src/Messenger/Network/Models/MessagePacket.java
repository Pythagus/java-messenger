package Messenger.Network.Models;

import Messenger.Foundation.Models.User;
import Messenger.Network.Models.Concerns.UserPacket;
import Messenger.Foundation.Models.Messages.MessageData;

/**
 * @author Damien MOLINA
 */
public class MessagePacket extends UserPacket<MessageData> {

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
