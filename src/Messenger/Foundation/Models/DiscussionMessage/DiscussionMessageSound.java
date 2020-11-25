package Messenger.Foundation.Models.DiscussionMessage;

import Messenger.Foundation.System.Assets.Sounds.SoundThread;

/**
 * @author Damien MOLINA
 */
public class DiscussionMessageSound {

    /**
     * SoundAsset instance.
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
