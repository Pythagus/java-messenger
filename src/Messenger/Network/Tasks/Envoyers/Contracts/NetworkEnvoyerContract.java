package Messenger.Network.Tasks.Envoyers.Contracts;

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
