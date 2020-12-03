package Messenger.Network.Tasks.Listeners.Meetings.Handlers;

import java.util.Scanner;
import Messenger.Foundation.Env;
import Messenger.Foundation.Models.User;
import Messenger.Foundation.Models.Messages.MessageData;
import Messenger.Foundation.Controllers.UserController;

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


        // TODO : to delete
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String text = scanner.next() ;

            Env.getNetworkInterface().getEnvoyer().sendMessage(
                new MessageData(text, null), user
            ) ;
        }
    }

    /**
     * Get the User Controller.
     *
     * @return the UserController instance.
     */
    private UserController getUserController() {
        return (UserController) Env.getController(UserController.class) ;
    }

}
