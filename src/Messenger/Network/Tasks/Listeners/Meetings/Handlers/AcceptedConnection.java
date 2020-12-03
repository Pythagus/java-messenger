package Messenger.Network.Tasks.Listeners.Meetings.Handlers;

import Messenger.Foundation.Models.Messages.MessageData;
import Messenger.Network.Models.Datagram.Stream;
import Messenger.Network.Models.MessagePacket;
import Messenger.Network.NetworkInterface;
import Messenger.Foundation.Models.User;
import java.net.Socket;

/**
 * @author Damien MOLINA
 */
public class AcceptedConnection {

    /**
     * Run the listener.
     */
    public void accepted(User user) {
        System.out.println("Accepted user : " + user.getPseudo()) ;

        MessagePacket packet = new MessagePacket(new User(), user) ;
        packet.setData(
            new MessageData("Coucou toi", null)
        ) ;

        try {
            Socket socket = new Socket(
                user.getAddress(), NetworkInterface.receivingPort
            ) ;

            Stream exchanger = new Stream() ;
            exchanger.bindOutput(socket.getOutputStream()) ;
            exchanger.send(packet) ;

            exchanger.close() ;
            socket.close() ;
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        // TODO : to do
    }

}
