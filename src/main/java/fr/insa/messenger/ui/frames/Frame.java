package fr.insa.messenger.ui.frames;

import java.awt.*;
import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import fr.insa.messenger.system.Env;
import fr.insa.messenger.system.assets.ImageAsset;
import fr.insa.messenger.network.NetworkInterface;
import fr.insa.messenger.network.models.basis.BroadcastType;

/**
 * @author Damien MOLINA
 */
abstract public class Frame extends JFrame {

    /**
     * Make a new Frame instance.
     *
     * @param title : frame title.
     */
    public Frame(String title) {
        this.setTitle(title + " - " + Env.getApplication().getName()) ;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
        this.setIconImage(ImageAsset.logoAsImageIcon().getImage()) ;
        this.addWindowListener(new CloseFrameHandler()) ;
    }

    /**
     * Render the frame.
     */
    public void render() {
        this.setVisible(true) ;
    }

    /**
     * Center the current frame instance.
     *
     * @param width : frame width
     * @param height : frame height
     */
    protected void center(int width, int height) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize() ;

        this.setMinimumSize(new Dimension(width, height)) ;
        this.setLocation(
            dim.width / 2 - width / 2, dim.height / 2 - height / 2
        ) ;
    }

    /**
     * Center the current frame instance.
     *
     * @param dimension : frame dimensions
     */
    protected void center(Dimension dimension) {
        this.center(dimension.width, dimension.height) ;
    }

    /**
     * Handler called when the frame
     * is closing.
     *
     * @author Damien MOLINA
     */
    class CloseFrameHandler extends WindowAdapter {
        /**
         * When the window is closing.
         *
         * @param event : caught event.
         */
        public void windowClosing(WindowEvent event) {
            int response = JOptionPane.showConfirmDialog(Frame.this,
                "Voulez-vous vraiment quitter l'application ?", "Fermer la fenêtre",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) ;

            if(response == JOptionPane.YES_OPTION) {
                NetworkInterface.instance().getEnvoyer().broadcast(BroadcastType.LOGOUT) ;
                System.exit(0) ;
            }
        }
    }

}
