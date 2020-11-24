package Messenger;

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
        Messenger msn = new Messenger() ;
        msn.start() ;
    }

}
