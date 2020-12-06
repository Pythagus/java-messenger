package Messenger.Network.Tasks.Listeners.Meetings;

import java.net.Socket;
import java.io.IOException;
import Messenger.Foundation.System.Env;
import Messenger.Network.NetworkInterface;
import Messenger.Network.Models.MeetingPacket;
import Messenger.Foundation.System.Console.Console;
import Messenger.Foundation.Controllers.UserController;
import Messenger.Network.Tasks.Listeners.Concerns.ServerListener;
import Messenger.Network.Tasks.Listeners.Meetings.Handlers.AcceptedConnection;

/**
 * @author Damien MOLINA
 */
public class MeetingListener extends ServerListener<MeetingPacket> {

    /**
     * Network interface instance.
     */
    private final NetworkInterface networkInterface ;

    /**
     * Make a new meeting listener instance.
     *
     * @param networkInterface : network interface.
     * @param port : listening port.
     */
    public MeetingListener(NetworkInterface networkInterface, int port) throws IOException {
        super(port) ;

        this.networkInterface = networkInterface ;
    }

    /**
     * Determine whether the packet should
     * be managed.
     *
     * @param packet : packet to manage.
     * @return True if the packet should be managed,
     * False otherwise.
     */
    protected boolean shouldManagePacket(MeetingPacket packet) {
        return packet.hasState(MeetingPacket.State.REQUEST) ;
    }

    /**
     * Manage the received packet instance.
     *
     * @param packet : received packet.
     */
    protected void manageReceivedPacket(Socket socket, MeetingPacket packet) {
        UserController controller = this.getUserController() ;

        /*
         * First of all, I check if I already
         * know the source user.
         */
        if(controller.hasUser(packet.getSourceUser())) {
            /*
             * I already know the user. So I refuse another
             * connection. We can use the current one !
             */
            packet.setState(MeetingPacket.State.DENIED) ;
        }
        /*
         * Else, the packet is valid. We can
         * accept it.
         */
        else {
            packet.setState(MeetingPacket.State.ACCEPTED) ;
            new AcceptedConnection().accepted(packet.getSourceUser()) ;
        }

        if(Env.getApplication().isDebugMode()) {
            Console.comment("=> New state : " + packet.getState()) ;
        }

        packet.reverse() ;

        try {
            this.networkInterface.getEnvoyer().send(socket, packet, true) ;
        } catch (Exception e) {
            e.printStackTrace() ;
        }
    }

}
