package Messenger.Network.Models;

import java.io.Serializable;
import java.net.InetAddress;

/**
 * @author Damien MOLINA
 */
abstract public class Packet implements Serializable {

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

    /**
     * Reverse the packet addresses.
     */
    public void reverse() {
        InetAddress tmp = this.srcAddress ;

        this.srcAddress = this.destAddress ;
        this.destAddress = tmp ;
    }

}
