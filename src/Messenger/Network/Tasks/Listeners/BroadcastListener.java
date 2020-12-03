package Messenger.Network.Tasks.Listeners;

import Messenger.Foundation.Models.User;
import Messenger.Foundation.System.Console.Console;
import Messenger.Network.Models.Broadcast.BroadcastNotification;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import Messenger.Foundation.Environment;
import Messenger.Foundation.Controllers.UserController;
import Messenger.Network.Models.MeetingPacket;

/**
 * @author Maud PENNETIER
 */
public class BroadcastListener
{

    private int portListen = 60403;


    /**
     * Run the listener.
     */
    @SuppressWarnings("InfiniteLoopStatement")
    public void run() throws Exception {
        while(true) {
            try {
                DatagramSocket dataListener = new DatagramSocket(portListen); //listen on a port
                Console.comment("=> BroadcastListener is waiting") ;

                byte[] buffer = new byte[4096];
                DatagramPacket datagram = new DatagramPacket(buffer, buffer.length);

                dataListener.receive(datagram);   //receiving datagram

                if(Environment.getApplication().isDebugMode()) {
                    Console.comment("=> MeetingListener received a datagram from " + datagram.getAddress()) ;
                }

                BroadcastNotification receivedNotif = new BroadcastNotification(datagram.getData().toString()); //comment faire ?

                this.manageReceivedPDU(receivedNotif);

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

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
        ((UserController)Environment.getController(UserController.class)).supprUser(notif.getUser());
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
        ((UserController)Environment.getController(UserController.class)).modifyUserName(notif.getUser() ,notif.getUser().getPseudo());
    }

}
