package Messenger.GUI.Layout.Items.Discussion;

import Messenger.Foundation.Models.Conversation;
import Messenger.GUI.Layout.Items.uiVerticalBarItem;
import Messenger.Foundation.Models.Messages.MessageData;
import Messenger.GUI.Layout.Concerns.Actions.DiscussionItemClicked;

/**
 * @author Damien MOLINA
 */
public class uiDiscussionItem extends uiVerticalBarItem {

    // uiDiscussion identifier.
    private final Conversation conversation ;

    /**
     * Make a new graphic instance of conversation.
     *
     * @param conversation : displayed conversation.
     * @param picture : file name.
     */
    public uiDiscussionItem(Conversation conversation, String picture) {
        super(conversation.getTarget(), picture) ;

        this.conversation = conversation ;

        this.addMouseListener(new DiscussionItemClicked(this)) ;
    }

    /**
     * Make a new graphic instance of conversation.
     *
     * @param conversation : displayed conversation.
     */
    public uiDiscussionItem(Conversation conversation) {
        this(conversation, "francois_hollande.png") ;
    }

    /**
     * Update the content from the given data.
     *
     * @param data : data instance.
     */
    public void setContentFromData(MessageData data) {
        this.setContent(data.messageToPrint()) ;
    }

    /**
     * Get the conversation.
     *
     * @return Conversation instance.
     */
    public Conversation getConversation() {
        return this.conversation ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true ;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false ;
        }

        uiDiscussionItem that = (uiDiscussionItem) o ;

        return this.conversation.equals(that.getConversation()) ;
    }

}
