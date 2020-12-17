package Messenger.Foundation.Observers.Listeners;

import Messenger.GUI.GraphicInterface;
import Messenger.Foundation.Models.User;
import java.util.concurrent.ConcurrentHashMap;
import Messenger.Foundation.Observers.BaseListener;
import Messenger.GUI.Frames.Screens.Contacts.ContactBar;
import static Messenger.Foundation.Controllers.UserController.UpdateState ;

/**
 * @author Damien MOLINA
 */
public class UserListUpdated extends BaseListener {

    /**
     * List of the users that have not
     * been updated graphically.
     */
    private static final ConcurrentHashMap<User, UpdateState> users = new ConcurrentHashMap<>() ;

    /**
     * Notify the listener with the given arguments.
     *
     * @param args : notification arguments.
     */
    @Override
    public void handle(Object... args) {
        UserListUpdated.users.put(
            (User) args[0], (UpdateState) args[1]
        ) ;

        UserListUpdated.updateUI() ;
    }

    /**
     * Manage the waiting users.
     */
    public static synchronized void updateUI() {
        ContactBar bar = GraphicInterface.instance().contactBar() ;

        /*
         * This is mandatory because the contact
         * bar is not generated when the user is
         * logging in. So, we need to wait until
         * the bar is made.
         */
        if(bar != null) {
            UserListUpdated.users.forEach((User user, UpdateState state) -> {
                switch(state) {
                    case ADDED: bar.addItem(user) ; break ;
                    case REMOVED: bar.removeItem(user) ; break ;
                }

                UserListUpdated.users.remove(user) ;
            }) ;
        }
    }

}
