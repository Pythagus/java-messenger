package fr.insa.messenger.client.network.models;

import java.util.Objects;
import java.io.Serializable;

import fr.insa.messenger.client.network.models.basis.UserPacket;
import fr.insa.messenger.client.models.User;

/**
 * @author Damien MOLINA
 */
public class MeetingPacket extends UserPacket<Object> implements Serializable {

    /**
     * Serialisation identifier.
     */
    private static final long serialVersionUID = 4242424242424242432L ;

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

        /**
         * The sender wants to close
         * the conversation.
         */
        LEAVE,
    }

    /**
     * Current packet state.
     */
    private State state ;

    /**
     * Make a new packet instance.
     *
     * @param srcUser  : source user instance.
     * @param destUser : destination user instance.
     */
    public MeetingPacket(User srcUser, User destUser) {
        super(srcUser, destUser) ;
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
     * Determine whether the packet
     * is waiting for response.
     *
     * @return True if It is, False otherwise.
     */
    public boolean isWaitingForResponse() {
        return ! this.state.equals(State.LEAVE) ;
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

}
