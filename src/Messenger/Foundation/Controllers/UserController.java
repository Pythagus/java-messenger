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
    public UserController() { this.users = new ArrayList<>() ; }

    /**
     * Add a user.
     *
     * @param user : User instance.
     */
    public void addUser(User user) {
        this.users.add(user) ;
    }
    //comment l'user est trouvé ? est ce que ça marche encore si un parametre de l'user change ?

    /**
     * Remove a user.
     * @param user : User instance.
     */
    public void supprUser(User user) {this.users.remove(user) ; }

    /**
     * modify an existing pseudo
     * @param user User instance
     * @param newName String that will replace the ancient pseudo
     */
    public void modifyUserName(User user, String newName){
        int index = this.users.indexOf(user);
        this.users.get(index).setPseudo(newName);
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
