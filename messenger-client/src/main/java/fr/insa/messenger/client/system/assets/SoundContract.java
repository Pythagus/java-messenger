package fr.insa.messenger.client.system.assets;

/**
 * @author Damien MOLINA
 */
public interface SoundContract {

    /**
     * File root to access sounds.
     */
    String ASSET_FOLDER = "Sounds/" ;

    /**
     * Start the sound.
     */
    void start() ;

    /**
     * Stop the sound.
     */
    void stop() ;

    /**
     * Add the executed runnable when
     * the sound is finished.
     *
     * @param runnable : runnable to execute.
     */
    void onFinish(Runnable runnable) ;

}
