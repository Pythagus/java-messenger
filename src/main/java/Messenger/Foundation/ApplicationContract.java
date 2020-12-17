package Messenger.Foundation;

/**
 * @author Damien MOLINA
 */
public interface ApplicationContract {

    /**
     * Get the current version of the
     * application.
     *
     * @return the application version.
     */
    String getVersion() ;

    /**
     * Get the current application's name.
     *
     * @return the application name.
     */
    String getName() ;

    /**
     * Get the current application's mode.
     *
     * @return the application mode.
     */
    Application.ApplicationMode getMode() ;

    /**
     * Start the application instance.
     */
    void start() ;

    /**
     * Load the application dependencies.
     */
    void load() ;

}
