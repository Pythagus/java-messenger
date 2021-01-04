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
            /*
             * We need to set the socket TTL. By
             * default, the TTL value is 1. So, we
             * put 15 to access the entire local
             * network.
             *
             * @see https://perso.telecom-paristech.fr/hudry/coursJava/reseau/multicast.html
             */
            MulticastSocket socket = new MulticastSocket() ;
            socket.setTimeToLive(15) ;

            // Buffer to send.
            byte[] buffer = this.notification.serialize().getBytes() ;

            // Packet to send.
            DatagramPacket packet = new DatagramPacket(
                buffer, buffer.length, AddressUtils.getMulticastAddress(), NetworkInterface.MULTICAST_PORT
            ) ;

            // Send the datagram.
            socket.send(packet) ;
        } catch(Exception e) {
            e.printStackTrace() ;
        }
    }

}
