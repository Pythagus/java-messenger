package fr.insa.messenger.client.network.listeners;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;
import fr.insa.messenger.client.models.User;
import fr.insa.messenger.client.system.console.Console;
import fr.insa.messenger.client.network.utils.AddressUtils;
import fr.insa.messenger.client.network.models.BroadcastPacket;

/**
 * @author Maud PENNETIER
 */
public class MulticastListener extends BroadcastListener {

    /**
     * Make a new listener instance.
     *
     * @param socket : listening socket.
     */
    public MulticastListener(MulticastSocket socket) {
        super(socket, "Multicast") ;

        try {
            ((MulticastSocket) this.listenerSocket).joinGroup(
                AddressUtils.getMulticastAddress()
            ) ;
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Run the listener.
     */
    public void run() {
        while(this.run) {
            try {
                Console.comment("=> MulticastListener is waiting") ;

                byte[] buffer = new byte[4096] ;
                DatagramPacket datagram = new DatagramPacket(buffer, buffer.length);

                this.listenerSocket.receive(datagram) ;

                Console.comment("=> MulticastListener received a datagram from " + datagram.getAddress()) ;

                BroadcastPacket notification = BroadcastPacket.unserialize(
                    new String(datagram.getData())
                ) ;

                User sender = notification.getUser() ;
                if(sender.getPseudo() != null && ! sender.isEnvUser()) {
                    this.manageReceivedPDU(notification) ;
                } else {
                    Console.warning("Multicast packet not managed") ;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
