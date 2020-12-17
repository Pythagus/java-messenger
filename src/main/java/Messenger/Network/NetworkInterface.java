package Messenger.Network;

import java.io.IOException;
import java.net.DatagramSocket;
import Messenger.Network.Tasks.Envoyers.Envoyer;
import Messenger.Network.Models.Broadcast.BroadcastType;
import Messenger.Network.Tasks.Listeners.ReceiveListener;
import Messenger.Network.Tasks.Listeners.BroadcastListener;
import Messenger.Network.Models.Broadcast.BroadcastNotification;
import Messenger.Network.Tasks.Listeners.Meetings.MeetingListener;

/**
 * @author Damien MOLINA
 */
final public class NetworkInterface extends Thread {

    /**
     * Singleton instance.
     */
    private static NetworkInterface INSTANCE;

    /**
     * Starting application port.
     */
    private static final int STARTING_PORT = 42420 ;

    /**
     * Meeting port.
     */
    public static final int MEETING_PORT = STARTING_PORT ;

    /**
     * Receiving port.
     */
    public static final int RECEIVING_PORT = STARTING_PORT + 1 ;

    /**
     * Broadcast listening port.
     */
    public static final int BROADCAST_PORT = STARTING_PORT + 2 ;

    /**
     * Multicast listening port.
     *
     */
    public static final int MULTICAST_PORT = STARTING_PORT + 3;

    /**
     * Meeting listener.
     * This listener is used to negotiate the
     * future used port between the current user
     * and the user who sent the request.
     */
    private final MeetingListener meetingListener ;

    /**
     * Receive listener.
     * This listener receives the messages sent
     * in the receivingPort.
     */
    private final ReceiveListener receiveListener ;

    /**
     * Broadcast listener.
     * This listener receives messages from all
     * the connected users.
     */
    private final BroadcastListener broadcastListener ;

    /**
     * Envoyer instance.
     */
    private final Envoyer envoyer ;

    /**
     * Make a new instance of the network
     * interface.
     *
     * @throws IOException : broadcast error.
     */
    public NetworkInterface() throws Exception {
        // Prepare the meeting listener.
        this.meetingListener = new MeetingListener(NetworkInterface.MEETING_PORT) ;

        // Prepare the receiving listener.
        this.receiveListener = new ReceiveListener(NetworkInterface.RECEIVING_PORT) ;

        // Prepare the broadcast listener.
        this.broadcastListener = new BroadcastListener(
            new DatagramSocket(NetworkInterface.BROADCAST_PORT)
        ) ;

        // Prepare the envoyer.
        this.envoyer = new Envoyer() ;
    }

    /**
     * Get the NetworkInterface singleton instance.
     *
     * @return the singleton instance.
     */
    public static NetworkInterface instance() {
        return NetworkInterface.INSTANCE ;
    }

    /**
     * Set the NetworkInterface singleton.
     *
     * @param instance : singleton instance.
     */
    public static void setInstance(NetworkInterface instance) {
        NetworkInterface.INSTANCE = instance ;
    }

    /**
     * Get the Envoyer instance.
     *
     * @return the Envoyer instance.
     */
    public Envoyer getEnvoyer() {
        return this.envoyer ;
    }

    /**
     * Run the network listeners.
     */
    public void run() {
        this.meetingListener.start() ;
        this.receiveListener.start() ;
        this.broadcastListener.start() ;

        /*
         * When we start the network interface,
         * we should broadcast a message to know
         * how is currently logged into the
         * application.
         */
        this.envoyer.broadcast(
            new BroadcastNotification(BroadcastType.EVERYONE_INFO)
        ) ;
    }

}
