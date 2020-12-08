package Messenger.GUI.Screens;

import java.awt.*;
import javax.swing.*;
import java.util.HashMap;
import Messenger.GUI.Layout.*;
import Messenger.GUI.Layout.Items.uiVerticalBar;
import Messenger.GUI.Layout.Concerns.VerticalBarType;
import Messenger.GUI.Layout.Items.Contact.uiContactBar;
import Messenger.GUI.Layout.Items.Discussion.uiDiscussionBar;

/**
 * @author Damien MOLINA
 */
public class uiWindow extends Screen {

    /**
     * Right side instance.
     */
    private final RightSide rightSide ;

    /**
     * List of added subScreen.
     */
    private final HashMap<VerticalBarType, uiVerticalBar<?>> verticalBars ;

    /**
     * Active bar.
     */
    private uiVerticalBar<?> bar ;

    /**
     * Bar container panel.
     */
    private final JPanel container;

    /**
     * Create a graphical instance for the application.
     */
    public uiWindow() {
        this.rightSide = new RightSide() ;
        this.container = this.graphicContainer() ;

        // Set the vertical bars.
        this.verticalBars = new HashMap<>() ;
        this.verticalBars.put(VerticalBarType.DISCUSSION, new uiDiscussionBar()) ;
        this.verticalBars.put(VerticalBarType.CONTACT, new uiContactBar()) ;

        this.initializeComponentGraphics() ;
    }

    /**
     * Initialize graphically the component.
     */
    private void initializeComponentGraphics() {
        // Add the components.
        this.container.add(new SideBar(this.rightSide), BorderLayout.WEST) ;
        this.addVerticalBar(VerticalBarType.CONTACT, new uiContactBar(), BorderLayout.CENTER) ;
        this.addVerticalBar(VerticalBarType.DISCUSSION, new uiDiscussionBar(), BorderLayout.EAST) ;

        // By default, active the discussion bar.
        this.activeBar(VerticalBarType.DISCUSSION) ;

        this.setLayout(new BorderLayout()) ;
        this.add(this.container, BorderLayout.LINE_START) ;
        this.add(this.rightSide, BorderLayout.CENTER) ;
    }

    /**
     * Create the bar container.
     *
     * @return the container instance.
     */
    private JPanel graphicContainer() {
        JPanel container = new JPanel() ;
        container.setLayout(new BorderLayout()) ;

        return container ;
    }

    /**
     * Add the vertical bar to the group.
     *
     * @param type : bar type.
     * @param bar : bar instance.
     */
    private void addVerticalBar(VerticalBarType type, uiVerticalBar<?> bar, String t) {
        bar.setVisible(false) ;

        this.container.add(bar, t) ;
        this.verticalBars.put(type, bar) ;
    }

    /**
     * Get the right side instance.
     *
     * @return the right side instance.
     */
    public RightSide getRightSide() {
        return this.rightSide ;
    }

    /**
     * Get the vertical bar instance.
     *
     * @return the vertical bar instance.
     */
    public uiVerticalBar<?> getVerticalBar(VerticalBarType type) {
        return this.verticalBars.get(type) ;
    }

    /**
     * Active the given type bar.
     *
     * @param type : bar type.
     */
    public void activeBar(VerticalBarType type) {
        if(this.bar != null) {
            this.bar.setVisible(false) ;
        }

        this.bar = this.getVerticalBar(type) ;
        this.bar.setVisible(true) ;
    }

}
