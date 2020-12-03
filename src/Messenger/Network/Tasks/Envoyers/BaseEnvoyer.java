package Messenger.Network.Tasks.Envoyers;

import Messenger.Foundation.Models.User;

/**
 * @author Damien MOLINA
 */
abstract public class BaseEnvoyer extends Thread {

    /**
     * User to send the data to.
     */
    protected User user ;

    /**
     * Envoyer instance.
     */
    protected final Envoyer envoyer ;

    /**
     * Make the sending.
     */
    abstract protected void send() throws Exception ;

    /**
     * Make a new message envoyer.
     *
     * @param envoyer : envoyer instance.
     * @param user : user to send the data to.
     */
    public BaseEnvoyer(Envoyer envoyer, User user) {
        this.user    = user ;
        this.envoyer = envoyer ;
    }

    /**
     * Make a new message envoyer.
     *
     * @param envoyer : envoyer instance.
     */
    public BaseEnvoyer(Envoyer envoyer) {
        this.envoyer = envoyer ;
    }

    /**
     * Run the envoyer.
     */
    public void run() {
        try {
            this.send() ;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
