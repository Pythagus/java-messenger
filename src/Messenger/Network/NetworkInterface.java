package Messenger.Network;

import java.net.*;
import java.io.IOException;
import Messenger.Network.Tasks.Envoyers.NetworkEnvoyer;
import Messenger.Network.Tasks.Listeners.MeetingListener;

/**
 * @author Damien MOLINA
 */
public class NetworkInterface extends Thread {

    /**
     * Meeting port.
     */
    private static final int meetingPort = 60042 ;

    /**
     * Receiving port.
     */
    public static final int receivingPort = 60043 ;

    /**
     * Sending port.
     */
    private static final int sendingPort = 60044 ;

    /*
     * Broadcast socket.
     */
    //private final DatagramSocket broadcastSocket ;

    /*
     * Meeting listener.
     * This listener is used to negotiate the
     * future used port between the current user
     * and the user who sent the request.
     */
    private final MeetingListener meetingListener ;

    /**
     * Envoyer instance.
     */
    private final NetworkEnvoyer envoyer ;

    /**
     * Make a new instance of the network
     * interface.
     *
     * @throws IOException : broadcast error.
     */
    public NetworkInterface() throws IOException {
        // Prepare the broadcast socket.
        //TODO : replace with the listener.
       /* this.broadcastSocket = new DatagramSocket() ;
        this.broadcastSocket.setBroadcast(true) ;*/

        // Prepare the meeting listener.
        this.meetingListener = new MeetingListener(
            this, new ServerSocket(NetworkInterface.meetingPort)
        ) ;

        // Prepare the envoyer.
        this.envoyer = new NetworkEnvoyer(this) ;
    }

    /**
     * Get the Envoyer instance.
     *
     * @return the Envoyer instance.
     */
    public NetworkEnvoyer getEnvoyer() {
        return this.envoyer ;
    }

    /**
     * Get the MeetingListener instance.
     *
     * @return the listener instance.
     */
    public MeetingListener getMeetingListener() {
        return this.meetingListener ;
    }

    /**
     * Run the network listeners.
     */
    public void run() {
        this.meetingListener.start() ;
    }























    /*
     * Broadcast the given type and data.
     *
     * @param type : broadcast type.
     * @param data : data type.
     * @throws IOException : broadcast error.
     */
   /* public void broadcast(BroadcastType type, Object data) throws IOException {
        this.broadcast(new BroadcastNotification(type, data) ) ;
    }*/

    /*
     * Broadcast the given notification.
     *
     * @param notification : notification instance.
     * @throws IOException : broadcast error.
     */
    /*public void broadcast(BroadcastNotification notification) throws IOException {
        this.broadcast(notification.serialize()) ;
    }*/

    /*
     * Broadcast the given buffer.
     *
     * @param buffer : bytes array.
     * @throws IOException : broadcast error.
     */
    /*public void broadcast(byte[] buffer) throws IOException {
        // Prepare the datagram.
        DatagramPacket packet = new DatagramPacket(
            buffer, buffer.length, AddressUtils.getBroadcastAddress(), this.broadcastPort
        ) ;

        // Send the datagram.
        this.broadcastSocket.send(packet) ;
        this.broadcastSocket.close() ;
    }*/

}
