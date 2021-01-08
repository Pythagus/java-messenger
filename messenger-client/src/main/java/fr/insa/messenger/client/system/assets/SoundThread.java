package fr.insa.messenger.client.system.assets;

/**
 * @author Damien MOLINA
 */
public class SoundThread implements SoundContract {

    /**
     * Thread executing the sound.
     */
    private final MySoundThread thread ;

    /**
     * Make a new instance of a sound
     * thread manager.
     *
     * @param file : file
     * @throws Exception : sound generation error.
     */
    public SoundThread(String file) throws Exception {
        this.thread = new MySoundThread(new Sound(file)) ;
    }

    /**
     * Start the sound.
     */
    public void start() {
        this.thread.start() ;
    }

    /**
     * Stop the sound.
     */
    public void stop() {
        this.thread.exit() ;
    }

    /**
     * Add the executed runnable when
     * the sound is finished.
     *
     * @param runnable : runnable to execute.
     */
    public void onFinish(Runnable runnable) {
        this.thread.getSound().onFinish(runnable) ;
    }

    /**
     * Determine whether the thread is running.
     *
     * @return True or False.
     */
    public boolean isRunning() {
        return this.thread.isRunning() ;
    }

    /**
     * Thread executing the sound.
     */
    static class MySoundThread extends Thread {

        /**
         * Sound instance.
         */
        private final Sound sound ;

        /**
         * Thread state.
         */
        private volatile boolean running ;

        /**
         * Make a new thread instance.
         *
         * @param sound : sound instance.
         */
        MySoundThread(Sound sound) {
            this.sound   = sound ;
            this.running = false ;

            /*
             * When the sound is finished,
             * we stop the thread.
             */
            this.sound.onFinish(MySoundThread.this::exit);
        }

        /**
         * Run the thread.
         */
        public void run() {
            this.sound.start() ;
            this.running = true ;

            while(this.running) ;

            this.sound.stop() ;
        }

        /**
         * Get the sound instance.
         *
         * @return the sound instance.
         */
        public Sound getSound() {
            return this.sound ;
        }

        /**
         * Stop the thread.
         */
        public void exit() {
            this.running = false ;
        }

        public boolean isRunning() {
            return this.running ;
        }

    }

}
