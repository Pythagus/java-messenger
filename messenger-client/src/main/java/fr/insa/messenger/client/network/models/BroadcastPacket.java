package fr.insa.messenger.client.network.models;

import java.util.ArrayList;
import java.io.Serializable;

import fr.insa.messenger.client.controllers.UserController;
import fr.insa.messenger.client.system.Env;
import fr.insa.messenger.client.models.User;
import fr.insa.messenger.client.network.models.basis.DataPacket;
import fr.insa.messenger.tools.models.UserStatus;
import fr.insa.messenger.tools.network.BroadcastSplitter;
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
        // 0 : default broadcast type.
        return BroadcastSplitter.join(
            "0", this.type.toString(), this.user.getPseudo(), this.user.getIdentifier(), this.user.getAddress().getHostAddress()
        ) ;
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

        /*
         * We get the broadcast type:
         * 0 : A default broadcast from a user to another.
         * 1 : A broadcast from the presence server to the users.
         */
        int broadcastType = Integer.parseInt(arr.remove(0)) ;
        switch(broadcastType) {
            case 0:
                return new BroadcastPacket(
                    BroadcastType.valueOf(arr.get(0)), new User(arr.get(1), arr.get(2), arr.get(3))
                ) ;
            case 1:
                User user = UserController.instance().getFromIdentifier(arr.get(0)) ;
                user.setStatus(
                    UserStatus.valueOf(arr.get(1))
                ) ;

                return new BroadcastPacket(BroadcastType.CHANGED_STATUS, user) ;
        }

       throw new Exception("Unknown type " + broadcastType) ;
    }

}
