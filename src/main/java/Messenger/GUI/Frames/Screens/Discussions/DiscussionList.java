package Messenger.GUI.Frames.Screens.Discussions;

import Messenger.GUI.Frames.MainFrame;
import Messenger.Foundation.Models.User;
import Messenger.GUI.Frames.Screens.Utils.UserList;

/**
 * @author Damien MOLINA
 */
public class DiscussionList extends UserList {

    /**
     * Make a discussion list instance.
     *
     * @param frame : main frame.
     */
    public DiscussionList(MainFrame frame) {
        super(frame) ;
    }

    /**
     * Make a new item instance regarding
     * the given user instance.
     *
     * @param user : user instance.
     */
    protected DiscussionListItem makeItem(User user) {
        return new DiscussionListItem(this, user) ;
    }

}
