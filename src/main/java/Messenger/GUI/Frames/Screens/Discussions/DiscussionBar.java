package Messenger.GUI.Frames.Screens.Discussions;

import Messenger.GUI.Frames.MainFrame;
import Messenger.GUI.Frames.Screens.Utils.UserBar;
import Messenger.GUI.Frames.Screens.Utils.BarType;

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
