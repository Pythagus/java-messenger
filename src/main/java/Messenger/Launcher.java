package Messenger;

import java.net.InetAddress;
import Messenger.Foundation.Models.User;

/**
 * Application launcher.
 *
 * @author Damien MOLINA
 */
public class Launcher {

    /**
     * Start the application.
     *
     * @param args : command line arguments.
     */
    public static void main(String[] args) {
        Messenger msn = new Messenger() ;
        msn.start() ;
    }

    // TODO : to delete when the broadcast is complete.
    public static User targetedUser() {
        InetAddress ip = null ;

        try {
            ip = InetAddress.getByName("192.168.1.1") ;
        } catch (Exception ignored) {}

        return new User("Tata Antoinette", "00:00:00:00:00:41", ip) ;
    }

}
