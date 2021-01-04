package fr.insa.messenger.client.network.listeners;

import java.net.Socket;
import fr.insa.messenger.client.system.console.Console;
import fr.insa.messenger.client.network.models.MeetingPacket;
import fr.insa.messenger.client.network.streams.InputSocketStream;
import fr.insa.messenger.client.network.listeners.handlers.DeniedHandler;
import fr.insa.messenger.client.network.listeners.handlers.AcceptedHandler;

/**
 * @author Damien MOLINA
 */
public class MeetingResponseListener extends NetworkBaseListener<Socket> {

    /**
     * Runnable called when the packet is
     * accepted.
     */
    private AcceptedHandler callbackOnAccepted ;

    /**
     * Runnable called when the packet is
     * accepted.
     */
    private DeniedHandler callbackOnDenied ;

    /**
     * Make a new listener instance.
     *
     * @param socket : listening socket.
     */
    public MeetingResponseListener(Socket socket) {
        super(socket) ;
    }

    /**
     * Set the runnable executed when the
     * packet has been accepted or denied.
     *
     * @param accepted : accepted runnable.
     * @param denied : denied runnable.
     */
    public void setCallbacks(AcceptedHandler accepted, DeniedHandler denied) {
        this.callbackOnAccepted = accepted ;
        this.callbackOnDenied   = denied ;
    }

    /**
     * Run the listener.
     */
    public void run() {
        try {
            InputSocketStream exchanger = new InputSocketStream(this.listenerSocket) ;
            MeetingPacket packet = (MeetingPacket) exchanger.receive() ;

            this.managePacket(packet) ;

            this.listenerSocket.close() ;
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Manage the received packet instance.
     *
     * @param packet : received packet.
     */
    private void managePacket(MeetingPacket packet) {
        switch (packet.getState()) {

            /*
             * The request was accepted. We can now
             * contact the other user in the correct
             * port.
             */
            case ACCEPTED: this.manageAcceptedPacket(packet) ; break ;

            /*
             * The request was refused. We need to remove
             * the packet from the temporary list.
             */
            case DENIED: this.manageDeniedPacket(packet) ; break ;
        }
    }

    /**
     * Manage an accepted packet.
     *
     * @param packet : accepted packet instance.
     */
    private void manageAcceptedPacket(MeetingPacket packet) {
        Console.comment("=> Meeting response packet accepted from " + packet.getSourceAddress()) ;

        if(this.callbackOnAccepted != null) {
            this.callbackOnAccepted.handle(packet.getSourceUser()) ;
        }
    }

    /**
     * Manage an denied packet.
     *
     * @param packet : denied packet instance.
     */
    private void manageDeniedPacket(MeetingPacket packet) {
        Console.comment("=> Denied packet from " + packet.getSourceAddress()) ;

        if(this.callbackOnDenied != null) {
            this.callbackOnDenied.denied() ;
        }
    }

}
