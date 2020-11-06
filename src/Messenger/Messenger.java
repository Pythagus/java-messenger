package Messenger;

import Messenger.application.Application;
import Messenger.application.ApplicationMode;
import Messenger.gui.screens.Screen;
import Messenger.gui.screens.Window;

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
     * Current application mode.
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
        //TODO : check in cookies if a previous session is still available.
        //TODO : return login screen.
        return new Window() ;
    }

}
