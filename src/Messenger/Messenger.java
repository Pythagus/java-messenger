package Messenger;

import Messenger.GUI.Screens.Screen;
import Messenger.GUI.Screens.uiLogin;
import Messenger.Foundation.Application;

/**
 * @author Damien MOLINA
 */
public class Messenger extends Application {

    /**
     * The Messenger version.
     */
    private static final String VERSION = "1.0.0" ;

    /**
     * The application name.
     */
    private static final String NAME = "Java Messenger" ;

    /**
     * Environment application mode.
     */
    private static final ApplicationMode mode = ApplicationMode.DEBUG ;

    /**
     * Make a new instance of Messenger.
     */
    public Messenger() {
        super(Messenger.mode) ;
    }

    /**
     * Get the current version of the
     * application.
     *
     * @return the application version.
     */
    public String getVersion() {
        return Messenger.VERSION ;
    }

    /**
     * Get the current application's name.
     *
     * @return the application name.
     */
    public String getName() {
        return Messenger.NAME ;
    }

    /**
     * Get the screen instance displayed when
     * the application is started.
     *
     * @return a Screen instance.
     */
    protected Screen getStartingScreen() {
        return new uiLogin() ;
    }

}
