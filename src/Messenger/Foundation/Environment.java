package Messenger.Foundation;

import Messenger.Foundation.Models.User;

/**
 * @author Damien MOLINA
 */
public class Environment {

    /**
     * Application instance.
     */
    private static Application application ;

    /**
     * Currently logged in user.
     */
    private static User user ;

    /**
     * Get the current application instance.
     *
     * @return the application instance.
     */
    public static Application getApplication() {
        return Environment.application ;
    }

    /**
     * Get the current logged in user instance.
     *
     * @return the user instance if there is a
     *      logged in user, NULL otherwise.
     */
    public static User getUser() {
        return Environment.user ;
    }

    /**
     * Set the current application instance.
     *
     * @param app : the application instance.
     */
    public static void setApplication(Application app) {
        Environment.application = app ;
    }

    /**
     * Set the user instance.
     *
     * @param user : the user instance.
     */
    public static void setUser(User user) {
        Environment.user = user ;
    }

    // Forbid object instantiation.
    private Environment() {}

}
