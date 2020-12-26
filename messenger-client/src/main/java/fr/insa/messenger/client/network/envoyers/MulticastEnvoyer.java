package fr.insa.messenger.client.network.envoyers;

import java.net.InetAddress;
import java.net.DatagramPacket;
import java.net.MulticastSocket;
import fr.insa.messenger.client.network.Envoyer;
import fr.insa.messenger.client.network.NetworkInterface;
import fr.insa.messenger.client.network.utils.AddressUtils;
import fr.insa.messenger.client.network.models.BroadcastPacket;

/**
 * @author Maud Pennetier
 */
public class MulticastEnvoyer extends BaseEnvoyer {

    /**
     * Notification to broadcast
     */
    private BroadcastPacket notification ;

    /**
     * targeted address.
     */
    private InetAddress target ;

    /**
     * Make a new Multicast envoyer instance.
     *
     * @param envoyer : network envoyer.
     * @param notification : notification to broadcast.
     */
    public MulticastEnvoyer(Envoyer envoyer, BroadcastPacket notification) {
        this(envoyer, notification, AddressUtils.getBroadcastAddress()) ;
    }

    /**
     * Make a new Multicast envoyer instance.
     *
     * @param envoyer : network envoyer.
     * @param notification : notification to broadcast.
     * @param target : targeted address.
     */
    public MulticastEnvoyer(Envoyer envoyer, BroadcastPacket notification, InetAddress target) {
        super(envoyer);
        this.notification = notification ;
        this.target       = target ;
    }

    /**
     * Make the sending.
     *
     */
    @Override
    protected void send() {
        try {
            byte[] buffer = this.notification.serialize().getBytes();
            MulticastSocket socket = new MulticastSocket() ;
            // join multicast
            socket.joinGroup(this.target);

            DatagramPacket datagram = new DatagramPacket(
                    buffer, buffer.length, this.target, NetworkInterface.MULTICAST_PORT
            ) ;

            datagram.setData(buffer) ;
            socket.send(datagram) ;  // set ttl
        } catch(Exception e) {
            e.printStackTrace() ;
        }
    }

}
