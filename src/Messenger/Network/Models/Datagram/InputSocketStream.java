package Messenger.Network.Models.Datagram;

import java.net.Socket;

/**
 * Class used to concentrate the datagram
 * sending and receiving in the same
 * interface.
 *
 * @author Damien MOLINA
 */
public class InputSocketStream extends SocketStream {

    /**
     * Make a new stream instance.
     */
    public InputSocketStream(Socket socket) throws Exception {
        this.bindInput(socket.getInputStream()) ;
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
    }

}

