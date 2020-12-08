package Messenger.Network.Tasks.Envoyers.Concerns;

import java.net.InetAddress;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import Messenger.Network.NetworkInterface;
import Messenger.Network.Utils.AddressUtils;
import Messenger.Network.Tasks.Envoyers.Envoyer;
import Messenger.Network.Tasks.Envoyers.BaseEnvoyer;
import Messenger.Network.Models.Broadcast.BroadcastNotification;

/**
 * @author Maud Pennetier, Damien Molina
 */
public class BroadcastEnvoyer extends BaseEnvoyer {

    /**
     * Notification to broadcast
     */
    private final BroadcastNotification notification ;

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
    public BroadcastEnvoyer(Envoyer envoyer, BroadcastNotification notification) {
        this(envoyer, notification, AddressUtils.getBroadcastAddress()) ;
    }

    /**
     * Make a new Broadcast envoyer instance.
     *
     * @param envoyer : network envoyer.
     * @param notification : notification to broadcast.
     * @param target : targeted address.
     */
    public BroadcastEnvoyer(Envoyer envoyer, BroadcastNotification notification, InetAddress target) {
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
