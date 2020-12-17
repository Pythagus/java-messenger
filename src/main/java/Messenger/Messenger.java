package Messenger;

import Messenger.Foundation.Application;

/**
 * This is a list of the taken serialize
 * uid:
 * - 4242424242424242410L : MessageData
 * - 4242424242424242411L : Message
 * - 4242424242424242412L : MessageFile
 * - 4242424242424242424L : User
 * - 4242424242424242430L : Packet
 * - 4242424242424242431L : UserPacket
 * - 4242424242424242432L : MeetingPacket
 * - 4242424242424242433L : MessagePacket
 * - 4242424242424242434L : DataPacket
 *
 * @author Damien MOLINA
 */
public class Messenger extends Application {

    /**
     * The Messenger version.
     */
    private static final String VERSION = "1.0.0" ;

    /**
     * Make a new instance of Messenger.
     */
    public Messenger() {
        super() ;
    }

    /**
     * Get the current version of the
     * application.
     *
     * @return the application version.
     */
    public String getVersion() {
        return Messenger.VERSION ;
    }

}
