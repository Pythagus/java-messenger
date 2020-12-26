package fr.insa.messenger.models;

import java.util.Objects;
import java.io.Serializable;
import java.net.InetAddress;
import fr.insa.messenger.system.Env;
import fr.insa.messenger.network.utils.AddressUtils;

/**
 * Main application user.
 *
 * @author Maud PENNETIER
 */
public class User implements Serializable {

    /**
     * Serialisation identifier.
     */
    private static final long serialVersionUID = 4242424242424242424L ;

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
     * Make a new user instance.
     *
     * @param pseudo : user pseudo.
     * @param macAddress : user mac address.
     * @param addr : user IP address.
     * @throws Exception : address error.
     */
    public User(String pseudo, String macAddress, String addr) throws Exception {
        this(pseudo, macAddress, InetAddress.getByName(addr)) ;
    }

    /**
     * Make a new user instance.
     *
     * @param pseudo : user pseudo.
     * @param macAddress : user mac address.
     * @param addr : user IP address.
     */
    public User(String pseudo, String macAddress, InetAddress addr) {
        this.pseudo     = pseudo ;
        this.macAddress = macAddress;
        this.address    = addr ;
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
     * Determine whether the current user
     * instance is the one logged in
     * the application.
     *
     * @return True or False.
     */
    public boolean isEnvUser() {
        return this.equals(Env.getUser()) ;
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
