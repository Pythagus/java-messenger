package fr.insa.messenger.client.models;

import java.util.ArrayList;

/**
 * Describe 1 discussion between 2 users.
 *
 * @author Maud PENNETIER
 */
public class Conversation {

    /**
     * Targeted user.
     */
    private final User target ;

    /**
     * Messages list.
     */
    private final ArrayList<AbstractMessage<?>> messages ;

    /**
     * Make a new instance of conversation.
     *
     * @param user : user we are discussing to.
     */
    public Conversation(User user) {
        this.target   = user ;
        this.messages = new ArrayList<>() ;
    }

    /**
     * Get the user target of this conversation.
     *
     * @return the User instance.
     */
    public User getTarget()
    {
        return this.target;
    }

    /**
     * Get the messages' list.
     *
     * @return the list of messages.
     */
    public ArrayList<AbstractMessage<?>> getMessages() {
        return this.messages ;
    }

    /**
     * Add a new message into the list.
     *
     * @param message : the message instance.
     */
    public void addMessage(AbstractMessage<?> message) {
        this.messages.add(message) ;
    }

    /**
     * Add a new message into the list.
     *
     * @param messages : messages list instance.
     */
    public void addMessages(Iterable<AbstractMessage<?>> messages) {
        for(AbstractMessage<?> message : messages) {
            this.addMessage(message) ;
        }
    }

    /**
     * Get the conversation title.
     *
     * @return the conversation title.
     */
    public String getTitle() {
        return this.target.getPseudo() ;
    }

    /**
     * Override the default equals method.
     * Two conversations are equals only if they have
     * the same target.
     *
     * @param o : conversation to compare with.
     * @return True if they are the same, False otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true ;
        }

        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        Conversation that = (Conversation) o ;

        return this.target.equals(that.getTarget()) ;
    }

}
