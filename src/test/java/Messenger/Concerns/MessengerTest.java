package Messenger.Concerns;

import Messenger.Messenger;

/**
 * @author Damien MOLINA
 */
abstract public class MessengerTest {

    /**
     * Start the application.
     */
    protected void startApplication() {
        Messenger msn = new Messenger() ;
        msn.startWithoutGraphics() ;
    }

}
