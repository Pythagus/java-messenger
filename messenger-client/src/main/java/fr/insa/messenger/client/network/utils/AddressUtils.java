package fr.insa.messenger.client.network.utils;

import java.net.*;
import java.util.Enumeration;

/**
 * @author Damien MOLINA
 */
public class AddressUtils {

    /**
     * Get the user MAC address.
     *
     * @return the MAC address.
     * @throws Exception : impossible to get the MAC address.
     */
    public static String getMacAddress() throws Exception {
        NetworkInterface netInf = NetworkInterface.getNetworkInterfaces().nextElement() ;

        // Get the first MAC address available.
        byte[] mac = netInf.getHardwareAddress() ;

        // Convert the MAC address to be readable.
        StringBuilder sb = new StringBuilder() ;
        for (int i = 0; i < mac.length; i++) {
            sb.append(
                String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : "")
            ) ;
        }

        return sb.toString() ;
    }

    /**
     * Get the broadcast network address.
     *
     * @apiNote https://stackoverflow.com/a/29238764
     * @return the broadcast address.
     */
    public static InetAddress getBroadcastAddress() {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces() ;

            while(interfaces.hasMoreElements()) {
                NetworkInterface networkInterface = interfaces.nextElement() ;

                // Don't use the loopback interface.
                if(networkInterface.isLoopback()) {
                    continue ;
                }
                // Do not want to use the loopback interface.
                for (InterfaceAddress interfaceAddress : networkInterface.getInterfaceAddresses()) {
                    InetAddress broadcast = interfaceAddress.getBroadcast();
                    if (broadcast == null) {
                        continue ;
                    }

                    return broadcast ;
                }
            }
        } catch(Exception e) {
            e.printStackTrace() ;
        }

        return null ;
    }

    /**
     * Get the multicast address.
     *
     * @return an InetAddress for the multicast.
     */
    public static InetAddress getMulticastAddress() {
        try {
            return InetAddress.getByName(
                fr.insa.messenger.client.network.NetworkInterface.MULTICAST_ADDR
            ) ;
        } catch (Exception e) {
            e.printStackTrace() ;
            return null ;
        }
    }

    /**
     * Get the user IP address.
     *
     * @apiNote see https://stackoverflow.com/a/20418809 for the original post.
     * @throws UnknownHostException If the LAN address of the machine cannot be found.
     */
    public static InetAddress getIpAddress() throws UnknownHostException {
        UnknownHostException exception = new UnknownHostException("Failed to get IP") ;

        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces() ;

            // For each interfaces
            while(interfaces.hasMoreElements()) {
                NetworkInterface nInterface = interfaces.nextElement() ;
                Enumeration<InetAddress> addresses = nInterface.getInetAddresses() ;

                if(nInterface.isLoopback() || ! nInterface.isUp()) {
                    continue ;
                }

                // For each addresses of the interface.
                while(addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement() ;

                    /*
                     * We are not using IPv6 addresses. It's a
                     * choice.
                     *
                     * || addr instanceof Inet6Address
                     */
                    if (addr instanceof Inet4Address) {
                        return InetAddress.getByName(addr.getHostAddress()) ;
                    }
                }
            }
        } catch (Exception e) {
            exception.initCause(e) ;
        }

        throw exception ;
    }

}
