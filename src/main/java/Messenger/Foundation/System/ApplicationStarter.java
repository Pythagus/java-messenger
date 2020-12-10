package Messenger.Foundation.System;

import Messenger.Foundation.Application;
import Messenger.Foundation.Models.User;
import Messenger.Network.NetworkInterface;
import Messenger.Foundation.Controllers.UserController;
import Messenger.Foundation.Controllers.ConversationController;
import Messenger.Foundation.Observers.Listeners.UserListUpdated;

/**
 * @author Damien MOLINA
 */
public class ApplicationStarter {

    /**
     * Application instance.
     */
    private final Application application ;

    /**
     * Determine whether the graphics should
     * start with the application.
     */
    private boolean graphics = true ;

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
     *
     * @param app : application to start.
     */
    public static void startWithoutGraphics(Application app) {
        new ApplicationStarter(app).withoutGraphics().start() ;
    }

    /**
     * Set the graphics properties to false.
     *
     * @return this.
     */
    public ApplicationStarter withoutGraphics() {
        this.graphics = false ;

        return this ;
    }

    /**
     * Start the application.
     */
    public void start() {
        // Load the dependencies.
        this.application.load() ;

        // Set the current user.
        Env.setUser(new User()) ;

        // Start the controllers.
        this.startControllers() ;

        /*
         * Start the graphics only whether
         * the application need them.
         */
        if(this.graphics) {
            this.application.startGraphics() ;
        }

        // Start the network interface.
        this.startNetwork() ;
    }

    /**
     * Start the network interface.
     */
    public void startNetwork() {
        try {
            Env.setNetworkInterface(new NetworkInterface()) ;
            Env.getNetworkInterface().start() ;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Start the controllers.
     */
    public void startControllers() {
        // User controller.
        UserController userController = new UserController() ;
        userController.addListener(new UserListUpdated()) ;
        Env.addController(userController) ;

        // Conversation controller.
        Env.addController(new ConversationController()) ;
    }

}
