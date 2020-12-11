package Messenger.Foundation.Models.Messages;

import Messenger.Foundation.System.Assets.Sounds.SoundThread;

/**
 * @author Damien MOLINA
 */
public class MessageSound {

    /**
     * Sound instance.
     */
    private static SoundThread thread ;

    /**
     * Play the sound only whether It is not
     * currently running.
     */
    public static void play() {
        if(MessageSound.thread == null) {
            MessageSound.initializeSound() ;
            MessageSound.thread.start() ;
        }
    }

    /**
     * Initialize the static::thread object.
     */
    private static void initializeSound() {
        try {
            MessageSound.thread = new SoundThread("new-message-sound.wav") ;
            MessageSound.thread.onFinish(() -> MessageSound.thread = null) ;
        } catch (Exception e) {
            e.printStackTrace() ;
        }
    }

}
