package fr.insa.messenger.client.network;

import java.io.IOException;
import java.net.MulticastSocket;
import java.net.InetSocketAddress;
import fr.insa.messenger.client.network.utils.AddressUtils;
import fr.insa.messenger.client.network.listeners.MeetingListener;
import fr.insa.messenger.client.network.listeners.ReceiveListener;
import fr.insa.messenger.client.network.models.basis.BroadcastType;
import fr.insa.messenger.client.network.listeners.MulticastListener;
import fr.insa.messenger.client.network.listeners.FileListener;

/**
 * @author Damien MOLINA
 */
final public class NetworkInterface extends Thread {

    /**
     * Singleton instance.
     */
    private static NetworkInterface INSTANCE ;

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
    public static final int MULTICAST_PORT = STARTING_PORT + 3 ;

    /**
     * File listening port
     */
    public static final int FILE_RECEIVING_PORT = STARTING_PORT + 4;

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

    /*
     * Broadcast listener.
     * This listener receives messages from all
     * the connected users.
     */
    //private final BroadcastListener broadcastListener ;

    /**
     * Multicast listener.
     * This listener receives messages from all
     * the connected users.
     */
    private final MulticastListener multicastListener ;

    /**
     * FileListener.
     * This listener is dedicated to the file that are coming
     * regardless the sender
     */
    private final FileListener fileReceiveListener;

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
        /*this.broadcastListener = new BroadcastListener(
            new DatagramSocket(NetworkInterface.BROADCAST_PORT)
        ) ;*/

        // Prepare the multicast listener.
        this.multicastListener = new MulticastListener(new MulticastSocket(
            new InetSocketAddress(AddressUtils.getMulticastAddress(), NetworkInterface.MULTICAST_PORT)
        )) ;

        // Prepare the file listener.
        this.fileReceiveListener = new FileListener(NetworkInterface.FILE_RECEIVING_PORT) ;

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
        //this.broadcastListener.start() ;
        this.multicastListener.start() ;
        this.fileReceiveListener.start() ;

        /*
         * When we start the network interface,
         * we should broadcast a message to know
         * how is currently logged into the
         * application.
         */
        //this.envoyer.broadcast(BroadcastType.EVERYONE_INFO) ;

        /*
         * When we start the network interface,
         * we should multicast a message to know
         * how is currently logged into the
         * application.
         */
        this.envoyer.multicast(BroadcastType.EVERYONE_INFO) ;
    }

}
