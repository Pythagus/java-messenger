package Messenger.Network.Tasks.Envoyers.Concerns;

import java.net.InetAddress;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import Messenger.Network.NetworkInterface;
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
     * Make a new Broadcast envoyer instance.
     *
     * @param envoyer : network envoyer.
     * @param notification : notification to broadcast.
     */
    public BroadcastEnvoyer(Envoyer envoyer, BroadcastNotification notification) {
        super(envoyer) ;

        this.notification = notification ;
    }

    /**
     * Make the sending.
     */
    @Override
    protected void send() {
        try {
            String message = this.notification.NotifToString();
            byte[] buffer = message.getBytes();

            DatagramSocket sender = new DatagramSocket();  //create connexion

            //datagram
            // TODO : Use Messenger.Network.Utils.AddressUtils.getBroadcastAddress() instead
            InetAddress ipAdress = InetAddress.getByName("192.168.1.255"); //broadcast
            DatagramPacket datagram = new DatagramPacket(buffer, buffer.length, ipAdress, NetworkInterface.broadcastListeningPort);

            datagram.setData(buffer); //payload
            sender.send(datagram); // send packet
        } catch(Exception e) {
            e.printStackTrace() ;
        }
    }

}
