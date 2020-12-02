package Messenger.Network.Tasks.Listeners;

import java.net.Socket;
import java.io.IOException;
import java.util.ArrayList;
import java.net.ServerSocket;
import java.io.ObjectInputStream;
import Messenger.Foundation.Environment;
import Messenger.Network.NetworkInterface;
import Messenger.Network.Models.MeetingPacket;
import Messenger.Foundation.Controllers.UserController;
import Messenger.Network.Tasks.Listeners.Concerns.NetworkBaseListener;

/**
 * @author Damien MOLINA
 */
public class MeetingListener extends NetworkBaseListener<ServerSocket> {

    /**
     * Requested packets list.
     */
    private final ArrayList<MeetingPacket> requestedPackets ;

    /**
     * Network interface instance.
     */
    private final NetworkInterface networkInterface ;

    /**
     * Make a new meeting listener instance.
     *
     * @param socket : socket network.
     */
    public MeetingListener(NetworkInterface networkInterface, ServerSocket socket) {
        super(socket) ;

        this.requestedPackets = new ArrayList<>() ;
        this.networkInterface = networkInterface ;
    }

    /**
     * Add the given packet to the waiting
     * packets list.
     *
     * @param packet : new requested packet.
     */
    public void addRequestedPacket(MeetingPacket packet) {
        this.requestedPackets.add(packet) ;
    }

    /**
     * Run the listener.
     */
    @SuppressWarnings("InfiniteLoopStatement")
    public void run() {
        while(true) {
            try {
                Socket socket        = this.socket.accept() ;
                ObjectInputStream is = new ObjectInputStream(socket.getInputStream());

                this.manageReceivedPacket(
                    socket, (MeetingPacket) is.readObject()
                ) ;
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Manage the received packet instance.
     *
     * @param packet : received packet.
     */
    protected void manageReceivedPacket(Socket socket, MeetingPacket packet) {
        switch (packet.getState()) {
            /*
             * This packet is the first one
             * to start a connection.
             */
            case REQUEST: this.manageRequestedPacket(socket, packet) ; break;

            /*
             * The request was accepted. We can now
             * contact the other user in the correct
             * port.
             */
            case ACCEPTED: if(this.shouldManagePacket(socket, packet)) {
                this.manageAcceptedPacket(packet) ;
            } break ;

            /*
             * The request was refused. We need to remove
             * the packet from the temporary list.
             */
            case DENIED: if(this.shouldManagePacket(socket, packet)) {
                this.manageDeniedPacket(packet) ;
            } break ;
        }
    }

    /**
     * Determine whether the packet should be managed.
     *
     * @param socket : socket to check.
     * @param packet : packet to manage.
     * @return True or False
     */
    private boolean shouldManagePacket(Socket socket, MeetingPacket packet) {
        if(this.requestedPackets.contains(packet)) {
            return true ;
        }

        try {
            socket.close() ;
        } catch (IOException exception) {
            exception.printStackTrace() ;
        }

        return false ;
    }

    /**
     * Get the User Controller.
     *
     * @return the UserController instance.
     */
    private UserController getUserController() {
        return (UserController) Environment.getController(UserController.class) ;
    }

    /**
     * Manage a Meeting packet instance.
     *
     * @param packet : MeetingPacket instance.
     */
    private void manageRequestedPacket(Socket socket, MeetingPacket packet) {
        UserController controller = this.getUserController() ;

        /*
         * First of all, I check if I already
         * know the source user.
         */
        if(controller.hasUser(packet.getSourceUser()) && ! this.requestedPackets.contains(packet)) {
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
            controller.addUser(packet.getSourceUser()) ;
            packet.setState(MeetingPacket.State.ACCEPTED) ;
        }

        try {
            this.networkInterface.getEnvoyer().send(
                socket, packet, false
            ) ;
        } catch (Exception e) {
            e.printStackTrace() ;
        }
    }

    /**
     * Manage an accepted packet.
     *
     * @param packet : accepted packet instance.
     */
    private void manageAcceptedPacket(MeetingPacket packet) {
        UserController controller = this.getUserController() ;

        this.requestedPackets.remove(packet) ;
        controller.addUser(packet.getSourceUser()) ;

        // TODO : notify observer (accepted)
    }

    /**
     * Manage an denied packet.
     *
     * @param packet : denied packet instance.
     */
    private void manageDeniedPacket(MeetingPacket packet) {
        this.requestedPackets.remove(packet) ;

        // TODO : notify observer (denied)
    }

}
