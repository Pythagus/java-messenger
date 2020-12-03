package Messenger.Network.Models.Broadcast;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ByteArrayOutputStream;
import java.net.UnknownHostException;

import Messenger.Foundation.Models.User;
import Messenger.Network.Models.Concerns.Packet;
import Messenger.Network.Utils.AddressUtils;

/**
 * @author Damien MOLINA
 */
public class BroadcastNotification extends Packet<User> {

    /**
     * Broadcast type.
     */
    private final BroadcastType type ;

    /**
     * Make a new BroadcastNotification instance.
     *
     * @param type : broadcast type.
     * @param user : user sending the notification.
     */
    public BroadcastNotification(BroadcastType type, User user) throws UnknownHostException {
        super(
            AddressUtils.getIpAddress(), AddressUtils.getBroadcastAddress()
        ) ;

        this.type = type ;
        this.setData(user) ;
    }

    /**
     * Serialize the current object.
     *
     * @return an array of bytes.
     * @throws IOException : impossible to serialize.
     */
    public byte[] serialize() throws IOException {
        // TODO : do it with serialize
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            ObjectOutputStream out ;
            out = new ObjectOutputStream(bos) ;
            out.writeObject(this) ;
            out.flush() ;

            return bos.toByteArray() ;
        }
    }

}
