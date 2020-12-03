package Messenger.Network.Tasks.Envoyers.Concerns;

import Messenger.Foundation.Models.User;
import Messenger.Network.Tasks.Envoyers.NetworkEnvoyer;

/**
 * @author Damien MOLINA
 */
abstract public class BaseEnvoyer implements NetworkEnvoyerContract {

    /**
     * User to send the data to.
     */
    protected final User user ;

    /**
     * Envoyer instance.
     */
    protected final NetworkEnvoyer envoyer ;

    /**
     * Make a new message envoyer.
     *
     * @param envoyer : envoyer instance.
     * @param user : user to send the data to.
     */
    public BaseEnvoyer(NetworkEnvoyer envoyer, User user) {
        this.user    = user ;
        this.envoyer = envoyer ;
    }

}
