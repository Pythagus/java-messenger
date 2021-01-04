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
public class MulticastEnvoyer extends BroadcastEnvoyer {

    /**
     * Make a new Multicast envoyer instance.
     *
     * @param envoyer : network envoyer.
     * @param notification : notification to broadcast.
     */
    public MulticastEnvoyer(Envoyer envoyer, BroadcastPacket notification) {
        this(envoyer, notification, AddressUtils.getMulticastAddress()) ;
    }

    /**
     * Make a new Multicast envoyer instance.
     *
     * @param envoyer : network envoyer.
     * @param notification : notification to broadcast.
     * @param target : targeted address.
     */
    public MulticastEnvoyer(Envoyer envoyer, BroadcastPacket notification, InetAddress target) {
        super(envoyer, notification, target) ;
    }

    /**
     * Send the multicast packet.
     */
    @Override
    protected void send() {
        try {
            byte[] buffer = this.notification.serialize().getBytes() ;

            // Prepare and join the multicast group.
            MulticastSocket socket = new MulticastSocket() ;
            socket.joinGroup(this.target) ;
            //socket.setTimeToLive() ;  // TODO : set the socket TTL

            // Prepare the datagram to send.
            DatagramPacket datagram = new DatagramPacket(
                    buffer, buffer.length, this.target, NetworkInterface.MULTICAST_PORT
            ) ;
            datagram.setData(buffer) ;

            // Send the datagram.
            socket.send(datagram) ;
        } catch(Exception e) {
            e.printStackTrace() ;
        }
    }

}
