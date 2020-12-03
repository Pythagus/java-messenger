package Messenger.Network.Tasks.Listeners.Meetings;

import java.net.Socket;
import Messenger.Foundation.Environment;
import Messenger.Network.Models.MeetingPacket;
import Messenger.Network.Models.Datagram.Stream;
import Messenger.Foundation.System.Console.Console;
import Messenger.Foundation.Controllers.UserController;
import Messenger.Network.Tasks.Listeners.Concerns.NetworkBaseListener;
import Messenger.Network.Tasks.Listeners.Meetings.Handlers.DeniedConnection;
import Messenger.Network.Tasks.Listeners.Meetings.Handlers.AcceptedConnection;

/**
 * @author Damien MOLINA
 */
public class MeetingResponseListener extends NetworkBaseListener<Socket> {

    /**
     * Runnable called when the packet is
     * accepted.
     */
    private AcceptedConnection callbackOnAccepted ;

    /**
     * Runnable called when the packet is
     * accepted.
     */
    private DeniedConnection callbackOnDenied ;

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
     * packet has been accepted or denied.
     *
     * @param accepted : accepted runnable.
     * @param denied : denied runnable.
     */
    public void setCallbacks(AcceptedConnection accepted, DeniedConnection denied) {
        this.callbackOnAccepted = accepted ;
        this.callbackOnDenied   = denied ;
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
            Stream exchanger = new Stream() ;
            exchanger.bindInput(this.listenerSocket.getInputStream()) ;
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
        if(Environment.getApplication().isDebugMode()) {
            Console.comment("=> Accepted packet from " + packet.getSourceAddress()) ;
        }

        if(this.callbackOnAccepted != null) {
            this.callbackOnAccepted.accepted(packet.getSourceUser()) ;
        }
    }

    /**
     * Manage an denied packet.
     *
     * @param packet : denied packet instance.
     */
    private void manageDeniedPacket(MeetingPacket packet) {
        if(Environment.getApplication().isDebugMode()) {
            Console.comment("=> Denied packet from " + packet.getSourceAddress()) ;
        }

        if(this.callbackOnDenied != null) {
            this.callbackOnDenied.denied() ;
        }
    }

}
