package fr.insa.messenger.network;

import java.net.Socket;
import java.net.InetAddress;
import fr.insa.messenger.models.User;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import fr.insa.messenger.models.messages.Message;
import fr.insa.messenger.network.models.basis.Packet;
import fr.insa.messenger.network.streams.SocketStream;
import fr.insa.messenger.network.models.BroadcastPacket;
import fr.insa.messenger.network.envoyers.MeetingEnvoyer;
import fr.insa.messenger.network.envoyers.MessageEnvoyer;
import fr.insa.messenger.network.envoyers.BroadcastEnvoyer;

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
     * @param message : message to send.
     */
    public void sendMessage(Message message) {
        new MessageEnvoyer(this, message).start() ;
    }

    /**
     * Broadcast the given notification.
     *
     * @param notification : notification to broadcast.
     */
    public void broadcast(BroadcastPacket notification) {
        new BroadcastEnvoyer(this, notification).start() ;
    }

    /**
     * Broadcast the given notification.
     *
     * @param notification : notification to broadcast.
     */
    public void broadcastResponse(BroadcastPacket notification, InetAddress address) {
        new BroadcastEnvoyer(this, notification, address).start() ;
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
