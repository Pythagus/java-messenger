package Messenger.Network.Models.Broadcast;

import java.util.Arrays;
import java.util.ArrayList;
import Messenger.Foundation.Models.User;

/**
 * @author Damien MOLINA
 */
public class BroadcastNotification {

    private static final String DELIMITER = "#" ;

    /**
     * Broadcast type.
     */
    private final BroadcastType type ;

    /**
     * User sent with the broadcast notification.
     */
    private final User sender ;

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
        return this.sender ;
    }

    /**
     * Make a new BroadcastNotification instance.
     *
     * @param type : broadcast type.
     * @param user : user sending the notification.
     */
    public BroadcastNotification(BroadcastType type, User user) {
        this.type   = type ;
        this.sender = user ;
    }

    /**
     * Make a string out of a Broadcast notification.
     *
     * @return String with a standardized format
     */
    public String serialize() {
        return BroadcastNotification.DELIMITER + this.type +
                BroadcastNotification.DELIMITER + this.sender.getPseudo() +
                BroadcastNotification.DELIMITER + this.sender.getIdentifier() +
                BroadcastNotification.DELIMITER + this.sender.getAddress().getHostAddress() +
                BroadcastNotification.DELIMITER ;
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
        String[] data = str.trim().split(BroadcastNotification.DELIMITER) ;

        ArrayList<String> arr = new ArrayList<>(
            Arrays.asList(data)
        ) ;
        arr.removeIf(
            item -> item == null || "".equals(item)
        ) ;

        return new BroadcastNotification(
            BroadcastType.valueOf(data[0]), new User(arr.get(1), arr.get(2), arr.get(3))
        ) ;
    }

}
