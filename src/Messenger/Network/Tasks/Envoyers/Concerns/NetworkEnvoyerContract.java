package Messenger.Network.Tasks.Envoyers.Concerns;

import java.io.IOException;

/**
 * @author Damien MOLINA
 */
public interface NetworkEnvoyerContract {

    /**
     * Make the sending.
     */
    public void send() throws IOException ;

}
