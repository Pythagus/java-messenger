package Messenger.Network.Models.Broadcast;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ByteArrayOutputStream;
import java.net.UnknownHostException;

import Messenger.Network.Models.Packet;
import Messenger.Network.Utils.AddressUtils;

/**
 * @author Damien MOLINA
 */
public class BroadcastNotification extends Packet {

    /**
     * Broadcast type.
     */
    private final BroadcastType type ;

    /**
     * Data sent with the broadcast notification.
     */
    private final Object data ;

    /**
     * Make a new BroadcastNotification instance.
     *
     * @param type : broadcast type.
     * @param data : sent data.
     */
    public BroadcastNotification(BroadcastType type, Object data) throws UnknownHostException {
        super(
            AddressUtils.getIpAddress(), AddressUtils.getBroadcastAddress()
        ) ;

        this.type = type ;
        this.data = data ;
    }

    /**
     * Serialize the current object.
     *
     * @return an array of bytes.
     * @throws IOException : impossible to serialize.
     */
    public byte[] serialize() throws IOException {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            ObjectOutputStream out ;
            out = new ObjectOutputStream(bos) ;
            out.writeObject(this) ;
            out.flush() ;

            return bos.toByteArray() ;
        }
    }

}
