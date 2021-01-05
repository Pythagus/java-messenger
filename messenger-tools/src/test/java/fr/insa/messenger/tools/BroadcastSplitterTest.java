package fr.insa.messenger.tools;

import org.junit.Test;
import static org.junit.Assert.*;
import fr.insa.messenger.tools.network.BroadcastSplitter;

/**
 * @author Damien MOLINA
 */
public class BroadcastSplitterTest {

    /**
     * Sentence used in the tests. It is coupled
     * with the WORDS constant.
     */
    private static final String SENTENCE = "#Elle#a#fait#un#bébé#toute#seule#" ;

    /**
     * List of words used in the tests. It is coupled
     * with the SENTENCE constant.
     */
    private static final String[] WORDS = new String[] {
        "Elle", "a", "fait", "un", "bébé", "toute", "seule"
    } ;

    @Test
    public void join() {
        assertEquals(SENTENCE, BroadcastSplitter.join(WORDS)) ;
        assertEquals("", BroadcastSplitter.join()) ;
    }

    @Test
    public void split() {
        String sentence = String.join(" ", WORDS) ;

        assertArrayEquals(
            WORDS, BroadcastSplitter.split(SENTENCE).toArray()
        ) ;
        assertArrayEquals(
            new String[]{sentence}, BroadcastSplitter.split(sentence).toArray()
        ) ;
    }

}
