package Messenger.Foundation.Objects.DiscussionMessage;

import Messenger.Foundation.System.Sound.SoundThread;

/**
 * @author Damien MOLINA
 */
public class DiscussionMessageSound {

    /**
     * Sound instance.
     */
    private static SoundThread sound ;

    static {
        try {
            DiscussionMessageSound.sound = new SoundThread("new-message-sound.wav");
        } catch (Exception e) {
            e.printStackTrace() ;
        }
    }

    /**
     * Play the sound only whether It is not
     * currently running.
     */
    public static void play() {
        if(! DiscussionMessageSound.sound.isRunning()) {
            DiscussionMessageSound.sound.start() ;
        }
    }

}
