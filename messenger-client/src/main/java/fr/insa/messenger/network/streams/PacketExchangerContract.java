package fr.insa.messenger.network.streams;

import java.net.Socket;

/**
 * This interface implements the whole protocol
 * main features that every side of the
 * communication should provide.
 *
 * @author Damien MOLINA
 */
public interface PacketExchangerContract {

    /**
     * Bind the input and output with the given streams.
     *
     * @param socket : socket to bind with.
     * @throws Exception : stream error.
     */
    void bind(Socket socket) throws Exception ;

    /**
     * Receive an outgoing data.
     *
     * @return the received data.
     * @throws Exception : socket errors.
     */
    Object receive() throws Exception ;

    /**
     * Send the given data to the other side
     * of the connection.
     *
     * @param data : the object to send.
     * @throws Exception : socket errors.
     */
    void send(Object data) throws Exception ;

    /**
     * Close the connection.
     *
     * @throws Exception : socket errors.
     */
    void close() throws Exception ;

}
