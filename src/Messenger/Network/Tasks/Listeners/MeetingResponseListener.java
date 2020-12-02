package Messenger.Network.Tasks.Listeners;

import Messenger.Network.Tasks.Listeners.Concerns.NetworkBaseListener;
import Messenger.Network.Models.Datagram.DatagramExchanger;
import Messenger.Foundation.Controllers.UserController;
import Messenger.Network.Models.Datagram.Stream;
import Messenger.Network.Models.MeetingPacket;
import Messenger.Foundation.Environment;
import java.net.Socket;

/**
 * @author Damien MOLINA
 */
public class MeetingResponseListener extends NetworkBaseListener<Socket> {

    /**
     * Runnable called when the packet is
     * accepted.
     */
    private Runnable callbackOnAccepted ;

    /**
     * Runnable called when the packet is
     * accepted.
     */
    private Runnable callbackOnDenied ;


    /**
     * Make a new listener instance.
     *
     * @param socket : listening socket.
     */
    public MeetingResponseListener(Socket socket) {
        super(socket) ;
    }

    /**
     * Set the runnable executed when the
     * packet has been accepted.
     *
     * @param runnable : runnable to execute.
     */
    public void setCallbackOnAccepted(Runnable runnable) {
        this.callbackOnAccepted = runnable ;
    }

    /**
     * Set the runnable executed when the
     * packet has been denied.
     *
     * @param runnable : runnable to execute.
     */
    public void setCallbackOnDenied(Runnable runnable) {
        this.callbackOnDenied = runnable ;
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
     * Run the listener.
     */
    public void run() {
        try {
            DatagramExchanger exchanger = new Stream(this.listenerSocket) ;
            MeetingPacket packet = (MeetingPacket) exchanger.receive() ;

            this.managePacket(packet) ;

            this.listenerSocket.close() ;
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Manage the received packet instance.
     *
     * @param packet : received packet.
     */
    private void managePacket(MeetingPacket packet) {
        switch (packet.getState()) {

            /*
             * The request was accepted. We can now
             * contact the other user in the correct
             * port.
             */
            case ACCEPTED: this.manageAcceptedPacket(packet) ; break ;

            /*
             * The request was refused. We need to remove
             * the packet from the temporary list.
             */
            case DENIED: this.manageDeniedPacket(packet) ; break ;
        }
    }

    /**
     * Manage an accepted packet.
     *
     * @param packet : accepted packet instance.
     */
    private void manageAcceptedPacket(MeetingPacket packet) {
        this.getUserController().addUser(packet.getSourceUser()) ;

        if(this.callbackOnAccepted != null) {
            this.callbackOnAccepted.run() ;
        }
    }

    /**
     * Manage an denied packet.
     *
     * @param packet : denied packet instance.
     */
    private void manageDeniedPacket(MeetingPacket packet) {
        if(this.callbackOnDenied != null) {
            this.callbackOnDenied.run() ;
        }
    }

}
