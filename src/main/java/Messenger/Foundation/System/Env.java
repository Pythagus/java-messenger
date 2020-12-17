package Messenger.Foundation.System;

import Messenger.Foundation.Application;
import Messenger.Foundation.Models.User;

/**
 * @author Damien MOLINA
 */
public final class Env {

    // Forbid object instantiation.
    private Env() {}

    /**
     * Application instance.
     */
    private static Application application ;

    /**
     * Currently logged in user.
     */
    private static User user ;

    /**
     * Get the current logged in user instance.
     *
     * @return the user instance if there is a
     *      logged in user, NULL otherwise.
     */
    public static User getUser() {
        return Env.user ;
    }

    /**
     * Set the user instance.
     *
     * @param user : the user instance.
     */
    public static void setUser(User user) {
        Env.user = user;
    }

    /**
     * Get the current application instance.
     *
     * @return the application instance.
     */
    public static Application getApplication() {
        return Env.application ;
    }

    /**
     * Set the current application instance.
     *
     * @param app : the application instance.
     */
    public static void setApplication(Application app) {
        Env.application = app ;
    }

}
