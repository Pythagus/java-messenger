package fr.insa.messenger.tools.models;

/**
 * @author Damien MOLINA
 */
public enum UserStatus {

    /**
     * Status when the user is
     * connected to the application.
     */
    CONNECTED,

    /**
     * The user is not connected
     * to the application.
     */
    DISCONNECTED,

    /**
     * The user is connected to the
     * application, but he is idle.
     */
    IDLE

}
