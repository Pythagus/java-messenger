package Messenger.Network.Tasks.Envoyers;

import java.net.Socket;
import java.util.concurrent.Executors;
import Messenger.Foundation.Models.User;
import java.util.concurrent.ExecutorService;
import Messenger.Network.Models.Concerns.Packet;
import Messenger.Network.Models.Datagram.SocketStream;
import Messenger.Foundation.Models.Messages.MessageData;
import Messenger.Network.Models.Broadcast.BroadcastNotification;
import Messenger.Network.Tasks.Envoyers.Concerns.MeetingEnvoyer;
import Messenger.Network.Tasks.Envoyers.Concerns.MessageEnvoyer;
import Messenger.Network.Tasks.Envoyers.Concerns.BroadcastEnvoyer;

/**
 * @author Damien MOLINA
 */
public class Envoyer {

    /**
     * Executor instance.
     */
    private final ExecutorService executor ;

    /**
     * Exchanger instance.
     */
    private final SocketStream exchanger ;

    /**
     * Make a new network exchanger instance.
     */
    public Envoyer() {
        this.executor  = Executors.newFixedThreadPool(5) ;
        this.exchanger = new SocketStream() ;
    }

    /**
     * Request a new connection with the
     * given user.
     *
     * @param user : user to connect with.
     */
    public void sendRequestMeeting(User user) {
        new MeetingEnvoyer(this, user).start() ;
    }

    /**
     * Make a new message envoyer.
     *
     * @param data : data to send.
     * @param user : user to send the data to.
     */
    public void sendMessage(MessageData data, User user) {
        new MessageEnvoyer(this, data, user).start() ;
    }

    /**
     * Broadcast the given notification.
     *
     * @param notification : notification to broadcast.
     */
    public void broadcast(BroadcastNotification notification) {
        new BroadcastEnvoyer(this, notification).start() ;
    }

    /**
     * Send the given packet with the given socket.
     *
     * @param socket : socket instance.
     * @param packet : packet instance.
     */
    public void send(Socket socket, Packet<?> packet, boolean closeSocket) {
        this.executor.submit(() -> {
            try {
                // Send the packet.
                this.exchanger.clear() ;
                this.exchanger.bindOutput(socket.getOutputStream()) ;
                this.exchanger.send(packet) ;

                // Close the socket.
                if(closeSocket) {
                    this.exchanger.close() ;
                    socket.close() ;
                }
            } catch (Exception e) {
                e.printStackTrace() ;
            }
        }) ;
    }

}
