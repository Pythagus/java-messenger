package fr.insa.messenger.tomcat.models;

/**
 * @author Damien MOLINA
 */
public class User {

    /**
     * User identifier.
     */
    private String identifier ;

    /**
     * User status.
     */
    private UserStatus status ;

    /**
     * Make a User instance.
     */
    public User() {}

    /**
     * Get the user identifier.
     *
     * @return the user identifier.
     */
    public String getIdentifier() {
        return this.identifier ;
    }

}
