package fr.insa.messenger.client.network.models;

import java.util.ArrayList;
import java.io.Serializable;
import fr.insa.messenger.client.system.Env;
import fr.insa.messenger.client.models.User;
import fr.insa.messenger.client.network.models.basis.DataPacket;
import fr.insa.messenger.client.network.utils.BroadcastSplitter;
import fr.insa.messenger.client.network.models.basis.BroadcastType;

/**
 * @author Damien MOLINA
 */
public class BroadcastPacket extends DataPacket<Object> implements Serializable {

    /**
     * Broadcast type.
     */
    protected final BroadcastType type ;

    /**
     * User sent with the broadcast notification.
     */
    protected final User user ;

    /**
     * Get the broadcast type.
     *
     * @return the type.
     */
    public BroadcastType getType() {
        return this.type ;
    }

    /**
     * Get the notification's sender.
     *
     * @return the user instance.
     */
    public User getUser() {
        return this.user;
    }

    /**
     * Make a new BroadcastPacket instance.
     *
     * @param type : broadcast type.
     * @param user : user sending the notification.
     */
    public BroadcastPacket(BroadcastType type, User user) {
        this.type = type ;
        this.user = user ;
    }

    /**
     * Make a new BroadcastPacket instance.
     *
     * @param type : broadcast type.
     */
    public BroadcastPacket(BroadcastType type) {
        this(type, Env.getUser()) ;
    }

    /**
     * Make a string out of a Broadcast notification.
     *
     * @return String with a standardized format
     */
    public String serialize() {
        return BroadcastSplitter.DELIMITER + this.type +
            BroadcastSplitter.DELIMITER + this.user.getPseudo() +
            BroadcastSplitter.DELIMITER + this.user.getIdentifier() +
            BroadcastSplitter.DELIMITER + this.user.getAddress().getHostAddress() +
            BroadcastSplitter.DELIMITER ;
    }

    /**
     * Unserialize the given string to make
     * a BroadcastPacket instance.
     *
     * @param str : string to unserialize.
     * @return the new instance.
     * @throws Exception : user error.
     */
    public static BroadcastPacket unserialize(String str) throws Exception {
        ArrayList<String> arr = BroadcastSplitter.split(str) ;

        return new BroadcastPacket(
            BroadcastType.valueOf(arr.get(0)), new User(arr.get(1), arr.get(2), arr.get(3))
        ) ;
    }

}
