package fr.insa.messenger.observers;

import fr.insa.messenger.models.User;
import fr.insa.messenger.ui.GraphicInterface;
import java.util.concurrent.ConcurrentHashMap;
import fr.insa.messenger.ui.screens.contacts.ContactBar;
import fr.insa.messenger.observers.contracts.BaseListener;
import static fr.insa.messenger.controllers.UserController.UpdateState;

/**
 * @author Damien MOLINA
 */
public class UserListListener extends BaseListener {

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
        UserListListener.users.put(
            (User) args[0], (UpdateState) args[1]
        ) ;

        UserListListener.updateUI() ;
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
            UserListListener.users.forEach((User user, UpdateState state) -> {
                switch(state) {
                    case ADDED: bar.addItem(user) ; break ;
                    case REMOVED: bar.removeItem(user) ; break ;
                }

                UserListListener.users.remove(user) ;
            }) ;
        }
    }

}
