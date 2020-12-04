package Messenger.Network.Tasks.Listeners.Meetings.Handlers;

import Messenger.Foundation.Env;
import Messenger.Foundation.Models.User;
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
