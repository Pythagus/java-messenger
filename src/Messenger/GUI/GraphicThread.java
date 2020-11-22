package Messenger.GUI;

import Messenger.GUI.Screens.Screen;

/**
 * @author Damien MOLINA
 */
public class GraphicThread extends Thread {

    /**
     * Main graphic frame.
     */
    private Frame frame ;

    /**
     * Set the frame screen regarding the
     * current frame instance.
     *
     * @param screen : new screen.
     */
    public void setFrameScreen(Screen screen) {
        if(this.frame == null) {
            this.frame = new Frame(screen) ;
            this.frame.setVisible(true) ;
        } else {
            this.frame.replaceScreen(screen) ;
        }
    }

    /**
     * get the graphic frame.
     *
     * @return the Frame instance.
     */
    public Frame getFrame() {
        return this.frame ;
    }

}
