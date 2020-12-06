package Messenger.Network.Models.Broadcast;

import Messenger.Foundation.Models.User;

/**
 * @author Damien MOLINA
 */
public class BroadcastResponsePacket extends BroadcastNotification {

    /**
     * Receiver instance.
     */
    private final User target ;

    /**
     * Make a new BroadcastNotification instance.
     *
     * @param type : broadcast type.
     * @param target : user receiving the notification.
     */
    public BroadcastResponsePacket(BroadcastType type, User target) {
        super(type) ;

        this.target = target ;
    }

    /**
     * Get the targeted user.
     *
     * @return targeted user.
     */
    public User getTarget() {
        return this.target ;
    }

}
