package Messenger.GUI.Frames.Screens.Contacts;

import Messenger.Foundation.Models.User;
import Messenger.GUI.Frames.Screens.Utils.BarType;
import Messenger.GUI.Frames.Screens.Utils.ContentType;
import Messenger.GUI.Frames.Screens.Utils.UserListItem;
import Messenger.Foundation.Controllers.ConversationController;

/**
 * @author Damien MOLINA
 */
public class ContactListItem extends UserListItem {

    /**
     * Make a new UserListItem instance.
     *
     * @param list : parent list.
     * @param user : user instance.
     */
    public ContactListItem(ContactList list, User user) {
        super(list, user) ;
    }

    /**
     * When the item is selected.
     */
    public void selected() {
        this.list.getFrame().showBar(BarType.DISCUSSION) ;
        this.list.getFrame().showContent(ContentType.DISCUSSION) ;

        // Add the current item to the discussion list.
        // TODO : do it in a listener waiting for server return.
        ConversationController.instance().addConversation(this.user) ;
        this.list.getFrame().getBar(BarType.DISCUSSION).getList().addItem(this.user, true) ;

        // Remove the current item from the list.
        this.list.removeItem(this) ;
        this.list.clearSelection() ;
    }

}
