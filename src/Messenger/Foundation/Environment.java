package Messenger.Foundation;

import Messenger.Foundation.Contracts.ApplicationContract;

/**
 * @author Damien MOLINA
 */
public class Environment {

    /**
     * Application instance.
     */
    private static ApplicationContract application ;

    /**
     * Get the current application instance.
     *
     * @return the application instance.
     */
    public static ApplicationContract getApplication() {
        return Environment.application ;
    }

    /**
     * Set the current application instance.
     *
     * @param app : the application instance.
     */
    public static void setApplication(ApplicationContract app) {
        Environment.application = app ;
    }

    // Forbid object instantiation.
    private Environment() {}

}
