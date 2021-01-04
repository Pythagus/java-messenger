package fr.insa.messenger.client.network.listeners;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.MulticastSocket;
import fr.insa.messenger.client.system.Env;
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
    public MulticastListener(DatagramSocket socket) {
        super(socket) ;

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
    @SuppressWarnings("InfiniteLoopStatement")
    public void run() {
        while(true) {
            try {
                Console.comment("=> MulticastListener is waiting") ;

                byte[] buffer = new byte[4096] ;
                DatagramPacket datagram = new DatagramPacket(buffer, buffer.length);

                this.listenerSocket.receive(datagram) ;

                Console.comment("=> MulticastListener received a datagram from " + datagram.getAddress()) ;

                BroadcastPacket notification = BroadcastPacket.unserialize(
                    new String(datagram.getData())
                ) ;

                if(! notification.getUser().equals(Env.getUser())) {
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
