package Messenger.Network.Tasks.Listeners;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import Messenger.Foundation.Env;
import Messenger.Foundation.System.Console.Console;
import Messenger.Foundation.Controllers.UserController;
import Messenger.Network.Models.Broadcast.BroadcastNotification;
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

                this.listenerSocket.receive(datagram);   //receiving datagram

                if(Env.getApplication().isDebugMode()) {
                    Console.comment("=> MeetingListener received a datagram from " + datagram.getAddress()) ;
                }

                BroadcastNotification receivedNotif = new BroadcastNotification(datagram.getData().toString()); //comment faire ?

                this.manageReceivedPDU(receivedNotif);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * redirect on functions depending of the type of packet
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
             * This packet is from a user that has to choose a pseudo and ask for everyone else information.
             */
            case HASPSEUDO: this.manageHasPseudoPDU(pdu); break ;

            /*
             * This packet is from a user that change its pseudo while still using the app.
             */
            case CHANGEDPSEUDO: this.manageChangedPseudoPDU(pdu); break ;
        }
    }

    /**
     * add an entry to the user DTB
     * @param notif Need the content of the notification
     */
    private void manageLoginPDU(BroadcastNotification notif)
    {
        Console.comment("=> Broadcast LOGIN PDU received") ;
       // ((UserController)Environment.getController(UserController.class)).addUser(notif.getUser());
    }

    /**
     * Erase the sender from the userConnected DTB
     * @param notif Need the content of the notification
     */
    private void manageLogoutPDU(BroadcastNotification notif)
    {
        ((UserController) Env.getController(UserController.class)).supprUser(notif.getUser());
    }

    /**
     * Send its information (ip, mac, pseudo, port ?)
     * @param notif Need the content of the notification
     */
    private void manageHasPseudoPDU(BroadcastNotification notif)
    {
        //todo
    }

    // y'a un truc qui va pas ici !!! comment trouver grace à seulement l'attribut MAC ?
    /**
     * Change the sender pseudo in the userConnected DTB
     * @param notif Need the content of the notification
     */
    private void manageChangedPseudoPDU(BroadcastNotification notif)
    {
        ((UserController) Env.getController(UserController.class)).modifyUserName(notif.getUser() ,notif.getUser().getPseudo());
    }

}