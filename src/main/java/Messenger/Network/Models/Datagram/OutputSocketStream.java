package Messenger.Network.Models.Datagram;

import java.net.Socket;

/**
 * Class used to concentrate the datagram
 * sending and receiving in the same
 * interface.
 *
 * @author Damien MOLINA
 */
public class OutputSocketStream extends SocketStream {

    /**
     * Make a new stream instance.
     */
    public OutputSocketStream(Socket socket) throws Exception {
        this.bindOutput(socket.getOutputStream()) ;
    }

    /**
     * Close the streams.
     *
     * @throws Exception : stream error.
     */
    public void close() throws Exception {
        if(this.output != null) {
            this.output.close() ;
        }
    }

}

