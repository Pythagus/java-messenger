package fr.insa.messenger.ui.screens.discussions;

import fr.insa.messenger.models.User;
import fr.insa.messenger.ui.frames.MainFrame;
import fr.insa.messenger.ui.screens.utils.UserList;

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
