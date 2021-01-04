package fr.insa.messenger.client.network.listeners;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

import fr.insa.messenger.client.network.listeners.handlers.LogoutHandler;
import fr.insa.messenger.client.network.models.basis.BroadcastType;
import fr.insa.messenger.client.system.console.Console;
import fr.insa.messenger.client.network.NetworkInterface;
import fr.insa.messenger.client.controllers.UserController;
import fr.insa.messenger.client.network.models.BroadcastPacket;

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

                Console.comment("=> BroadcastListener is waiting");
                DatagramPacket datagram = new DatagramPacket(buffer, buffer.length);
                this.listenerSocket.receive(datagram) ;
                Console.comment("=> BroadcastListener received a datagram from " + datagram.getAddress()) ;

                BroadcastPacket notification = BroadcastPacket.unserialize(
                    new String(datagram.getData())
                ) ;

                if(! notification.getUser().isEnvUser()) {
                    this.manageReceivedPDU(notification);
                } else {
                    Console.warning("Broadcast packet not managed") ;
                }
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
    protected void manageReceivedPDU(BroadcastPacket pdu) {
        switch (pdu.getType()) {
            /*
             * This packet announces a new user of the network.
             */
            case LOGIN: this.manageLoginPDU (pdu) ; break;

            /*
             * This packet announces that a user left the network.
             */
            case LOGOUT: this.manageLogoutPDU(pdu) ; break ;

            /*
             * This packet is from a user that has to choose a pseudo and
             * ask for everyone else information. The users will respond
             * a LOGIN notification.
             */
            case EVERYONE_INFO: this.manageEveryoneInformationPDU(pdu) ; break ;

            /*
             * This packet is from a user that change its pseudo while still
             * using the app.
             */
            case CHANGED_PSEUDO: this.manageChangedPseudoPDU(pdu) ; break ;

            // Unknown type.
            default:
                Console.warning("Unknown received broadcast type : " + pdu.getType()) ;
        }
    }

    /**
     * Add an entry to the user DTB.
     *
     * @param notification : Need the content of the notification
     */
    private void manageLoginPDU(BroadcastPacket notification) {
        this.printReceivedNotification(notification) ;

        // Add the sending user to the users list.
        UserController.instance().addUser(notification.getUser()) ;
    }

    /**
     * Erase the sender from the userConnected DTB
     *
     * @param notification : Need the content of the notification
     */
    private void manageLogoutPDU(BroadcastPacket notification) {
        this.printReceivedNotification(notification) ;

        // Remove the sending user from the users list.
        LogoutHandler.handleNetwork(notification.getUser()) ;
    }

    /**
     * Send its information (ip, mac, pseudo, port ?)
     *
     * @param notification : Need the content of the notification
     */
    private void manageEveryoneInformationPDU(BroadcastPacket notification) {
        this.printReceivedNotification(notification) ;

        NetworkInterface.instance().getEnvoyer().broadcastResponse(
            new BroadcastPacket(BroadcastType.LOGIN), notification.getUser().getAddress()
        ) ;
    }

    /**
     * Change the sender pseudo in the userConnected DTB
     *
     * @param notification Need the content of the notification
     */
    private void manageChangedPseudoPDU(BroadcastPacket notification) {
        this.printReceivedNotification(notification) ;

        // Update the user pseudo
        UserController.instance().modifyUserName(
            notification.getUser(), notification.getUser().getPseudo()
        ) ;
    }

    /**
     * Print into the console the received
     * notification.
     *
     * @param notification : received notification.
     */
    private void printReceivedNotification(BroadcastPacket notification) {
        Console.comment("=> Broadcast " + notification.getType() + " received from " + notification.getUser().getPseudo()) ;
    }

}
