package Messenger.Network.Tasks.Listeners.Meetings.Handlers;

import Messenger.Network.Models.Datagram.OutputSocketStream;
import Messenger.Foundation.Models.Messages.MessageData;
import Messenger.Foundation.Controllers.UserController;
import Messenger.Network.Models.MessagePacket;
import Messenger.Network.NetworkInterface;
import Messenger.Foundation.Models.User;
import Messenger.Foundation.Environment;
import java.util.Scanner;
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
        this.getUserController().addUser(user) ;
        // TODO : to do



        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String text = scanner.next() ;




            MessagePacket packet = new MessagePacket(new User(), user) ;
            packet.setData(
                new MessageData(text, null)
            ) ;

            try {
                Socket socket = new Socket(
                    user.getAddress(), NetworkInterface.receivingPort
                ) ;

                OutputSocketStream exchanger = new OutputSocketStream(socket) ;
                exchanger.send(packet) ;

                exchanger.close() ;
                socket.close() ;
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    /**
     * Get the User Controller.
     *
     * @return the UserController instance.
     */
    private UserController getUserController() {
        return (UserController) Environment.getController(UserController.class) ;
    }

}
