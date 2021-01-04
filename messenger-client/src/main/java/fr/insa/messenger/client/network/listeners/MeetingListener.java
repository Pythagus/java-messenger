package fr.insa.messenger.client.network.listeners;

import java.net.Socket;
import java.io.IOException;
import fr.insa.messenger.client.system.console.Console;
import fr.insa.messenger.client.network.NetworkInterface;
import fr.insa.messenger.client.network.models.MeetingPacket;
import fr.insa.messenger.client.network.listeners.handlers.QuitHandler;
import fr.insa.messenger.client.network.listeners.handlers.AcceptedHandler;

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
        return packet.hasState(MeetingPacket.State.REQUEST) || packet.hasState(MeetingPacket.State.LEAVE) ;
    }

    /**
     * Manage the received packet instance.
     *
     * @param packet : received packet.
     */
    protected void manageReceivedPacket(Socket socket, MeetingPacket packet) {
        //UserController controller = this.getUserController() ;

        switch(packet.getState()) {
            case REQUEST:
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
                this.acceptPacket(socket, packet) ;
                // }
                break ;
            case LEAVE:
                new QuitHandler().handle(packet.getSourceUser()) ;
                break ;
        }
    }

    /**
     * Accept the given packet.
     *
     * @param socket : incoming socket.
     * @param packet : incoming packet.
     */
    private void acceptPacket(Socket socket, MeetingPacket packet) {
        packet.setState(MeetingPacket.State.ACCEPTED) ;
        new AcceptedHandler().handle(packet.getSourceUser()) ;

        // Send the response.
        this.sendAgain(socket, packet) ;
    }

    /**
     * Refuse the given packet.
     *
     * @param socket : incoming socket.
     * @param packet : incoming packet.
     */
    private void refusePacket(Socket socket, MeetingPacket packet) {
        packet.setState(MeetingPacket.State.DENIED) ;

        // Send the response.
        this.sendAgain(socket, packet) ;
    }

    /**
     * Send again the given socket and packet.
     *
     * @param socket : incoming socket.
     * @param packet : incoming packet.
     */
    private void sendAgain(Socket socket, MeetingPacket packet) {
        Console.comment("=> New state : " + packet.getState()) ;

        packet.reverse() ;

        try {
            NetworkInterface.instance().getEnvoyer().send(socket, packet, true) ;
        } catch (Exception e) {
            e.printStackTrace() ;
        }
    }

}
