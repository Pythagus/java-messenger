package Messenger.Network;

import Messenger.Network.Tasks.Listeners.Meetings.MeetingListener;
import Messenger.Network.Tasks.Listeners.BroadcastListener;
import Messenger.Network.Tasks.Listeners.ReceiveListener;
import Messenger.Network.Tasks.Envoyers.Envoyer;
import java.net.DatagramSocket;
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
    public static final int receivingPort = 60043 ;

    /**
     * Broadcast listening port.
     */
    public static final int broadcastListeningPort = 60044 ;

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
        this.meetingListener = new MeetingListener(
            this, NetworkInterface.meetingPort
        ) ;

        // Prepare the receiving listener.
        this.receiveListener = new ReceiveListener(NetworkInterface.receivingPort) ;

        // Prepare the broadcast listener.
        this.broadcastListener = new BroadcastListener(
            new DatagramSocket(NetworkInterface.broadcastListeningPort)
        ) ;

        // Prepare the envoyer.
        this.envoyer = new Envoyer() ;
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
    }

}
