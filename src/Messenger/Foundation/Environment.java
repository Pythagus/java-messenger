package Messenger.Foundation;

/**
 * @author Damien MOLINA
 */
public class Environment {

    /**
     * Application instance.
     */
    private static Application application ;

    /**
     * Get the current application instance.
     *
     * @return the application instance.
     */
    public static Application getApplication() {
        return Environment.application ;
    }

    /**
     * Set the current application instance.
     *
     * @param app : the application instance.
     */
    public static void setApplication(Application app) {
        Environment.application = app ;
    }

    // Forbid object instantiation.
    private Environment() {}

}
