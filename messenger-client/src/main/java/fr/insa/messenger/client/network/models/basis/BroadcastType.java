package fr.insa.messenger.client.network.models.basis;

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
     * Moreover asked for everyone information (ip, mac, pseudo, listening port)
     */
    EVERYONE_INFO,

    /**
     * The user tells everyone he changed his
     * pseudo.
     */
    CHANGED_PSEUDO,

}
