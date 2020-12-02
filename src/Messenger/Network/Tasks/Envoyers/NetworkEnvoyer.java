package Messenger.Network.Tasks.Envoyers;

import java.net.Socket;
import java.io.IOException;
import java.util.concurrent.Executors;
import Messenger.Network.Models.Packet;
import Messenger.Network.NetworkInterface;
import java.util.concurrent.ExecutorService;
import Messenger.Network.Models.MeetingPacket;
import Messenger.Network.Models.Datagram.Stream;

/**
 * @author Damien MOLINA
 */
public class NetworkEnvoyer {

    /**
     * Executor instance.
     */
    private final ExecutorService executor ;

    /**
     * Exchanger instance.
     */
    private final Stream exchanger ;

    /**
     * Network interface instance.
     */
    private final NetworkInterface networkInterface ;

    /**
     * Make a new network exchanger instance.
     */
    public NetworkEnvoyer(NetworkInterface networkInterface) {
        this.executor         = Executors.newFixedThreadPool(5) ;
        this.exchanger        = new Stream() ;
        this.networkInterface = networkInterface ;
    }

    /**
     * Send the given packet.
     *
     * @param packet : packet to send.
     */
    public void send(Packet packet, boolean closeSocket) {
        try {
            this.send(
                new Socket(
                    packet.getDestinationAddress(), NetworkInterface.receivingPort
                ), packet, closeSocket
            ) ;
        } catch (IOException exception) {
            exception.printStackTrace() ;
        }
    }

    /**
     * Send the given meeting packet.
     *
     * @param packet : packet instance.
     */
    public void send(MeetingPacket packet, boolean closeSocket) {
        /*
         * If the packet is requested, then
         * we add it into the requested list.
         */
        if(packet.hasState(MeetingPacket.State.REQUEST)) {
            this.networkInterface.getMeetingListener().addRequestedPacket(packet) ;
        }

        this.send((Packet) packet, closeSocket) ;
    }

    /**
     * Send the given packet with the given socket.
     *
     * @param socket : socket instance.
     * @param packet : packet instance.
     */
    public void send(Socket socket, Packet packet, boolean closeSocket) {
        this.executor.submit(() -> {
            try {
                // Send the packet.
                this.exchanger.bindOutput(socket.getOutputStream()) ;
                this.exchanger.send(packet) ;
                this.exchanger.close() ;

                // Close the socket.
                if(closeSocket) {
                    socket.close() ;
                }
            } catch (Exception e) {
                e.printStackTrace() ;
            }
        }) ;
    }

}
