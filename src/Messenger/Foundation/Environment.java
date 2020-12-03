package Messenger.Foundation;

import Messenger.Foundation.Providers.ControllerProvider;
import Messenger.Foundation.Controllers.Controller;
import Messenger.Network.NetworkInterface;
import Messenger.Foundation.Models.User;

/**
 * @author Damien MOLINA
 */
public final class Environment {

    // Forbid object instantiation.
    private Environment() {}

    /**
     * Application instance.
     */
    private static Application application ;

    /**
     * Currently logged in user.
     */
    private static User user ;

    /**
     * Controller provider instance.
     */
    private static final ControllerProvider controller = new ControllerProvider() ;

    /**
     * Network interface instance.
     */
    private static NetworkInterface networkInterface ;


    /**
     * Get the current logged in user instance.
     *
     * @return the user instance if there is a
     *      logged in user, NULL otherwise.
     */
    public static User getUser() {
        return Environment.user ;
    }

    /**
     * Set the user instance.
     *
     * @param user : the user instance.
     */
    public static void setUser(User user) {
        Environment.user = user;
    }

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

    /**
     * Add a controller to the controllers list.
     *
     * @param controller : controller to add.
     */
    public static void addController(Controller controller) {
        Environment.controller.add(controller) ;
    }

    /**
     * Get the controller identified by the given
     * class instance.
     *
     * @param controller : controller class.
     * @return the controller instance, null otherwise.
     */
    public static Controller getController(Class<? extends Controller> controller) {
        return Environment.controller.get(controller) ;
    }

    /**
     * Get the network interface instance.
     *
     * @return the network interface instance.
     */
    public static NetworkInterface getNetworkInterface() {
        return Environment.networkInterface ;
    }

    /**
     * Get the network interface instance.
     *
     * @param networkInterface : the network interface instance.
     */
    public static void setNetworkInterface(NetworkInterface networkInterface) {
        Environment.networkInterface = networkInterface ;
    }

}
