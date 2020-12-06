package Messenger.Network.Tasks.Listeners;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import Messenger.Foundation.System.Env;
import Messenger.Foundation.System.Console.Console;
import Messenger.Foundation.Controllers.UserController;
import Messenger.Network.Models.Broadcast.BroadcastType;
import Messenger.Network.Models.Broadcast.BroadcastNotification;
import Messenger.Network.Models.Broadcast.BroadcastResponsePacket;
import Messenger.Network.Tasks.Listeners.Concerns.NetworkBaseListener;

/**
 * @author Maud PENNETIER
 */
public class BroadcastListener extends NetworkBaseListener<DatagramSocket> {

    /**
     * Make a new listener instance.
     *
     * @param listenerSocket : listening socket.
     */
    public BroadcastListener(DatagramSocket listenerSocket) {
        super(listenerSocket) ;
    }

    /**
     * Run the listener.
     */
    @SuppressWarnings("InfiniteLoopStatement")
    public void run() {
        while(true) {
            try {
                byte[] buffer = new byte[4096];

                if(Env.getApplication().isDebugMode()) {
                    Console.comment("=> BroadcastListener is waiting");
                }

                DatagramPacket datagram = new DatagramPacket(buffer, buffer.length);

                this.listenerSocket.receive(datagram) ;

                if(Env.getApplication().isDebugMode()) {
                    Console.comment("=> BroadcastListener received a datagram from " + datagram.getAddress()) ;
                }

                BroadcastNotification notification = BroadcastNotification.unserialize(
                    new String(datagram.getData())
                ) ;

                this.manageReceivedPDU(notification);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * redirect on functions depending of the type of packet
     *
     * @param pdu payload of the udp packet
     */
    protected void manageReceivedPDU(BroadcastNotification pdu) {
        switch (pdu.getType()) {
            /*
             * This packet announces a new user of the network.
             */
            case LOGIN: this.manageLoginPDU (pdu) ; break;

            /*
             * This packet announces that a user left the network.
             */
            case LOGOUT: this.manageLogoutPDU(pdu); break ;

            /*
             * This packet is from a user that has to choose a pseudo and
             * ask for everyone else information. The users will respond
             * a LOGIN notification.
             */
            case EVERYONE_INFO: this.manageEveryoneInformationPDU(pdu); break ;

            /*
             * This packet is from a user that change its pseudo while still using the app.
             */
            case CHANGED_PSEUDO: this.manageChangedPseudoPDU(pdu); break ;
        }
    }

    /**
     * Add an entry to the user DTB.
     *
     * @param notification : Need the content of the notification
     */
    private void manageLoginPDU(BroadcastNotification notification) {
        this.printReceivedNotification(notification) ;

        // Add the sending user to the users list.
        this.getUserController().addUser(notification.getUser()) ;
    }

    /**
     * Erase the sender from the userConnected DTB
     *
     * @param notification : Need the content of the notification
     */
    private void manageLogoutPDU(BroadcastNotification notification) {
        this.printReceivedNotification(notification) ;

        // Remove the sending user from the users list.
        this.getUserController().removeUser(notification.getUser()) ;
    }

    /**
     * Send its information (ip, mac, pseudo, port ?)
     *
     * @param notification : Need the content of the notification
     */
    private void manageEveryoneInformationPDU(BroadcastNotification notification) {
        this.printReceivedNotification(notification) ;

        Env.getNetworkInterface().getEnvoyer().send(
            new BroadcastResponsePacket(BroadcastType.LOGIN, notification.getUser())
        ) ;
    }

    /**
     * Change the sender pseudo in the userConnected DTB
     *
     * @param notification Need the content of the notification
     */
    private void manageChangedPseudoPDU(BroadcastNotification notification) {
        this.printReceivedNotification(notification) ;

        // Update the user pseudo
        this.getUserController().modifyUserName(
            notification.getUser(), notification.getUser().getPseudo()
        ) ;
    }

    /**
     * Get the User Controller.
     *
     * @return the UserController instance.
     */
    private UserController getUserController() {
        return (UserController) Env.getController(UserController.class) ;
    }

    /**
     * Print into the console the received
     * notification.
     *
     * @param notification : received notification.
     */
    private void printReceivedNotification(BroadcastNotification notification) {
        if(Env.getApplication().isDebugMode()) {
            Console.comment("=> Broadcast " + notification.getType() + " received from " + notification.getUser().getPseudo()) ;
        }
    }

}
