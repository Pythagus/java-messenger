package Messenger.network.tasks;

import Messenger.network.datagram.DatagramExchanger;

/**
 * @author Damien MOLINA
 */
public class SendTask extends DatagramTask {

    /**
     * Make a new instance of the task.
     *
     * @param exchanger : datagram exchanger.
     */
    SendTask(DatagramExchanger exchanger) {
        super(exchanger);
    }

    /**
     * Send the given data.
     *
     * @param data : data to send.
     */
    public void run(Object data) {
        if(data == null) {
            return ;
        }

        try {
            this.exchanger.send(data) ;
        } catch (Exception e) {
            // TODO
            e.printStackTrace();
        }
    }

    /**
     * Get the task executed in the loop.
     */
    @Override
    public void run() {
        this.run(null) ;
    }

}
