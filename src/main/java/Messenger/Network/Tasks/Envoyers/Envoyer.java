package Messenger.Network.Tasks.Envoyers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.Socket;
import java.net.InetAddress;
import java.util.concurrent.Executors;
import Messenger.Foundation.Models.User;
import java.util.concurrent.ExecutorService;
import Messenger.Network.Models.Concerns.Packet;
import Messenger.Foundation.Models.Messages.Message;
import Messenger.Network.Models.Datagram.SocketStream;
import Messenger.Network.Models.Broadcast.BroadcastNotification;
import Messenger.Network.Tasks.Envoyers.Concerns.MeetingEnvoyer;
import Messenger.Network.Tasks.Envoyers.Concerns.MessageEnvoyer;
import Messenger.Network.Tasks.Envoyers.Concerns.BroadcastEnvoyer;
import Messenger.Network.Tasks.Envoyers.Concerns.MulticastEnvoyer;

/**
 * @author Damien MOLINA, Maud PENNETIER
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
    public void broadcast(BroadcastNotification notification) {
        new BroadcastEnvoyer(this, notification).start() ;
    }

    /**
     * Broadcast the given notification.
     *
     * @param notification : notification to broadcast.
     */
    public void broadcastResponse(BroadcastNotification notification, InetAddress address) {
        new BroadcastEnvoyer(this, notification, address).start() ;
    }

    /**
     * Multicast the given notification
     *
     * @param notification : notification to multicast.
     */
    public void multicast(BroadcastNotification notification){
        new MulticastEnvoyer(this, notification).start() ;
    }

    /**
     * Broadcast the given notification.
     *
     * @param notification : notification to broadcast.
     */
    public void multicastResponse(BroadcastNotification notification, InetAddress address) {
        new MulticastEnvoyer(this, notification, address).start() ;
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

    /**
     * Send a file
     * @param socket
     * @param filePath Path to the file
     */
    public void sendFile (Socket socket, String filePath, boolean closeSocket)
    {
        try
        {
            final int[] bytes = {0};
            File file = new File(filePath);
            FileInputStream fileInputStream = new FileInputStream(file);

            this.executor.submit(() ->
            {
                try
                {
                    this.exchanger.clear();
                    this.exchanger.bindOutput(socket.getOutputStream());
                    // packet send to precise that a file will be sent
                    this.exchanger.send("BOIimfCdPSTgspWu34MbJRWzgDRO3OmY4ULRjKdb");
                    // file name without path
                    this.exchanger.send(file.getName());
                    // file size
                    this.exchanger.send(file.length());

                    byte[] buffer = new byte[4 * 1024];
                    while ((bytes[0] = fileInputStream.read(buffer)) != -1)
                    {
                        this.exchanger.sendF(buffer, 0, bytes[0]);
                    }
                    // Close the socket.
                    if(closeSocket) {
                        this.exchanger.close() ;
                        socket.close() ;
                    }
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            });

        }catch (Exception e){}
    }

}
