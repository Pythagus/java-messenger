package Messenger.GUI.Frames.Screens.Utils;

import java.awt.*;

/**
 * @author Damien MOLINA
 */
abstract public class ContentScreen extends CardPanel {

    // Main screen color.
    public static final Color backgroundColor = Color.WHITE ;

    /**
     * Make a new screen instance.
     */
    public ContentScreen(ContentType type) {
        super(type.toString()) ;

        this.setBackground(ContentScreen.backgroundColor) ;
    }

}
