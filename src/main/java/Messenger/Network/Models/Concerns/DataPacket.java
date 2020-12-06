package Messenger.Network.Models.Concerns;

/**
 * Serial version numbers:
 * - 4242424242424242430L : Packet
 * - 4242424242424242431L : UserPacket
 * - 4242424242424242432L : MeetingPacket
 * - 4242424242424242433L : MessagePacket
 *
 * @author Damien MOLINA
 */
abstract public class DataPacket<T> {

    /**
     * Packet data.
     */
    private T data ;

    /**
     * Get the packet data.
     *
     * @return the data.
     */
    public T getData() {
        return this.data ;
    }

    /**
     * Set the packet data.
     *
     * @param data : packet data.
     */
    public void setData(T data) {
        this.data = data ;
    }

}
