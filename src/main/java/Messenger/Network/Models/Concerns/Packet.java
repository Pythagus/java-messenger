package Messenger.Network.Models.Concerns;

import java.io.Serializable;
import java.net.InetAddress;

/**
 * Serial version numbers:
 * - 4242424242424242430L : Packet
 * - 4242424242424242431L : UserPacket
 * - 4242424242424242432L : MeetingPacket
 * - 4242424242424242433L : MessagePacket
 *
 * @author Damien MOLINA
 */
abstract public class Packet<T> extends DataPacket<T> implements Serializable {

    /**
     * Serialisation identifier.
     */
    private static final long serialVersionUID = 4242424242424242430L ;

    /**
     * Source address.
     */
    protected InetAddress srcAddress ;

    /**
     * Destination address.
     */
    protected InetAddress destAddress ;

    /**
     * Make a new packet instance.
     *
     * @param srcAddress : source inet address.
     * @param destAddress : destination inet address.
     */
    public Packet(InetAddress srcAddress, InetAddress destAddress) {
        this.srcAddress  = srcAddress ;
        this.destAddress = destAddress ;
    }

    /**
     * Get the source address.
     *
     * @return the Inet source address.
     */
    public InetAddress getSourceAddress() {
        return this.srcAddress ;
    }

    /**
     * Get the destination address.
     *
     * @return the Inet destination address.
     */
    public InetAddress getDestinationAddress() {
        return this.destAddress ;
    }

    /**
     * Reverse the packet addresses.
     */
    public void reverse() {
        InetAddress tmp = this.srcAddress ;

        this.srcAddress = this.destAddress ;
        this.destAddress = tmp ;
    }

}
