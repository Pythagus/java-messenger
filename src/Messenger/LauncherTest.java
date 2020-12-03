package Messenger;

import java.net.InetAddress;
import Messenger.Foundation.Env;
import Messenger.Foundation.Models.User;

// TODO : TO DELETE
public class LauncherTest {

    public static void main(String[] args) {
        Messenger msn = new Messenger() ;
        msn.start() ;

        Env.getNetworkInterface().getEnvoyer().sendRequestMeeting(
            LauncherTest.getUser()
        ) ;
    }

    public static User getUser() {
        InetAddress ip = null ;

        try {
            ip = InetAddress.getByName("192.168.1.1") ;
        } catch (Exception ignored) {}

        return new User("Tata Antoinette", "00:00:00:00:00:42", ip) ;
    }

}
