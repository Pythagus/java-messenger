package Messenger.Network.Models;

import java.util.Objects;
import Messenger.Foundation.Models.User;

/**
 * @author Damien MOLINA
 */
public class MeetingPacket extends Packet {

    /**
     * Serialisation identifier.
     */
    private static final long serialVersionUID = 4242424242424242431L ;

    /**
     * Possible packet state.
     */
    public enum State {
        /**
         * The packet is the first one requesting
         * a new connection with the current user.
         */
        REQUEST,

        /**
         * The request was accepted.
         */
        ACCEPTED,

        /**
         * The request was denied.
         */
        DENIED,
    }

    /**
     * Current packet state.
     */
    private State state ;

    /**
     * The source user.
     */
    private User srcUser ;

    /**
     * The destination user.
     */
    private User destUser ;

    /**
     * Make a new packet instance.
     *
     * @param srcUser  : source user instance.
     * @param destUser : destination user instance.
     */
    public MeetingPacket(User srcUser, User destUser) {
        super(srcUser.getAddress(), destUser.getAddress()) ;

        this.srcUser  = srcUser ;
        this.destUser = destUser ;
    }

    /**
     * Determine whether the packet has
     * the given state.
     *
     * @param state : state test.
     * @return True if It has, False otherwise.
     */
    public boolean hasState(State state) {
        return this.state == state ;
    }

    /**
     * Get the current packet state.
     *
     * @return the State value.
     */
    public State getState() {
        return this.state ;
    }

    /**
     * Set the current packet state.
     *
     * @param state : new state.
     */
    public void setState(State state) {
        this.state = state ;
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
     * Determine whether two packets are
     * equal.
     *
     * @param o : other packet to test with.
     * @return True if it's the same object, False otherwise.
     */
    public boolean equals(Object o) {
        if(this == o) {
            return true ;
        }

        if(o == null || this.getClass() != o.getClass()) {
            return false ;
        }

        MeetingPacket that = (MeetingPacket) o ;

        return Objects.equals(this.srcUser, that.getSourceUser())
            && Objects.equals(this.destUser, that.getDestinationUser())
            && Objects.equals(this.srcAddress, that.getSourceAddress())
            && Objects.equals(this.destAddress, that.getDestinationAddress());
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
