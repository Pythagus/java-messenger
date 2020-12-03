package Messenger.Network.Tasks.Listeners.Meetings.Handlers;

import Messenger.Foundation.Models.Messages.MessageData;
import Messenger.Foundation.Controllers.UserController;
import Messenger.Network.Models.Datagram.Stream;
import Messenger.Network.Models.MessagePacket;
import Messenger.Network.NetworkInterface;
import Messenger.Foundation.Environment;
import Messenger.Foundation.Models.User;
import java.net.Socket;
import java.util.Scanner;

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

                Stream exchanger = new Stream() ;
                exchanger.bindOutput(socket.getOutputStream()) ;
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
