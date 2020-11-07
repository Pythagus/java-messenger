package Messenger.Network.tasks;

import Messenger.Network.datagram.DatagramExchanger;

/**
 * @author Damien MOLINA
 */
abstract class DatagramTask implements Runnable {

    /**
     * Datagram exchanger to manage the incoming
     * packet.
     */
    protected final DatagramExchanger exchanger ;

    /**
     * Make a new datagram task instance.
     *
     * @param exchanger : datagram exchanger.
     */
    protected DatagramTask(DatagramExchanger exchanger) {
        this.exchanger = exchanger;
    }

}
