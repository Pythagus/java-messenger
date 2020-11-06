package Messenger.utils;

import Messenger.application.ApplicationContract;

/**
 * @author Damien MOLINA
 */
public final class Current {

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
        return Current.application ;
    }

    /**
     * Set the current application instance.
     *
     * @param app : the application instance.
     */
    public static void setApplication(ApplicationContract app) {
        Current.application = app ;
    }

    // Forbid object instantiation.
    private Current() {}

}
