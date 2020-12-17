package Messenger.Network.Tasks.Listeners;


import Messenger.Foundation.System.Console.Console;
import Messenger.Foundation.System.Env;
import Messenger.Network.Models.Broadcast.BroadcastNotification;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

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

                if(Env.getApplication().isDebugMode()) {
                    Console.comment("=> MulticastListener is waiting");
                }

                DatagramPacket datagram = new DatagramPacket(buffer, buffer.length);

                this.listenerSocket.receive(datagram) ;

                if(Env.getApplication().isDebugMode()) {
                    Console.comment("=> BroadcastListener received a datagram from " + datagram.getAddress()) ;
                }

                BroadcastNotification notification = BroadcastNotification.unserialize(
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
