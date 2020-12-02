package Messenger.Foundation.Models;

import java.util.Objects;
import java.net.InetAddress;
import Messenger.Network.Utils.AddressUtils;

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
     * Source address.
     */
    protected InetAddress address ;

    /**
     * Make a new User instance.
     */
    public User() {
        try {
            this.address    = AddressUtils.getIpAddress() ;
            this.macAddress = AddressUtils.getMacAddress() ;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


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
     * Get the user inet address.
     *
     * @return the address.
     */
    public InetAddress getAddress() {
        return this.address ;
    }

    /**
     * Set the user pseudo.
     *
     * @param pseudo : the user pseudo.
     */
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo ;
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
