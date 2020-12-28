package fr.insa.messenger.tomcat.models;

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
    IDLE ;

    /**
     * Determine whether the current status
     * defines a logged in user.
     *
     * @return True if the user should already be connected,
     *         False otherwise.
     */
    public boolean shouldBeConnected() {
        return this.equals(CONNECTED) || this.equals(IDLE) ;
    }

}
