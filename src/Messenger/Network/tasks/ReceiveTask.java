package Messenger.Network.tasks;

import Messenger.Network.datagram.DatagramExchanger;

/**
 * @author Damien MOLINA
 */
public class ReceiveTask extends DatagramTask {

    /**
     * Make a new instance of the task.
     *
     * @param exchanger : datagram exchanger.
     */
    ReceiveTask(DatagramExchanger exchanger) {
        super(exchanger) ;
    }

    /**
     * Get the task executed in the loop.
     */
    @Override
    public void run() {
        try {
            while(true) {
                String data = (String) this.exchanger.receive() ;

                // TODO
                System.out.println(data) ;
            }
        } catch (Exception e) {
            // TODO
            e.printStackTrace();
        }
    }

}
