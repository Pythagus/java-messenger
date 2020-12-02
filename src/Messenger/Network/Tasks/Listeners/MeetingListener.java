package Messenger.Network.Tasks.Listeners;

import java.net.Socket;
import java.io.IOException;
import java.net.ServerSocket;
import java.io.ObjectInputStream;
import Messenger.Foundation.Environment;
import Messenger.Foundation.System.Console.Console;
import Messenger.Network.NetworkInterface;
import Messenger.Network.Models.MeetingPacket;
import Messenger.Foundation.Controllers.UserController;
import Messenger.Network.Tasks.Listeners.Concerns.NetworkBaseListener;

/**
 * @author Damien MOLINA
 */
public class MeetingListener extends NetworkBaseListener<ServerSocket> {

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

        this.networkInterface = networkInterface ;
    }

    /**
     * Run the listener.
     */
    @SuppressWarnings("InfiniteLoopStatement")
    public void run() {
        while(true) {
            try {
                if(Environment.getApplication().isDebugMode()) {
                    Console.comment("=> MeetingListener is waiting") ;
                }

                Socket socket        = this.listenerSocket.accept() ;
                ObjectInputStream is = new ObjectInputStream(socket.getInputStream()) ;

                if(Environment.getApplication().isDebugMode()) {
                    Console.comment("=> MeetingListener received a request from " + socket.getInetAddress()) ;
                }

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
        if(packet.hasState(MeetingPacket.State.REQUEST)) {
            this.manageRequestedPacket(socket, packet) ;
        }
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
            controller.addUser(packet.getSourceUser()) ;
            packet.setState(MeetingPacket.State.ACCEPTED) ;
        }

        if(Environment.getApplication().isDebugMode()) {
            Console.comment("=> New state : " + packet.getState()) ;
        }

        packet.reverse() ;

        try {
            this.networkInterface.getEnvoyer().send(socket, packet, false) ;
        } catch (Exception e) {
            e.printStackTrace() ;
        }
    }

}
