package Messenger.Network.Tasks.Envoyers.Concerns;

import java.net.Socket;
import java.io.IOException;
import java.net.NoRouteToHostException;
import Messenger.Foundation.Models.User;
import Messenger.Network.NetworkInterface;
import Messenger.Network.Models.MeetingPacket;
import Messenger.Network.Tasks.Envoyers.Envoyer;
import Messenger.Network.Tasks.Envoyers.BaseEnvoyer;
import Messenger.Network.Tasks.Listeners.Meetings.MeetingResponseListener;
import Messenger.Network.Tasks.Listeners.Meetings.Handlers.DeniedConnection;
import Messenger.Network.Tasks.Listeners.Meetings.Handlers.AcceptedConnection;

/**
 * @author Damien MOLINA
 */
public class MeetingEnvoyer extends BaseEnvoyer {

    /**
     * Generated meeting packet.
     */
    private final MeetingPacket packet ;

    /**
     * Make a new Meeting envoyer instance.
     *
     * @param envoyer : envoyer instance.
     * @param user : user to connect with.
     */
    public MeetingEnvoyer(Envoyer envoyer, User user) {
        super(envoyer, user) ;

        // Make the meeting packet.
        this.packet = new MeetingPacket(new User(), user) ;
        this.packet.setState(MeetingPacket.State.REQUEST) ;
    }

    /**
     * Make the sending.
     */
    protected void send() throws IOException {
        DeniedConnection deniedCallback = new DeniedConnection() ;

        try {
            // Make the socket.
            Socket socket = new Socket(
                this.packet.getDestinationAddress(), NetworkInterface.meetingPort
            ) ;

            this.envoyer.send(
                socket, this.packet, false
            ) ;

            // Start the listener at the given port.
            MeetingResponseListener listener = new MeetingResponseListener(socket) ;
            listener.setCallbacks(
                new AcceptedConnection(), deniedCallback
            ) ;
            listener.start() ;
        }

        /*
         * This exception is thrown when the
         * user is not reachable in the network.
         */
        catch (NoRouteToHostException e) {
            deniedCallback.unreachableUser(e, this.user) ;
        }
    }

}
