package Messenger.Network.Models;

import java.io.Serializable;
import java.net.InetAddress;

/**
 * @author Damien MOLINA
 */
abstract public class Packet implements Serializable {

    /**
     * Source address.
     */
    protected final InetAddress srcAddress ;

    /**
     * Destination address.
     */
    protected final InetAddress destAddress ;

    /**
     * Packet data.
     */
    private Object data ;

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
     * Get the packet data.
     *
     * @return the data.
     */
    public Object getData() {
        return this.data ;
    }

    /**
     * Set the packet data.
     *
     * @param data : packet data.
     */
    public void setData(Object data) {
        this.data = data ;
    }

}
