package Messenger.GUI.Frames.Screens.Utils;

import javax.swing.JPanel;

/**
 * @author Damien MOLINA
 */
abstract public class CardPanel extends JPanel {

    /**
     * Make a card panel instance.
     *
     * @param name : card name.
     */
    public CardPanel(String name) {
        this.setName(name) ;
    }

}
