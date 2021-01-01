package fr.insa.messenger.client.system;

import fr.insa.messenger.client.Application;
import fr.insa.messenger.client.models.User;
import fr.insa.messenger.client.http.PresenceResponse;
import fr.insa.messenger.client.http.PresenceInterface;
import fr.insa.messenger.client.system.console.Console;
import fr.insa.messenger.client.network.NetworkInterface;
import fr.insa.messenger.client.http.PresenceServerException;

/**
 * @author Damien MOLINA
 */
public class ApplicationStarter {

    /**
     * Application instance.
     */
    private final Application application ;

    /**
     * Start the application.
     *
     * @param app : application instance.
     */
    public ApplicationStarter(Application app) {
        this.application = app ;
    }

    /**
     * Start the application.
     *
     * @param app : application to start.
     */
    public static void start(Application app) {
        new ApplicationStarter(app).start() ;
    }

    /**
     * Start the application.
     */
    public void start() {
        // Load the dependencies.
        this.application.load() ;

        // Set the current user.
        Env.setUser(new User()) ;

        // Start the graphics.
        this.application.startGraphics() ;

        // Start the network interface.
        this.startNetwork() ;

        // Subscribe to the status list.
        this.startPresence() ;
    }

    /**
     * Start the network interface.
     */
    public void startNetwork() {
        try {
            NetworkInterface.setInstance(new NetworkInterface()) ;
            NetworkInterface.instance().start() ;
        } catch (Exception exception) {
            exception.printStackTrace() ;
        }
    }

    /**
     * Subscribe to the presence server
     * status list.
     */
    public void startPresence() {
        try {
            PresenceResponse response = PresenceInterface.subscribe() ;

            if(response.isSuccessful()) {
                Console.comment("[SUCCESS] Presence subscribe") ;
            } else {
                Console.danger("[ERROR] Presence subscribe failed : " + response.getMessage()) ;
            }
        } catch (PresenceServerException ignored) {
            Console.danger("[ERROR] Presence subscribe failed") ;
        }
    }

}
