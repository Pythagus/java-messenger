package Messenger.Network.Models.Datagram;

import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.Socket;

/**
 * Class used to concentrate the datagram
 * sending and receiving in the same
 * interface.
 *
 * @author Damien MOLINA
 */
public class SocketStream implements PacketExchangerContract {

    /**
     * The input data stream.
     */
    protected ObjectInputStream input ;

    /**
     * The output data stream.
     */
    protected ObjectOutputStream output ;

    /**
     * Make a new stream instance.
     */
    public SocketStream() {}

    /**
     * Bind the input with the given stream.
     *
     * @param stream : input socket stream.
     * @throws Exception : stream error.
     */
    public void bindInput(InputStream stream) throws Exception {
        this.input = new ObjectInputStream(stream) ;
    }

    /**
     * Bind the output with the given stream.
     *
     * @param stream : output socket stream.
     * @throws Exception : stream error.
     */
    public void bindOutput(OutputStream stream) throws Exception {
        this.output = new ObjectOutputStream(stream) ;
    }

    /**
     * Clear the stream.
     *
     * @throws Exception : close error.
     */
    public void clear() throws Exception {
        this.close() ;

        this.input  = null ;
        this.output = null ;
    }

    /**
     * Bind the in and out puts with the given streams.
     *
     * @param socket : socket to bind with.
     * @throws Exception : stream error.
     */
    public void bind(Socket socket) throws Exception {
        this.bindOutput(socket.getOutputStream()) ;
        this.bindInput(socket.getInputStream()) ;
    }

    /**
     * Get the received data as an object.
     *
     * @return the received data.
     * @throws Exception : stream error.
     */
    public Object receive() throws Exception {
        return this.input.readObject() ;
    }

    /**
     * Send the given object.
     *
     * @param data : object to send.
     * @throws Exception : stream error.
     */
    public void send(Object data) throws Exception {
        this.output.writeObject(data) ;
        this.output.flush() ;
    }

    public void sendF(byte[] data, int offset, int leng) throws Exception{
        this.output.write(data, offset, leng);
        this.output.flush();
    }

    /**
     * Close the streams.
     *
     * @throws Exception : stream error.
     */
    public void close() throws Exception {
        if(this.input != null) {
            this.input.close() ;
        }

        if(this.output != null) {
            this.output.close() ;
        }
    }

}

