package Messenger;

import Messenger.GUI.Screens.Screen;
import Messenger.GUI.Screens.uiLogin;
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
 *
 * @author Damien MOLINA
 */
public class Messenger extends Application {

    /**
     * The Messenger version.
     */
    private static final String VERSION = "1.0.0" ;

    /**
     * The application name.
     */
    private static final String NAME = "Java Messenger" ;

    /**
     * Env application mode.
     */
    private static final ApplicationMode mode = ApplicationMode.DEBUG ;

    /**
     * Make a new instance of Messenger.
     */
    public Messenger() {
        super(Messenger.mode) ;
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

    /**
     * Get the current application's name.
     *
     * @return the application name.
     */
    public String getName() {
        return Messenger.NAME ;
    }

    /**
     * Get the screen instance displayed when
     * the application is started.
     *
     * @return a Screen instance.
     */
    protected Screen getStartingScreen() {
        return new uiLogin() ;
    }

}
