package Messenger.Network.Tasks.Envoyers;

import java.net.Socket;
import java.io.IOException;
import java.util.concurrent.Executors;
import Messenger.Foundation.Models.User;
import Messenger.Network.Models.Packet;
import Messenger.Network.NetworkInterface;
import java.util.concurrent.ExecutorService;
import Messenger.Network.Models.MeetingPacket;
import Messenger.Network.Models.Datagram.Stream;
import Messenger.Network.Tasks.Listeners.MeetingResponseListener;

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
     * Make a new network exchanger instance.
     */
    public NetworkEnvoyer() {
        this.executor  = Executors.newFixedThreadPool(5) ;
        this.exchanger = new Stream() ;
    }

    /**
     * Request a new connection with the
     * given user.
     *
     * @param user : user to connect with.
     * @throws IOException : socket error.
     */
    public void sendRequestMeeting(User user, Runnable onAccepted, Runnable onDenied) throws IOException {
        MeetingPacket packet = new MeetingPacket(
            new User(), user
        ) ;
        packet.setState(MeetingPacket.State.REQUEST) ;

        Socket socket = new Socket(
            packet.getDestinationAddress(), NetworkInterface.meetingPort
        ) ;

        this.send(socket, packet, false) ;

        // Start the listener at the given port.
        MeetingResponseListener listener = new MeetingResponseListener(socket) ;
        listener.setCallbackOnAccepted(onAccepted) ;
        listener.setCallbackOnDenied(onDenied) ;
        listener.start() ;
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
                this.exchanger.clear() ;
                this.exchanger.bindOutput(socket.getOutputStream()) ;
                this.exchanger.send(packet) ;
                this.exchanger.close() ;

                // Close the socket.
                if(closeSocket) {
                    //socket.close() ;
                }
            } catch (Exception e) {
                e.printStackTrace() ;
            }
        }) ;
    }

}
