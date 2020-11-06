package Messenger.application;

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
    public String getVersion() ;

    /**
     * Get the current application's name.
     *
     * @return the application name.
     */
    public String getName() ;

    /**
     * Get the current application's mode.
     *
     * @return the application mode.
     */
    public ApplicationMode getMode() ;

    /**
     * Start the application instance.
     */
    public void start() ;

}
