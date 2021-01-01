package fr.insa.messenger.client.system;

import fr.insa.messenger.client.Application;
import fr.insa.messenger.client.models.User;
import fr.insa.messenger.client.http.PresenceResponse;
import fr.insa.messenger.client.http.PresenceInterface;
import fr.insa.messenger.client.network.models.basis.BroadcastType;
import fr.insa.messenger.client.observers.UserListListener;
import fr.insa.messenger.client.system.console.Console;
import fr.insa.messenger.client.network.NetworkInterface;
import fr.insa.messenger.client.http.PresenceServerException;
import fr.insa.messenger.client.ui.GraphicInterface;
import fr.insa.messenger.client.ui.frames.Frame;
import fr.insa.messenger.client.ui.frames.LoginFrame;
import fr.insa.messenger.client.ui.frames.MainFrame;

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
     */
    public void start() {
        // Load the dependencies.
        this.application.load() ;

        // Set the current user.
        Env.setUser(new User()) ;

        // Start the graphics.
        this.startGraphics(LoginFrame.class) ;

        // Start the network interface.
        this.startNetwork() ;
    }

    /**
     * Start the graphic frame.
     *
     * @param c : frame to start.
     */
    private void startGraphics(Class<? extends Frame> c) {
        GraphicInterface.instance().start(c) ;
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
     * Start the application when the user
     * is logged in.
     */
    public void startLoggedIn() {
        // Start the graphics.
        this.startGraphics(MainFrame.class) ;
        GraphicInterface.instance().notifyWhenRendered(UserListListener::updateUI) ;

        // Send the broadcast notification.
        NetworkInterface.instance().getEnvoyer().broadcast(BroadcastType.LOGIN) ;

        // Subscribe to the status list.
        this.startPresence() ;
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
