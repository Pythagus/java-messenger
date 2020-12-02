package Messenger;

import Messenger.Foundation.Environment;
import Messenger.Foundation.Models.User;
import java.net.InetAddress;

// TODO : TO DELETE
public class LauncherTest {

    public static void main(String[] args) throws Exception {
        Messenger msn = new Messenger() ;
        msn.start() ;


        InetAddress ip = InetAddress.getByName("192.168.1.1") ;

        Environment.getNetworkInterface().getEnvoyer().sendRequestMeeting(
            new User(ip), () -> System.out.println("Accepted"), () -> System.out.println("Denied")
        ) ;
    }

}
