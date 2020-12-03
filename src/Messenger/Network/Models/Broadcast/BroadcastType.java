package Messenger.Network.Models.Broadcast;

/**
 * @author Damien MOLINA
 */
public enum BroadcastType {

    /**
     * The user logged into the network.
     */
    LOGIN,

    /**
     * The user left the network.
     */
    LOGOUT,

    /**
     * The user wants to know if anybody has
     * the given pseudo.
     * Moreover asked for everyone informations (ip, mac, pseudo, listening port)
     */
    HASPSEUDO,

    /**
     * The user tells everyone he changed his
     * pseudo.
     */
    CHANGEDPSEUDO,

}
