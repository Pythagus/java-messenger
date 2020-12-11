package Messenger.GUI.Layout.Items.Discussion;

import Messenger.Foundation.Models.Conversation;
import Messenger.Foundation.Models.User;
import Messenger.GUI.Layout.Items.uiVerticalBar;
import Messenger.Foundation.Models.Messages.MessageData;
import Messenger.GUI.Exceptions.ConversationItemNotFoundException;

/**
 * @author Damien MOLINA
 */
public class uiDiscussionBar extends uiVerticalBar<uiDiscussionItem> {

    /**
     * Make a new instance of the conversation bar.
     */
    public uiDiscussionBar() {
        super("Discussions") ;
    }

    /**
     * Determine whether the given items
     * are equal.
     *
     * @param o1 : first item.
     * @param o2 : second item.
     * @return True or False.
     */
    protected boolean equalItems(uiDiscussionItem o1, uiDiscussionItem o2) {
        return o1.getConversation().equals(o2.getConversation()) ;
    }

    /**
     * Determine whether the given item
     * was made by the given user.
     *
     * @param o1 : first item.
     * @param user : user instance.
     * @return True or False.
     */
    protected boolean fromUser(uiDiscussionItem o1, User user) {
        return o1.getConversation().getTarget().equals(user) ;
    }

    /**
     * Update the active item.
     *
     * @param data : sent data.
     */
    public void updateActiveItem(MessageData data) {
        try {
            this.getActiveItem().setContentFromData(data) ;
        } catch (ConversationItemNotFoundException e) {
            e.printStackTrace() ;
        }
    }

    /**
     * Update the item from the user.
     *
     * @param user : user sending the data.
     * @param data : data sent.
     */
    public void updateFromUser(User user, MessageData data) {
        try {
            uiDiscussionItem item = this.getFromUser(user) ;
            item.setContentFromData(data) ;
        } catch (ConversationItemNotFoundException e) {
            uiDiscussionItem item = new uiDiscussionItem(new Conversation(user)) ;
            item.setContentFromData(data) ;

            this.addItem(item) ;
        }
    }

}
