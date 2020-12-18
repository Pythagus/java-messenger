package fr.insa.messenger.network.listeners;

import java.net.Socket;
import java.io.IOException;
import fr.insa.messenger.system.console.Console;
import fr.insa.messenger.network.NetworkInterface;
import fr.insa.messenger.network.models.MeetingPacket;
import fr.insa.messenger.network.listeners.handlers.AcceptedHandler;

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
        new AcceptedHandler().handle(packet.getSourceUser()) ;
        // }

        Console.comment("=> New state : " + packet.getState()) ;

        packet.reverse() ;

        try {
            NetworkInterface.instance().getEnvoyer().send(socket, packet, true) ;
        } catch (Exception e) {
            e.printStackTrace() ;
        }
    }

}
