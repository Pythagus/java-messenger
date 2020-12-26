package fr.insa.messenger.client.network.envoyers;

import java.net.Socket;

import fr.insa.messenger.client.network.models.basis.Packet;
import fr.insa.messenger.client.models.User;
import fr.insa.messenger.client.network.Envoyer;

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

    /**
     * Send the given packet.
     *
     * @param packet : packet
     * @param port : destination port.
     */
    protected void sendPacket(Packet<?> packet, int port) {
        try {
            Socket socket = new Socket(
                packet.getDestinationAddress(), port
            ) ;

            this.envoyer.send(
                socket, packet, true
            ) ;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

}
