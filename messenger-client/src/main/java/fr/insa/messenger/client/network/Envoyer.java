package fr.insa.messenger.client.network;

import java.io.File;
import java.net.Socket;
import java.net.InetAddress;
import java.io.FileInputStream;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import fr.insa.messenger.client.models.User;
import fr.insa.messenger.client.models.Message;
import fr.insa.messenger.client.network.envoyers.*;
import fr.insa.messenger.client.system.console.Console;
import fr.insa.messenger.client.network.models.FilePacket;
import fr.insa.messenger.client.network.models.basis.Packet;
import fr.insa.messenger.client.network.models.MeetingPacket;
import fr.insa.messenger.client.network.streams.SocketStream;
import fr.insa.messenger.client.network.models.BroadcastPacket;
import fr.insa.messenger.client.network.models.basis.BroadcastType;

/**
 * @author Damien MOLINA, Maud PENNETIER
 */
final public class Envoyer {

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
    public void sendMeeting(User user, MeetingPacket.State state) {
        new MeetingEnvoyer(this, user, state).start() ;
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
     * Send the given file.
     *
     * @param target : targeted user.
     * @param file : file to send.
     */
    public void sendFile(User target, final File file) {
        new FileEnvoyer(this, target, file).start() ;
    }

    /**
     * Broadcast the given notification.
     *
     * @param notification : notification to broadcast.
     */
    @Deprecated
    public void broadcast(BroadcastPacket notification) {
        new BroadcastEnvoyer(this, notification).start() ;
    }

    /**
     * Broadcast the given notification.
     *
     * @param type : broadcast notification type.
     */
    @Deprecated
    public void broadcast(BroadcastType type) {
        this.broadcast(new BroadcastPacket(type)) ;
    }

    /**
     * Broadcast the given notification.
     *
     * @param notification : notification to broadcast.
     */
    @Deprecated
    public void broadcastResponse(BroadcastPacket notification, InetAddress address) {
        new BroadcastEnvoyer(this, notification, address).start() ;
    }

    /**
     * Multicast the given notification.
     *
     * @param notification : notification to multicast.
     */
    public void multicast(BroadcastPacket notification) {
        new MulticastEnvoyer(this, notification).start() ;
    }

    /**
     * Multicast the given notification's type.
     *
     * @param type : broadcast notification type.
     */
    public void multicast(BroadcastType type) {
        this.multicast(new BroadcastPacket(type)) ;
    }

    /**
     * Broadcast the given notification.
     *
     * @param notification : notification to broadcast.
     */
    public void multicastResponse(BroadcastPacket notification, InetAddress address) {
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
     * Send a file.
     *
     * @param socket : file socket.
     * @param packet : packet containing the file information.
     * @param stream : file stream.
     */
    public void sendFile(Socket socket, FilePacket packet, FileInputStream stream) {
        this.executor.submit(() -> {
            try {
                final int[] bytes = {0} ;

                this.exchanger.clear() ;
                this.exchanger.bindOutput(socket.getOutputStream()) ;
                this.exchanger.send(packet) ;
                Console.comment("[FILE] FilePacket sent for file " + packet.getData().getOriginalName()) ;

                // send the file in several packets
                byte[] buffer = new byte[4 * 1024] ;
                while ((bytes[0] = stream.read(buffer)) != -1) {
                    this.exchanger.sendF(buffer, 0, bytes[0]) ;
                }

                Console.comment("[FILE] file sent : " + packet.getData().getOriginalName()) ;
            } catch (Exception e) {
                e.printStackTrace() ;
            }
        }) ;
    }



    // TODO : do it in a FileEnvoyer

    /*
     * Send a file
     * @param dest to whom send the file
     * @param file File to be send
     */
    /*public void sendFile(User dest, final File file) {
        try {
            Socket socket = new Socket(dest.getAddress(), NetworkInterface.FILE_RECEIVING_PORT);

            final int[] bytes = {0};
            FileInputStream stream = new FileInputStream(file);

            this.executor.submit(() -> {
                try {
                    this.exchanger.clear();
                    this.exchanger.bindOutput(socket.getOutputStream());

                    // send the information about the file
                    FilePacket filePacket = new FilePacket(dest, file.getName(), file.length()) ;
                    Console.warning("packet sent ") ;

                    this.exchanger.send(filePacket);

                    // send the file in several packets
                    byte[] buffer = new byte[4 * 1024];
                    while ((bytes[0] = stream.read(buffer)) != -1) {
                        this.exchanger.sendF(buffer, 0, bytes[0]);
                    }
                    Console.warning("file sent ") ;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception ignored) {}
    }*/

}
