package Messenger.Network.Tasks.Envoyers.Concerns;

import java.net.InetAddress;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.MulticastSocket;
import Messenger.Network.NetworkInterface;
import Messenger.Network.Utils.AddressUtils;
import Messenger.Network.Tasks.Envoyers.Envoyer;
import Messenger.Network.Tasks.Envoyers.BaseEnvoyer;
import Messenger.Network.Models.Broadcast.BroadcastNotification;

/**
 * @author Maud Pennetier
 */
public class MulticastEnvoyer extends BaseEnvoyer {

    /**
     * Notification to broadcast
     */
    private BroadcastNotification notification ;

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
    public MulticastEnvoyer(Envoyer envoyer, BroadcastNotification notification) {
        this(envoyer, notification, AddressUtils.getBroadcastAddress()) ;
    }

    /**
     * Make a new Multicast envoyer instance.
     *
     * @param envoyer : network envoyer.
     * @param notification : notification to broadcast.
     * @param target : targeted address.
     */
    public MulticastEnvoyer(Envoyer envoyer, BroadcastNotification notification, InetAddress target) {
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
