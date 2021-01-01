package fr.insa.messenger.client;

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
        Application messenger = new Application() ;
        messenger.start() ;
    }

}
