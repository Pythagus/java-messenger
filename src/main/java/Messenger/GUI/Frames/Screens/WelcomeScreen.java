package Messenger.GUI.Frames.Screens;

import java.awt.*;
import javax.swing.*;
import Messenger.Utils.ColorUtils;
import Messenger.Foundation.System.Env;
import Messenger.GUI.Factories.FontFactory;
import Messenger.Foundation.System.Assets.ImageAsset;
import Messenger.GUI.Frames.Screens.Utils.ContentType;
import Messenger.GUI.Frames.Screens.Utils.ContentScreen;

/**
 * @author Damien MOLINA
 */
public class WelcomeScreen extends ContentScreen {

    /**
     * Make a new screen instance.
     */
    public WelcomeScreen() {
        super(ContentType.WELCOME) ;

        this.initializeGraphics() ;
    }

    /**
     * Graphically initialize the screen.
     */
    private void initializeGraphics() {
        this.setLayout(new GridBagLayout()) ;

        // Application logo.
        JLabel logo = new JLabel() ;
        logo.setHorizontalAlignment(SwingConstants.CENTER) ;
        logo.setIcon(ImageAsset.logoAsImageIcon()) ;
        logo.setBorder(
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ) ;

        // Prepare the screen content.
        JPanel container = new JPanel() ;
        container.setBackground(ColorUtils.transparent()) ;
        container.setMinimumSize(new Dimension(500, 50)) ;
        container.setLayout(new BorderLayout()) ;

        // Add container content.
        container.add(logo, BorderLayout.PAGE_START) ;
        container.add(
            this.graphicSubtitle("Bienvenue sur " + Env.getApplication().getName(), 22), BorderLayout.CENTER
        ) ;
        container.add(
            this.graphicSubtitle("Converser avec vos collègues en cliquant sur une conversation", 14), BorderLayout.PAGE_END
        ) ;

        this.add(container) ;
    }

    /**
     * Generate the subtitle label.
     *
     * @param message : message to print.
     * @param size : message font size.
     * @return the JLabel created.
     */
    private JLabel graphicSubtitle(String message, int size) {
        JLabel subtitle = new JLabel("<html>" + message + "</html>") ;
        subtitle.setHorizontalAlignment(SwingConstants.CENTER) ;
        subtitle.setFont(FontFactory.normal(size)) ;
        subtitle.setBorder(
            BorderFactory.createEmptyBorder(0, 0, 5, 0)
        ) ;

        return subtitle ;
    }

}
