package fr.insa.messenger.client.controllers;

import java.util.regex.*;
import java.util.ArrayList;
import fr.insa.messenger.client.system.Env;
import fr.insa.messenger.client.models.User;
import fr.insa.messenger.tools.models.UserStatus;
import fr.insa.messenger.client.exceptions.AppException;
import fr.insa.messenger.client.exceptions.PseudoException;
import fr.insa.messenger.client.observers.UserListListener;

/**
 * @author Damien MOLINA
 */
final public class UserController extends Controller {

    /**
     * Singleton instance.
     */
    private static final UserController INSTANCE = new UserController() ;

    /**
     * Possible update state.
     */
    public enum UpdateState {
        ADDED, REMOVED, UPDATED, STATUS
    }

    /**
     * Users list.
     */
    private final ArrayList<User> users ;

    /**
     * Make a new instance of the User controller.
     */
    public UserController() {
        this.users = new ArrayList<>() ;

        this.addListener(new UserListListener()) ;
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
     * Add a user.
     *
     * @param user : User instance.
     */
    public void addUser(User user) {
        if(this.hasUser(user)) {
            try {
                User saved = this.getFromIdentifier(user.getIdentifier()) ;

                if(saved.getPseudo() == null) {
                    saved.setPseudo(user.getPseudo()) ;
                }
            } catch (AppException e) {
                e.printStackTrace();
            }
        } else {
            this.users.add(user) ;
            this.notifyAllListeners(user, UpdateState.ADDED) ;
        }
    }

    /**
     * Remove a user.
     *
     * @param user : User instance.
     */
    public void removeUser(User user) {
        this.users.remove(user) ;
        this.notifyAllListeners(user, UpdateState.REMOVED) ;
    }

    /**
     * modify an existing pseudo.
     *
     * @param user User instance.
     * @param pseudo String that will replace the ancient pseudo.
     */
    public void modifyUserName(User user, String pseudo) {
        this.users.get(
            this.users.indexOf(user)
        ).setPseudo(pseudo) ;

        this.notifyAllListeners(user, UpdateState.UPDATED, pseudo) ;
    }

    /**
     * Update the user's status.
     *
     * @param user User instance.
     * @param status new user's status.
     */
    public void modifyUserStatus(User user, UserStatus status) {
        int index = this.users.indexOf(user) ;

        if(index >= 0) {
            this.users.get(index).setStatus(status) ;

            this.notifyAllListeners(user, UpdateState.STATUS, status) ;
        }
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
     * Check the validity of the given
     * pseudo.
     *
     * @param pseudo : pseudo to verify.
     * @throws PseudoException : invalid pseudo exception.
     */
    public void checkPseudo(String pseudo) throws PseudoException {
        if(pseudo.length() < 3) {
            throw new PseudoException("Le pseudo doit faire au moins 3 caractères") ;
        }

        Pattern pattern = Pattern.compile("[^A-Za-z0-9éèàïö ]") ;
        Matcher matcher = pattern.matcher(pseudo) ;

        if(matcher.find()) {
            throw new PseudoException("Le pseudo ne doit contenir que des chiffres et des lettres") ;
        }

        for(User user : this.users) {
            if(user.getPseudo().equals(pseudo)) {
                throw new PseudoException("Le pseudo est déjà utilisé") ;
            }
        }
    }

    /**
     * Get the users list.
     *
     * @return the users list.
     */
    public ArrayList<User> getUsers() {
        return this.users ;
    }

}
