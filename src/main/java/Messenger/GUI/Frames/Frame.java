package Messenger.GUI.Frames;

import java.awt.*;
import javax.swing.*;
import Messenger.Foundation.System.Assets.ImageAsset;

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
        this.setTitle(title) ;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
        this.setIconImage(ImageAsset.logoAsImageIcon().getImage()) ;
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

}
