package fr.insa.messenger.client.network.envoyers;

import java.io.File;
import java.net.Socket;
import java.io.FileInputStream;
import fr.insa.messenger.client.models.User;
import fr.insa.messenger.client.network.Envoyer;
import fr.insa.messenger.client.network.NetworkInterface;
import fr.insa.messenger.client.network.models.FilePacket;

/**
 * @author Damien MOLINA
 */
public class FileEnvoyer extends BaseEnvoyer {

    /**
     * Data to send.
     */
    private final File file ;

    /**
     * Make a new message envoyer.
     *
     * @param envoyer : envoyer instance
     * @param target : targeted user.
     */
    public FileEnvoyer(Envoyer envoyer, User target, final File file) {
        super(envoyer, target) ;

        this.file = file ;
    }

    /**
     * Make the sending.
     */
    @Override
    protected void send() {
        try {
            Socket socket = new Socket(
                this.user.getAddress(), NetworkInterface.FILE_RECEIVING_PORT
            ) ;

            FilePacket packet = new FilePacket(
                this.user, this.file.getName(), this.file.length()
            ) ;

            this.envoyer.sendFile(
                socket, packet, new FileInputStream(this.file)
            ) ;
        } catch (Exception e) {
            e.printStackTrace() ;
        }
    }

}
