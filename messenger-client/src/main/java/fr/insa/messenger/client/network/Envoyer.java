package fr.insa.messenger.client.network;

import java.io.File;
import java.net.Socket;
import java.net.InetAddress;
import java.io.FileInputStream;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import fr.insa.messenger.client.models.User;
import fr.insa.messenger.client.models.Message;
import fr.insa.messenger.client.network.models.basis.Packet;
import fr.insa.messenger.client.network.models.MeetingPacket;
import fr.insa.messenger.client.network.streams.SocketStream;
import fr.insa.messenger.client.network.models.BroadcastPacket;
import fr.insa.messenger.client.network.envoyers.MeetingEnvoyer;
import fr.insa.messenger.client.network.envoyers.MessageEnvoyer;
import fr.insa.messenger.client.network.envoyers.MulticastEnvoyer;
import fr.insa.messenger.client.network.envoyers.BroadcastEnvoyer;
import fr.insa.messenger.client.network.models.basis.BroadcastType;

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
     * @param type : broadcast notification type.
     */
    public void broadcast(BroadcastType type) {
        this.broadcast(new BroadcastPacket(type)) ;
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












    // TODO : do it in a FileEnvoyer
    private String getExtension(File file){
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
        {
            System.out.println(fileName.substring(fileName.lastIndexOf("."))) ;
            return fileName.substring(fileName.lastIndexOf(".")) ;
        } else return ".txt";
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

                    // file extension
                    String extension = getExtension(file);
                    this.exchanger.send(extension);

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
