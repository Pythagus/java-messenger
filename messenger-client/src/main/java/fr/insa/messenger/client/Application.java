package fr.insa.messenger.client;

import java.util.Locale;
import fr.insa.messenger.tools.Config;
import fr.insa.messenger.client.system.Env;
import fr.insa.messenger.client.system.console.Console;
import fr.insa.messenger.client.system.ApplicationStarter;
import fr.insa.messenger.tools.database.DatabaseConnection;

/**
 * This is a list of the taken serialize
 * uid:
 * - 4242424242424242410L : MessageData
 * - 4242424242424242411L : Message
 * - 4242424242424242412L : MessageFile
 * - 4242424242424242424L : User
 * - 4242424242424242430L : Packet
 * - 4242424242424242431L : UserPacket
 * - 4242424242424242432L : MeetingPacket
 * - 4242424242424242433L : MessagePacket
 * - 4242424242424242434L : DataPacket
 * - 4242424242424242435L : FilePacket
 *
 * @author Damien MOLINA
 */
public class Application {

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
     * The Application version.
     */
    private static final String VERSION = "0.2.0" ;

    /**
     * Application starter instance.
     */
    private final ApplicationStarter starter ;

    /**
     * Make a new instance of the application.
     */
    public Application() {
        this.name = Config.get("APP_NAME") ;
        this.mode = Boolean.parseBoolean(
            Config.get("APP_DEBUG", "0")
        ) ? ApplicationMode.DEBUG : ApplicationMode.PRODUCTION ;
        this.starter = new ApplicationStarter(this) ;

        Env.setApplication(this) ;

        if(this.isDebugMode()) {
            this.printConsoleIntro() ;
        }
    }

    /**
     * Get the application starter instance.
     *
     * @return application starter instance.
     */
    public ApplicationStarter getStarter() {
        return this.starter ;
    }

    /**
     * Print the console introduction.
     */
    private void printConsoleIntro() {
        Console.comment("/========== " + this.getName() + " ==========\\") ;
        Console.comment("/= Version " + Application.VERSION) ;
        Console.comment("/=") ;
        Console.comment("/= Developed by Maud Pennetier and Damien Molina") ;
        Console.comment("") ;
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
     * Load the application dependencies.
     */
    public void load() {
        // Set the default local value.
        Locale.setDefault(Locale.FRANCE) ;

        // Load the database.
        DatabaseConnection.loadDriver() ;
    }

    /**
     * Start the application instance.
     */
    public void start() {
        this.starter.start() ;
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

}
