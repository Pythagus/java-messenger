package fr.insa.messenger.ui.screens.discussions;

import fr.insa.messenger.ui.frames.MainFrame;
import fr.insa.messenger.ui.screens.utils.UserBar;
import fr.insa.messenger.ui.screens.utils.BarType;

/**
 * @author Damien MOLINA
 */
public class DiscussionBar extends UserBar {

    /**
     * Make a contact bar instance.
     *
     * @param frame : main graphic frame.
     */
    public DiscussionBar(MainFrame frame) {
        super(BarType.DISCUSSION, frame, new DiscussionList(frame), "Mes discussions") ;
    }

}
