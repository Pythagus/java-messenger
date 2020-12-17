package Messenger.Foundation;

import java.util.Locale;
import Messenger.Database.DB;
import Messenger.GUI.GraphicInterface;
import Messenger.Foundation.System.Env;
import Messenger.GUI.Frames.LoginFrame;
import Messenger.Foundation.System.Config;
import Messenger.Foundation.System.Console.Console;
import Messenger.Foundation.System.ApplicationStarter;
import Messenger.Foundation.Contracts.ApplicationContract;

/**
 * @author Damien MOLINA
 */
abstract public class Application implements ApplicationContract {

    /**
     * Available application modes.
     */
    public enum ApplicationMode {
        DEBUG, PRODUCTION
    }

    /**
     * Env application mode.
     */
    protected final ApplicationMode mode ;

    /**
     * Application name.
     */
    private final String name ;

    /**
     * Make a new instance of the application.
     */
    public Application() {
        this.name = Config.get("APP_NAME") ;
        this.mode = Boolean.parseBoolean(
            Config.get("APP_DEBUG", "0")
        ) ? ApplicationMode.DEBUG : ApplicationMode.PRODUCTION ;

        Env.setApplication(this) ;

        if(this.isDebugMode()) {
            this.printConsoleIntro() ;
        }
    }

    /**
     * Print the console introduction.
     */
    private void printConsoleIntro() {
        Console.comment("/========== " + this.getName() + " ==========\\") ;
        Console.comment("/= Version " + this.getVersion()) ;
        Console.comment("/=") ;
        Console.comment("/= Developed by Maud Pennetier and Damien Molina") ;
        Console.comment("") ;
    }

    /**
     * Get the current application's mode.
     *
     * @return the application mode.
     */
    public ApplicationMode getMode() {
        return this.mode ;
    }

    /**
     * Get the current application's name.
     *
     * @return the application name.
     */
    public String getName() {
        return this.name ;
    }

    /**
     * Determine whether the application is in
     * debug mode.
     *
     * @return True if It is, False otherwise.
     */
    public boolean isDebugMode() {
        return this.mode.equals(ApplicationMode.DEBUG) ;
    }

    /**
     * Load the application dependencies.
     */
    public void load() {
        // Set the default local value.
        Locale.setDefault(Locale.FRANCE) ;

        // Load the database.
        DB.load() ;
    }

    /**
     * Start the application instance.
     */
    public void start() {
        ApplicationStarter.start(this) ;
    }

    /**
     * Start the graphics.
     */
    public void startGraphics() {
        GraphicInterface.instance().start(LoginFrame.class) ;
    }

}
