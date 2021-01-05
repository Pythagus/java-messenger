package fr.insa.messenger.tomcat.network;

import java.net.InetAddress;
import java.net.DatagramPacket;
import java.net.MulticastSocket;
import java.net.InetSocketAddress;
import fr.insa.messenger.tools.Config;
import fr.insa.messenger.tools.models.UserStatus;

/**
 * @author Damien MOLINA
 */
public class MulticastEnvoyer extends Thread {

    /**
     * Multicast address.
     */
    private final InetSocketAddress addr ;

    /**
     * Broadcast packet instance.
     */
    private final BroadcastPresencePacket packet ;

    /**
     * Make a new Multicast envoyer instance.
     *
     * @param packet : packet to send.
     */
    public MulticastEnvoyer(BroadcastPresencePacket packet) {
        this.packet = packet ;
        this.addr   = new InetSocketAddress(
            this.getMulticastAddress(), Integer.parseInt(Config.get("MULTICAST_PORT"))
        ) ;
    }

    /**
     * Make a new Multicast envoyer instance.
     *
     * @param identifier : user's identifier.
     * @param status : user's new status.
     */
    public MulticastEnvoyer(String identifier, UserStatus status) {
        this(new BroadcastPresencePacket(identifier, status)) ;
    }

    /**
     * Get the multicast address.
     *
     * @return the multicast address.
     */
    private InetAddress getMulticastAddress() {
        try {
            return InetAddress.getByName(Config.get("MULTICAST_ADDR")) ;
        } catch (Exception e) {
            e.printStackTrace() ;
            return null ;
        }
    }

    @Override
    public void run() {
        try {
            /*
             * We need to set the socket TTL. By
             * default, the TTL value is 1. So, we
             * put 15 to access the entire local
             * network.
             *
             * @see https://perso.telecom-paristech.fr/hudry/coursJava/reseau/multicast.html
             */
            MulticastSocket socket = new MulticastSocket() ;
            socket.setTimeToLive(15) ;

            // Buffer to send.
            byte[] buffer = this.packet.serialize().getBytes() ;

            // Packet to send.
            DatagramPacket packet = new DatagramPacket(
                buffer, buffer.length, this.addr
            ) ;

            // Send the datagram.
            socket.send(packet) ;
        } catch(Exception e) {
            e.printStackTrace() ;
        }
    }

}
