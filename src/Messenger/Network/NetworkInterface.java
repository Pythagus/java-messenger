package Messenger.Network;

import Messenger.Network.Tasks.Listeners.Meetings.MeetingListener;
import Messenger.Network.Tasks.Listeners.ReceiveListener;
import Messenger.Network.Tasks.Envoyers.NetworkEnvoyer;
import java.io.IOException;

/**
 * @author Damien MOLINA
 */
public class NetworkInterface extends Thread {

    /**
     * Meeting port.
     */
    public static final int meetingPort = 60040 ;

    /**
     * Receiving port.
     */
    private static final int receivingPort = 60043 ;

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
     * Receive listener.
     * This listener receive the messages sent
     * in the receivingPort.
     */
    private final ReceiveListener receiveListener ;

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
    public NetworkInterface() throws Exception {
        // Prepare the broadcast socket.
        //TODO : replace with the listener.
       /* this.broadcastSocket = new DatagramSocket() ;
        this.broadcastSocket.setBroadcast(true) ;*/

        // Prepare the meeting listener.
        this.meetingListener = new MeetingListener(
            this, NetworkInterface.meetingPort
        ) ;

        // Prepare the receiving listener.
        this.receiveListener = new ReceiveListener(NetworkInterface.receivingPort) ;

        // Prepare the envoyer.
        this.envoyer = new NetworkEnvoyer() ;
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
     * Run the network listeners.
     */
    public void run() {
        this.meetingListener.start() ;
        this.receiveListener.start() ;
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
