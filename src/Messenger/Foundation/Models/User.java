package Messenger.Foundation.Models;

import java.util.Objects;

/**
 * Main application user.
 *
 * @author Maud PENNETIER
 */
public class User {

    /**
     * User pseudo.
     */
    protected String pseudo;

    /**
     * User MAC address.
     */
    protected String macAddress;

    /**
     * Get the unique user identifier. This
     * identifier should be unique to clearly
     * identify a user.
     *
     * @return the user's MAC address.
     */
    public String getIdentifier() {
        return this.macAddress ;
    }

    /**
     * Get the user's pseudo.
     *
     * @return the user pseudo.
     */
    public String getPseudo() {
        return this.pseudo ;
    }

    /**
     * Override the default equals method.
     * Two users are equals only if they have
     * the same identifier.
     *
     * @param o : user to compare with.
     * @return True if they are the same, False otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true ;
        }

        if (o == null || this.getClass() != o.getClass()) {
            return false ;
        }

        User user = (User) o ;

        return Objects.equals(
            this.getIdentifier(), user.getIdentifier()
        ) ;
    }

}
