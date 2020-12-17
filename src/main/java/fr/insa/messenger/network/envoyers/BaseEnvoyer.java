package fr.insa.messenger.network.envoyers;

import java.net.Socket;
import fr.insa.messenger.models.User;
import fr.insa.messenger.network.Envoyer;
import fr.insa.messenger.network.models.basis.DataPacket;
import fr.insa.messenger.network.streams.OutputSocketStream;

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
     * @param user : targeted user.
     * @param port : destination port.
     */
    protected void sendPacket(DataPacket<?> packet, User user, int port) {
        try {
            Socket socket = new Socket(user.getAddress(), port) ;

            OutputSocketStream exchanger = new OutputSocketStream(socket) ;
            exchanger.send(packet) ;

            exchanger.close() ;
            socket.close() ;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

}
