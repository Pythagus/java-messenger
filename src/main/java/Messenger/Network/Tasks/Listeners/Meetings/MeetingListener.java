package Messenger.Network.Tasks.Listeners.Meetings;

import java.net.Socket;
import java.io.IOException;
import Messenger.Foundation.System.Env;
import Messenger.Network.Models.MeetingPacket;
import Messenger.Foundation.System.Console.Console;
//import Messenger.Foundation.Controllers.UserController;
import Messenger.Network.Tasks.Listeners.Concerns.ServerListener;
import Messenger.Network.Tasks.Listeners.Meetings.Handlers.AcceptedConnection;

/**
 * @author Damien MOLINA
 */
public class MeetingListener extends ServerListener<MeetingPacket> {

    /**
     * Make a new meeting listener instance.
     *
     * @param port : listening port.
     */
    public MeetingListener(int port) throws IOException {
        super(port) ;
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
        //UserController controller = this.getUserController() ;

        /*
         * First of all, I check if I already
         * know the source user.
         */
        // TODO : check the current-user status.
        //if(controller.hasUser(packet.getSourceUser())) {
        /*
         * I already know the user. So I refuse another
         * connection. We can use the current one !
         */
        // packet.setState(MeetingPacket.State.DENIED) ;
        //}
        /*
         * Else, the packet is valid. We can
         * accept it.
         */
        //else {
        packet.setState(MeetingPacket.State.ACCEPTED) ;
        // TODO : make a handler instead.
        new AcceptedConnection().accepted(packet.getSourceUser()) ;
        // }

        Console.comment("=> New state : " + packet.getState()) ;

        packet.reverse() ;

        try {
            Env.getNetworkInterface().getEnvoyer().send(socket, packet, true) ;
        } catch (Exception e) {
            e.printStackTrace() ;
        }
    }

}
