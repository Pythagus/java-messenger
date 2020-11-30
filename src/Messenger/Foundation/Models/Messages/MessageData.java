package Messenger.Foundation.Models.Messages;

/**
 * Message content : string, file, etc.
 *
 * @author Damien MOLINA
 */
public class MessageData {

    /**
     * Message text.
     */
    private final String text ;

    /**
     * Sent file.
     */
    private final MessageFile file ;

    /**
     * Make a new MessageData instance.
     *
     * @param text : message text.
     * @param file : message file.
     */
    public MessageData(String text, MessageFile file) {
        this.text = text ;
        this.file = file ;
    }

    /**
     * Get the message text.
     *
     * @return the text message.
     */
    public String getText() {
        return this.text ;
    }

    /**
     * Get the message text.
     *
     * @return the text message.
     */
    public MessageFile getFile() {
        return this.file ;
    }

}
