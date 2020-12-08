package Messenger.GUI.Layout.Items.Discussion;

import java.awt.*;
import javax.swing.*;
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
     * @param picture : file name.
     */
    public uiDiscussionItem(Conversation conversation, String picture) {
        super(picture) ;

        this.conversation = conversation ;

        this.addMouseListener(new DiscussionItemClicked(this)) ;
    }

    /**
     * Update the content from the given data.
     *
     * @param data : data instance.
     */
    public void setContentFromData(MessageData data) {
        if(data.hasFile()) {
            this.setContent("Vous avez envoyé un fichier") ;
        } else {
            this.setContent(data.getText()) ;
        }
    }

    /**
     * Get the conversation.
     *
     * @return Conversation instance.
     */
    public Conversation getConversation() {
        return this.conversation ;
    }

    /**
     * Update the item content graphically.
     *
     * @param content : panel to update.
     * @return the updated panel.
     */
    protected JPanel graphicItemContent(JPanel content) {
        content = super.graphicItemContent(content) ;
        content.add(this.contentLabel) ;

        return content ;
    }

    /**
     * Generate the name label.
     *
     * @return the JLabel generated.
     */
    private JLabel graphicNameLabel() {
        JLabel label = new JLabel() ;
        label.setText(this.conversation.getTarget().getPseudo()) ;

        Font f = new Font(null, Font.PLAIN, 14) ;
        label.setFont(f.deriveFont(Font.BOLD)) ;

        return label ;
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
