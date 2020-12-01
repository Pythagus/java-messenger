package Messenger.Utils.Seeders.Concerns;

import java.util.Random;

/**
 * @author Damien MOLINA
 */
abstract public class Seeder {

    /**
     * How many items the seeder should
     * generate.
     */
    protected final int howMany ;

    /**
     * How many items the class
     * can generate.
     *
     * @return the number as a int.
     */
    abstract protected int maxItems() ;

    /**
     * Make a new seeder instance.
     *
     * @param howMany : how many items should the seeder contain.
     * @throws SeederException : too many items.
     */
    public Seeder(int howMany) throws SeederException {
        this.howMany = howMany ;

        int max = this.maxItems() ;

        if(howMany > max) {
            throw new SeederException(
                "Too many expected items (expected : " + howMany + ", max : " + max + ")"
            ) ;
        }
    }

    /**
     * Generate a random index between 0
     * and the max item.
     *
     * @return a random int.
     */
    protected int random() {
        return this.getRandomNumberInRange(0, this.maxItems() - 1) ;
    }

    /**
     * Generate a random int between min
     * and max.
     *
     * @param max : max random value.
     * @return a random int.
     */
    private int getRandomNumberInRange(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }

}
