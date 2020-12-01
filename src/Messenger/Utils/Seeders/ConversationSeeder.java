package Messenger.Utils.Seeders;

import Messenger.Foundation.Models.User;
import Messenger.Utils.Seeders.Concerns.Seeder;
import Messenger.Foundation.Models.Conversation;
import Messenger.Utils.Seeders.Concerns.SeederException;

/**
 * @author Damien MOLINA
 */
public class ConversationSeeder extends Seeder {

    /**
     * Make a new seeder instance.
     *
     * @param howMany : how many items should the seeder contain.
     * @throws SeederException : too many items.
     */
    public ConversationSeeder(int howMany) throws SeederException {
        super(howMany) ;
    }

    /**
     * Get a conversations list.
     *
     * @param howMany : how many conversations the list should contain.
     * @return the Conversation list as an array.
     */
    public static Conversation[] get(int howMany) throws SeederException {
        return new ConversationSeeder(howMany).get() ;
    }

    /**
     * Get the conversations list.
     *
     * @return users array.
     */
    public Conversation[] get() throws SeederException {
        if(this.howMany <= 0) {
            return new Conversation[]{} ;
        }

        Conversation[] conversations = new Conversation[this.howMany] ;
        User[] users = UserSeeder.get(this.howMany) ;

        for(int i = 0 ; i < this.howMany ; i++) {
            Conversation conversation = new Conversation(users[i]) ;

            conversations[i] = conversation ;
        }

        return conversations ;
    }

    /**
     * How many items the class
     * can generate.
     *
     * @return the number as a int.
     */
    protected int maxItems() {
        return 10 ;
    }

}
