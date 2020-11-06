package Messenger.gui;

import Messenger.gui.screens.Screen;
import java.awt.*;

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
     * @param content : new screen.
     */
    public void setFrameScreen(Screen content) {
        if(this.frame == null) {
            this.frame = new Frame(content) ;
            this.frame.setVisible(true) ;
        } else {
            Component[] list = this.frame.getContentPane().getComponents() ;

            for(Component c : list) {
                if(c instanceof Screen) {
                    this.frame.remove(c) ;
                }
            }

            this.frame.add(content) ;
            this.frame.revalidate() ;
            this.frame.repaint() ;
        }
    }

}
