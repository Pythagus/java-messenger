package Messenger.Utils.Seeders;

import Messenger.Foundation.Models.User;
import Messenger.Utils.Seeders.Concerns.Seeder;
import Messenger.Utils.Seeders.Concerns.SeederException;

/**
 * @author Damien MOLINA
 */
public class UserSeeder extends Seeder {

    /**
     * Random pseudos.
     */
    private static final String[] pseudos = new String[] {
        "AndréLPB", "AlbertAuxFinesHerbes", "AlfredEtBatman", "BobLeBricoleur", "BrunoLalcolo", "CharlesX", "CarotteDu69",
        "JaimeLesPommesDeTerre", "JamyEtSonCamion", "RouladeDePrintemps"
    } ;

    /**
     * Get the users list.
     *
     * @param howMany : how many users the list should contain.
     * @return the User list as an array.
     */
    public static User[] get(int howMany) throws SeederException {
        return new UserSeeder(howMany).get() ;
    }

    /**
     * Get the users list.
     *
     * @return users array.
     */
    public User[] get() {
        if(this.howMany <= 0) {
            return new User[]{} ;
        }

        User[] users = new User[this.howMany] ;

        for(int i = 0 ; i < this.howMany ; i++) {
            User user = new User() ;
            user.setPseudo(UserSeeder.pseudos[i]) ;

            users[i] = user ;
        }

        return users ;
    }

    /**
     * How many items the class
     * can generate.
     *
     * @return the number as a int.
     */
    @Override
    protected int maxItems() {
        return UserSeeder.pseudos.length ;
    }

    /**
     * Make a new UserSeeder
     *
     * @param howMany : how many items should the seeder contain.
     */
    public UserSeeder(int howMany) throws SeederException {
        super(howMany) ;
    }

}
