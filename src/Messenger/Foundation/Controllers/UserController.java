package Messenger.Foundation.Controllers;

import java.util.ArrayList;
import Messenger.Foundation.Models.User;

/**
 * @author Damien MOLINA
 */
public class UserController extends Controller {

    /**
     * Users list.
     */
    private final ArrayList<User> users ;

    /**
     * Make a new instance of the User controller.
     */
    public UserController() {
        this.users = new ArrayList<>() ;
    }

    /**
     * Add a user.
     *
     * @param user : User instance.
     */
    public void addUser(User user) {
        this.users.add(user) ;
    }

    /**
     * Determine whether the given user
     * is already in the array.
     *
     * @param user : User instance.
     * @return True if it is, False otherwise.
     */
    public boolean hasUser(User user) {
        return this.users.contains(user) ;
    }

}
