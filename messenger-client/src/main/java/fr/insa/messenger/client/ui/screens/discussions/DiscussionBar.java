package fr.insa.messenger.client.ui.screens.discussions;

import fr.insa.messenger.client.ui.frames.MainFrame;
import fr.insa.messenger.client.ui.screens.utils.UserBar;
import fr.insa.messenger.client.ui.screens.utils.BarType;

/**
 * @author Damien MOLINA
 */
public class DiscussionBar extends UserBar<DiscussionList> {

    /**
     * Make a contact bar instance.
     *
     * @param frame : main graphic frame.
     */
    public DiscussionBar(MainFrame frame) {
        super(BarType.DISCUSSION, frame, new DiscussionList(frame), "Mes discussions") ;
    }

}
