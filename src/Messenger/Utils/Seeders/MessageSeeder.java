package Messenger.Utils.Seeders;

import Messenger.Foundation.Models.User;
import Messenger.Utils.Seeders.Concerns.Seeder;
import Messenger.Foundation.Models.Messages.Message;
import Messenger.Utils.Seeders.Concerns.SeederException;

/**
 * @author Damien MOLINA
 */
public class MessageSeeder extends Seeder {

    /**
     * User instance.
     */
    private final User user ;

    /**
     * Random messages.
     */
    private static final String[] messages = new String[] {
        "Bonjour, ça va ?",
        "Ouais, top, ça me va !",
        "Tu es sûr ?",
        "Je suis pas trop motivé là",
        "J'ai faim",
        "J'ai soif",
        "C'est bientôt l'heure",
        "C'est quand les vacances ?",
        "As-tu fait ton attestation ?",
        "C'est non",
        "Peut-être"
    } ;

    /**
     * Make a new seeder instance.
     *
     * @param howMany : how many items should the seeder contain.
     * @param user : user sending the message.
     * @throws SeederException : too many items.
     */
    public MessageSeeder(int howMany, User user) throws SeederException {
        super(howMany) ;

        this.user = user ;
    }

    /**
     * Get a messages list.
     *
     * @param howMany : how many messages the list should contain.
     * @param user : user sending the message.
     * @return the Message list as an array.
     */
    public static Message[] get(int howMany, User user) throws SeederException {
        return new MessageSeeder(howMany, user).get() ;
    }


    /**
     * Get the messages list.
     *
     * @return users array.
     */
    public Message[] get() {
        if(this.howMany <= 0) {
            return new Message[]{} ;
        }

        Message[] messages = new Message[this.howMany] ;

        for(int i = 0 ; i < this.howMany ; i++) {
            Message message = new Message(
                this.user, MessageSeeder.messages[i]
            ) ;

            messages[i] = message ;
        }

        return messages ;
    }

    /**
     * How many items the class
     * can generate.
     *
     * @return the number as a int.
     */
    @Override
    protected int maxItems() {
        return MessageSeeder.messages.length ;
    }

}
