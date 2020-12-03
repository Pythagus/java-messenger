package Messenger.Network.Tasks.Envoyers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import Messenger.Network.Models.Broadcast.BroadcastNotification;

public class BroadcastEnvoyer
{
    private int portListen = 60403;

    public void sendUDP(BroadcastNotification notif) throws Exception
    {
        String message = notif.NotifToString();
        byte[] buffer = message.getBytes();

        DatagramSocket sender = new DatagramSocket();  //create connexion

        //datagram
        InetAddress ipAdress = InetAddress.getByName("255.255.255.255"); //broadcast
        DatagramPacket datagram = new DatagramPacket(buffer, buffer.length, ipAdress, portListen);

        datagram.setData(buffer); //payload
        sender.send(datagram); // send packet

    }



}
