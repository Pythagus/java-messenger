package fr.insa.messenger.client.network.envoyers;

import java.net.InetAddress;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import fr.insa.messenger.client.network.Envoyer;
import fr.insa.messenger.client.network.NetworkInterface;
import fr.insa.messenger.client.network.utils.AddressUtils;
import fr.insa.messenger.client.network.models.BroadcastPacket;

/**
 * @author Maud Pennetier, Damien Molina
 */
public class BroadcastEnvoyer extends BaseEnvoyer {

    /**
     * Notification to broadcast
     */
    private final BroadcastPacket notification ;

    /**
     * targeted address.
     */
    private final InetAddress target ;

    /**
     * Make a new Broadcast envoyer instance.
     *
     * @param envoyer : network envoyer.
     * @param notification : notification to broadcast.
     */
    public BroadcastEnvoyer(Envoyer envoyer, BroadcastPacket notification) {
        this(envoyer, notification, AddressUtils.getBroadcastAddress()) ;
    }

    /**
     * Make a new Broadcast envoyer instance.
     *
     * @param envoyer : network envoyer.
     * @param notification : notification to broadcast.
     * @param target : targeted address.
     */
    public BroadcastEnvoyer(Envoyer envoyer, BroadcastPacket notification, InetAddress target) {
        super(envoyer) ;

        this.notification = notification ;
        this.target       = target ;
    }

    /**
     * Make the sending.
     */
    @Override
    protected void send() {
        try {
            byte[] buffer         = this.notification.serialize().getBytes();
            DatagramSocket sender = new DatagramSocket() ;

            DatagramPacket datagram = new DatagramPacket(
                buffer, buffer.length, this.target, NetworkInterface.BROADCAST_PORT
            ) ;

            datagram.setData(buffer) ;
            sender.send(datagram) ;
        } catch(Exception e) {
            e.printStackTrace() ;
        }
    }

}
