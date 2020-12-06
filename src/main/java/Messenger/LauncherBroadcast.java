package Messenger;

import Messenger.Foundation.System.Env;
import Messenger.Network.Models.Broadcast.BroadcastType;
import Messenger.Network.Models.Broadcast.BroadcastNotification;

/**
 * Application launcher.
 *
 * @author Damien MOLINA
 */
public class LauncherBroadcast {

    /**
     * Start the application.
     *
     * @param args : command line arguments.
     */
    public static void main(String[] args) {
        Messenger msn = new Messenger() ;
        msn.start() ;

        Env.getUser().setPseudo("André") ;

        System.out.println(Env.getUser().getAddress());

        Env.getNetworkInterface().getEnvoyer().broadcast(
            new BroadcastNotification(BroadcastType.EVERYONE_INFO)
        ) ;
    }

}
