package fr.insa.messenger.network.listeners;

import fr.insa.messenger.network.models.BroadcastPacket;
import fr.insa.messenger.system.Env;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import fr.insa.messenger.system.console.Console;

public class MulticastListener extends BroadcastListener
{


    /**
     * Make a new listener instance.
     *
     *
     * @param listenerSocket : listening socket.
     */
    public MulticastListener(DatagramSocket listenerSocket)
    {
        super(listenerSocket);
    }


    /**
     * Run the listener.
     */
    @SuppressWarnings("InfiniteLoopStatement")
    public void run() {
        while(true) {
            try {
                byte[] buffer = new byte[4096];

                Console.comment("=> MulticastListener is waiting");

                DatagramPacket datagram = new DatagramPacket(buffer, buffer.length);

                this.listenerSocket.receive(datagram) ;

                Console.comment("=> BroadcastListener received a datagram from " + datagram.getAddress()) ;

                BroadcastPacket notification = BroadcastPacket.unserialize(
                    new String(datagram.getData())
                ) ;

                if(! notification.getUser().equals(Env.getUser())) {
                    this.manageReceivedPDU(notification);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
