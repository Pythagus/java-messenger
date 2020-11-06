package Messenger.gui.screens;

import Messenger.gui.layout.*;
import javax.swing.*;
import java.awt.*;

/**
 * @author Damien MOLINA
 */
public class Window extends Screen {

    /**
     * Create a graphical instance
     * for the Messenger.
     */
    public Window() {
        this.initializeComponentGraphics() ;
    }

    /**
     * Initialize graphically the component.
     */
    private void initializeComponentGraphics() {
        JPanel container = new JPanel() ;
        container.setLayout(new BorderLayout()) ;

        // Add the components.
        container.add(new SideBar(), BorderLayout.WEST) ;
        container.add(new uiDiscussionBar(), BorderLayout.CENTER) ;

        this.setLayout(new BorderLayout()) ;
        this.add(container, BorderLayout.LINE_START) ;
        this.add(new RightSide(), BorderLayout.CENTER) ;
    }

}
