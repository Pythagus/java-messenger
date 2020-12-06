package Messenger.Network.Models.Broadcast;

import java.util.ArrayList;
import Messenger.Foundation.System.Env;
import Messenger.Foundation.Models.User;
import Messenger.Network.Models.Concerns.DataPacket;
import Messenger.Network.Utils.BroadcastSplitter;

/**
 * @author Damien MOLINA
 */
public class BroadcastNotification extends DataPacket<Object> {

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
     * Make a new BroadcastNotification instance.
     *
     * @param type : broadcast type.
     * @param user : user sending the notification.
     */
    public BroadcastNotification(BroadcastType type, User user) {
        this.type = type ;
        this.user = user ;
    }

    /**
     * Make a new BroadcastNotification instance.
     *
     * @param type : broadcast type.
     */
    public BroadcastNotification(BroadcastType type) {
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
     * a BroadcastNotification instance.
     *
     * @param str : string to unserialize.
     * @return the new instance.
     * @throws Exception : user error.
     */
    public static BroadcastNotification unserialize(String str) throws Exception {
        ArrayList<String> arr = BroadcastSplitter.split(str) ;

        return new BroadcastNotification(
            BroadcastType.valueOf(arr.get(0)), new User(arr.get(1), arr.get(2), arr.get(3))
        ) ;
    }

}
