package Messenger.Network.Models.Datagram;

import Messenger.Foundation.Models.Messages.MessageFile;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 * Send the file to the others
 */
public class Server
{
    private DataOutputStream dataOutputStream = null;
    private DataInputStream dataInputStream = null;

    /**
     * established a connexion between 2 hosts
     * @param ip ip of the destination
     * @param port port of the socket destination
     * @throws Exception
     */
    public void server(String ip, int port) throws Exception
    {
        try(Socket socket = new Socket(ip, port))
        {
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());


        }

    }

    /**
     * send a text
     * @param text : the content of the message (only string)
     */
    public void sendMessage(String text)
    {
        // TODO
    }

    /**
     * send a text and a file
     * @param text : the content of the message (only string)
     * @param file : a file
     */
    public void sendMessage(String text, MessageFile file )
    {
        // TODO
        long length = file.length();

    }

    /**
     * send a file (i.e. looping to send multiple packet)
     * @param file : a file
     */
    public void sendMessage(MessageFile file )
    {
        // TODO
        //long length = file.length();
        //FileInputStream fileInputStream = new FileInputStream(file);
        // defined buffer size
       // while ()
       // {
       //     dataOutputStream.write()
       // }


    }
}
