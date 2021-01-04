package fr.insa.messenger.tomcat.network;

import fr.insa.messenger.tools.models.UserStatus;
import fr.insa.messenger.tools.network.BroadcastSplitter;

/**
 * @author Damien MOLINA
 */
public class BroadcastPresencePacket {

    /**
     * User identifier.
     */
    private final String identifier ;

    /**
     * User status.
     */
    private final UserStatus status ;

    /**
     * Make a broadcast presence packet instance.
     *
     * @param identifier : user identifier.
     * @param status : user status.
     */
    public BroadcastPresencePacket(String identifier, UserStatus status) {
        this.identifier = identifier ;
        this.status     = status ;
    }

    /**
     * Make a string out of a Broadcast notification.
     *
     * @return String with a standardized format
     */
    public String serialize() {
        // 1 : broadcast type from the server to all the users.
        return BroadcastSplitter.join(
            "1", this.identifier, this.status.toString()
        ) ;
    }

}
