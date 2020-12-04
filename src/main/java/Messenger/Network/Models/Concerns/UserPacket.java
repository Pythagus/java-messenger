package Messenger.Network.Models.Concerns;

import Messenger.Foundation.Models.User;

/**
 * @author Damien MOLINA
 */
abstract public class UserPacket<T> extends Packet<T> {

    /**
     * Serialisation identifier.
     */
    private static final long serialVersionUID = 4242424242424242431L ;

    /**
     * The source user.
     */
    protected User srcUser ;

    /**
     * The destination user.
     */
    protected User destUser ;

    /**
     * Make a new packet instance.
     *
     * @param srcUser  : source user instance.
     * @param destUser : destination user instance.
     */
    public UserPacket(User srcUser, User destUser) {
        super(srcUser.getAddress(), destUser.getAddress()) ;

        this.srcUser  = srcUser ;
        this.destUser = destUser ;
    }

    /**
     * Get the source user instance.
     *
     * @return the User instance.
     */
    public User getSourceUser() {
        return this.srcUser ;
    }

    /**
     * Get the destination user instance.
     *
     * @return the User instance.
     */
    public User getDestinationUser() {
        return this.destUser ;
    }


    /**
     * Reverse the packet addresses.
     */
    public void reverse() {
        super.reverse() ;

        User tmp = this.srcUser ;

        this.srcUser  = this.destUser ;
        this.destUser = tmp ;
    }

}
