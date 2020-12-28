package fr.insa.messenger.tomcat.controllers;

import java.util.ArrayList;
import fr.insa.messenger.tomcat.models.User;

/**
 * @author Damien MOLINA
 */
public class UserController {

    /**
     * Singleton instance.
     */
    private static final UserController INSTANCE = new UserController() ;

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
     * Get the UserController singleton instance.
     *
     * @return the singleton instance.
     */
    public static UserController instance() {
        return UserController.INSTANCE ;
    }

    /**
     * Determine whether the given user
     * is already in the array.
     *
     * @param identifier : user identifier.
     * @return True if it is, False otherwise.
     */
    public boolean hasUser(String identifier) {
        // TODO : do it with a DB request.

        for(User user : this.users) {
            if(user.getIdentifier().equals(identifier)) {
                return true ;
            }
        }

        return false ;
    }

}
