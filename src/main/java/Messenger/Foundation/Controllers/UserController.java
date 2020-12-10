package Messenger.Foundation.Controllers;

import java.util.ArrayList;
import Messenger.Foundation.Models.User;
import Messenger.Foundation.Exceptions.AppException;
import Messenger.Foundation.System.Env;
import java.util.regex.*;

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
    public UserController() { this.users = new ArrayList<>() ; }

    /**
     * Add a user.
     *
     * @param user : User instance.
     */
    public void addUser(User user) {
        if(! this.hasUser(user)) {
            this.users.add(user) ;
            this.notifyAllListeners(user) ;
        }
    }

    /**
     * Remove a user.
     *
     * @param user : User instance.
     */
    public void removeUser(User user) {
        this.users.remove(user) ;
        this.notifyAllListeners(user) ;
    }

    /**
     * modify an existing pseudo
     * @param user User instance
     * @param newName String that will replace the ancient pseudo
     */
    public void modifyUserName(User user, String newName) {
        this.users.get(
            this.users.indexOf(user)
        ).setPseudo(newName) ;
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

    /**
     * Get the user from his identifier.
     *
     * @param identifier : user identifier.
     * @return the user instance.
     * @throws AppException : user not found.
     */
    public User getFromIdentifier(String identifier) throws AppException {
        if(Env.getUser().getIdentifier().equals(identifier)) {
            return Env.getUser() ;
        }

        for(User user : this.users) {
            if(user.getIdentifier().equals(identifier)) {
                return user ;
            }
        }

        throw new AppException("Not found user with identifier " + identifier) ;
    }

    /**
     * Determine whether the given pseudo is available.
     * Or if the pseudo is valid (no forbidden characters)
     *
     * @param pseudo : pseudo to test.
     * @return 1 if the pseudo is available,
     *         2 if the pseudo contains forbidden characters : #
     *         3 if the pseudo is already used.
     */
    public int availablePseudo(String pseudo) {
        // todo : minimal size of pseudo
        Pattern pattern = Pattern.compile("[^A-Za-z0-9éèàïö ]");
        Matcher matcher = pattern.matcher(pseudo);
        if (matcher.find()) {
            return 2;
        } else
        {
            for (User user : this.users)
            {
                if (user.getPseudo().equals(pseudo))
                {
                    return 3;
                }
            }

            return 1;
        }
    }

}
