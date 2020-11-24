package Messenger.GUI.Layout;

import Messenger.GUI.Layout.Items.uiDiscussion;
import Messenger.GUI.Subscreens.uiSettings;
import Messenger.GUI.Subscreens.SubScreen;
import java.util.HashMap;
import javax.swing.*;

/**
 * @author Damien MOLINA
 */
public class RightSide extends JPanel {

    /**
     * Current subScreen.
     */
    private SubScreen subScreen ;

    /**
     * List of added subScreen.
     */
    private final HashMap<SubScreenType, SubScreen> subScreenList ;

    /**
     * Available subScreen types.
     */
    public enum SubScreenType {
        Discussion, Settings
    }

    /**
     * Make a new instance of the
     * right side panel.
     */
    public RightSide() {
        this.subScreenList = new HashMap<>() ;

        this.initializeComponentGraphics() ;
    }

    /**
     * Initialize graphically the component
     */
    private void initializeComponentGraphics() {
        this.setLayout(new OverlayLayout(this)) ;

        this.addSubScreen(SubScreenType.Discussion, new uiDiscussion()) ;
        this.addSubScreen(SubScreenType.Settings, new uiSettings()) ;

        /*
         * By default, the active screen is the
         * discussion one.
         */
        this.activeSubScreen(SubScreenType.Settings) ;
    }

    /**
     * Active the given subScreen.
     *
     * @param type : subScreen type.
     */
    public void activeSubScreen(SubScreenType type) {
        if(this.subScreen != null) {
            this.subScreen.setVisible(false) ;
        }

        this.subScreen = this.getSubScreen(type) ;
        this.subScreen.setVisible(true) ;
    }

    /**
     * Add the given subScreen.
     *
     * @param type : subScreen type.
     * @param subScreen : subScreen to add.
     */
    public void addSubScreen(SubScreenType type, SubScreen subScreen) {
        subScreen.setVisible(false) ;

        this.subScreenList.put(type, subScreen) ;
        this.add(subScreen) ;
    }

    /**
     * Get the SubScreen identified by the
     * given type.
     *
     * @param type : subScreen type.
     * @return a SubScreen instance.
     */
    public SubScreen getSubScreen(SubScreenType type) {
        return this.subScreenList.get(type) ;
    }

    /**
     * Get the current subScreen.
     *
     * @return the SubScreen instance.
     */
    public SubScreen getSubScreen() {
        return this.subScreen ;
    }

}
