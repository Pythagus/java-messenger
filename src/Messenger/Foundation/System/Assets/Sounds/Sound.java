package Messenger.Foundation.System.Assets.Sounds;

import javax.sound.sampled.*;
import Messenger.Foundation.System.Assets.Asset;

/**
 * @author Damien MOLINA
 */
public class Sound implements SoundContract {

    /**
     * Clip sound instance.
     */
    private final Clip clip ;

    /**
     * Make a new instance of Sound.
     * Supported formats : WAV, AIFF and AU.
     *
     * @param file : file to play.
     * @throws Exception : file stream errors.
     */
    public Sound(String file) throws Exception {
        this.clip = AudioSystem.getClip() ;

        this.openStream(file) ;
    }

    /**
     * Open the sound stream.
     *
     * @param file : sound file link.
     * @throws Exception : file stream errors.
     */
    private void openStream(String file) throws Exception {
        AudioInputStream stream = AudioSystem.getAudioInputStream(
            Asset.resourceAsStream(ASSET_FOLDER + file)
        ) ;

        this.clip.open(stream) ;
    }

    /**
     * Start the sound.
     */
    public void start() {
        this.clip.start() ;
    }

    /**
     * Stop the sound.
     */
    public void stop() {
        this.clip.stop() ;
    }

    /**
     * Add the executed runnable when
     * the sound is finished.
     *
     * @param runnable : runnable to execute.
     */
    public void onFinish(Runnable runnable) {
        this.clip.addLineListener(e -> {
            if (e.getType() == LineEvent.Type.STOP) {
                runnable.run() ;
            }
        }) ;
    }

}
