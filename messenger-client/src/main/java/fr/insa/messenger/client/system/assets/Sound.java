package fr.insa.messenger.client.system.assets;

import javax.sound.sampled.*;

/**
 * @author Damien MOLINA
 */
public class Sound implements SoundContract {

    /**
     * Clip sound instance.
     */
    private final Clip clip ;

    /**
     * Sound thread instance.
     */
    private static SoundThread thread ;

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
     * Play the given sound.
     *
     * @param file : sound file.
     */
    public static void play(String file) {
        if(Sound.thread == null) {
            try {
                Sound.thread = new SoundThread(file) ;
                Sound.thread.onFinish(() -> Sound.thread = null) ;
                Sound.thread.start() ;
            } catch (Exception e) {
                e.printStackTrace() ;
                Sound.thread = null ;
            }
        }
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
