package Messenger.Foundation.Models.DiscussionMessage;

import Messenger.Foundation.System.Assets.Sounds.SoundThread;

/**
 * @author Damien MOLINA
 */
public class DiscussionMessageSound {

    /**
     * Sound instance.
     */
    private static SoundThread thread ;

    /**
     * Play the sound only whether It is not
     * currently running.
     */
    public static void play() {
        if(DiscussionMessageSound.thread == null) {
            DiscussionMessageSound.initializeSound() ;
            DiscussionMessageSound.thread.start() ;
        }
    }

    /**
     * Initialize the static::thread object.
     */
    private static void initializeSound() {
        try {
            DiscussionMessageSound.thread = new SoundThread("new-message-sound.wav") ;
            DiscussionMessageSound.thread.onFinish(() -> DiscussionMessageSound.thread = null) ;
        } catch (Exception e) {
            e.printStackTrace() ;
        }
    }

}
