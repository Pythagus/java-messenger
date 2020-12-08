package Messenger.GUI.Subscreens;

import java.awt.*;
import javax.swing.*;
import Messenger.Foundation.System.Assets.ImageAsset;
import Messenger.Foundation.System.Env;

/**
 * @author Damien MOLINA
 */
public class uiWelcome extends SubScreen {

    /**
     * Make a new instance of the
     * uiWelcome sub screen.
     */
    public uiWelcome() {
        this.initializeComponentGraphics() ;
    }

    /**
     * Initialize graphically the component
     */
    private void initializeComponentGraphics() {
        this.setEnabled(false) ;
        this.setLayout(new GridBagLayout()) ;

        // Add container content.
        this.add(this.graphicContainer(), new GridBagConstraints()) ;
    }

    /**
     * Generate the container panel.
     *
     * @return the JPanel created.
     */
    private JPanel graphicContainer() {
        JPanel container = new JPanel() ;
        container.setBackground(null) ;
        container.setMinimumSize(new Dimension(500, 50)) ;
        container.setLayout(new BorderLayout()) ;

        // Add container content.
        container.add(this.graphicLogo(), BorderLayout.PAGE_START) ;
        container.add(
            this.graphicSubtitle("Bienvenue sur " + Env.getApplication().getName(), 18), BorderLayout.CENTER
        ) ;
        container.add(
            this.graphicSubtitle("Converser avec vos collègues en cliquant sur une conversation", 12), BorderLayout.PAGE_END
        ) ;

        return container ;
    }

    /**
     * Generate the logo label.
     *
     * @return the JLabel created.
     */
    private JLabel graphicLogo() {
        JLabel logo = new JLabel() ;
        logo.setHorizontalAlignment(SwingConstants.CENTER) ;
        logo.setIcon(ImageAsset.logoAsImageIcon()) ;
        logo.setBorder(
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ) ;

        return logo ;
    }

    /**
     * Generate the subtitle label.
     *
     * @param message : message to print.
     * @param size : message font size.
     * @return the JLabel created.
     */
    private JLabel graphicSubtitle(String message, int size) {
        JLabel subtitle = new JLabel() ;
        subtitle.setHorizontalAlignment(SwingConstants.CENTER) ;
        subtitle.setText("<html>" + message + "</html>") ;

        Font f = new Font(null, Font.PLAIN, size) ;
        subtitle.setFont(f.deriveFont(Font.PLAIN)) ;

        return subtitle ;
    }

}
