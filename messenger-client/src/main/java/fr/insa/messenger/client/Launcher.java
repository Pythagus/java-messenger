package fr.insa.messenger.client;

import fr.insa.messenger.tools.Config;

/**
 * Application launcher.
 *
 * @author Damien MOLINA
 */
public class Launcher {

    /**
     * Start the application.
     *
     * @param args : command line arguments.
     */
    public static void main(String[] args) {
        /*
         * Set the configuration file root
         * before the application is started.
         */
        Config.setRoot("/META-INF/") ;

        Application messenger = new Application() ;
        messenger.start() ;
    }

}
