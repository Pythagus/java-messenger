package Messenger.gui.layout;

import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.awt.*;

/**
 * @author Damien MOLINA
 * @see "https://stackoverflow.com/a/12270067"
 */
public class uiDiscussionScrollBar extends BasicScrollBarUI {

    // Control button image.
    private final Image imageThumb ;

    // Control button image.
    private final Image imageTrack ;

    // Control image.
    private final JButton button ;

    /**
     * Create a custom scroll bar instance.
     */
    uiDiscussionScrollBar() {
        this.imageThumb = uiDiscussionScrollBar.createImage(TitleBar.backgroundColor) ;
        this.imageTrack = uiDiscussionScrollBar.createImage(uiDiscussion.backgroundColor) ;

        this.button = new JButton() ;
        this.button.setPreferredSize(new Dimension(0, 0)) ;
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
        g.drawImage(imageThumb, r.x, r.y, r.width, r.height, null) ;
    }

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle r) {
        g.drawImage(imageTrack, r.x, r.y, r.width, r.height, null) ;
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        return this.button ;
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return this.button ;
    }

    /**
     * Generate a virtual image only
     * used to paint the button.
     *
     * @param c : button color.
     * @return the Image instance.
     */
    private static Image createImage(Color c) {
        BufferedImage bi = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB) ;
        Graphics2D g2d = bi.createGraphics() ;
        g2d.setPaint(c) ;
        g2d.fillRect(0, 0, 32, 32) ;
        g2d.dispose() ;

        return bi ;
    }

}
