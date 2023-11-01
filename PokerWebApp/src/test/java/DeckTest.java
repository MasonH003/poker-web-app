import com.example.pokerwebapp.Card;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.example.pokerwebapp.Deck;
public class DeckTest {


    @Test
    public void properDeckSize()
    {
        Deck d = new Deck();
        assertEquals(52, d.remainingDeckSize());
    }

    //Assure that the card that is dealt is equal to the first card of the deck
    @Test
    public void dealCard()
    {
        Deck d = new Deck();
        d.dealCard();
        assertEquals(d.getDeck().get(0), d.dealCard());
    }


    //weird bug in this test, assertEquals is not properly comparing the decks
    @Test
    public void shuffledDeck()
    {
        Deck d = new Deck();

        Deck shuffled = new Deck();
        shuffled.shuffleDeck();
        assertNotEquals(d, shuffled);
    }
    @Test
    public void resetDeckTest()
    {
        Deck d = new Deck();
        Deck d1 = new Deck();
        d1.resetDeck();
        assertNotEquals(d, d1);


    }


    //test to test the toString method on a non shuffled deck
    @Test
    public void testToString()
    {
        Deck d = new Deck();
        String expectedString = "ACE OF SPADES TWO OF SPADES THREE OF SPADES FOUR OF SPADES FIVE OF SPADES SIX OF SPADES SEVEN OF SPADES EIGHT OF SPADES NINE OF SPADES TEN OF SPADES JACK OF SPADES QUEEN OF SPADES KING OF SPADES ACE OF HEARTS TWO OF HEARTS THREE OF HEARTS FOUR OF HEARTS FIVE OF HEARTS SIX OF HEARTS SEVEN OF HEARTS EIGHT OF HEARTS NINE OF HEARTS TEN OF HEARTS JACK OF HEARTS QUEEN OF HEARTS KING OF HEARTS ACE OF DIAMONDS TWO OF DIAMONDS THREE OF DIAMONDS FOUR OF DIAMONDS FIVE OF DIAMONDS SIX OF DIAMONDS SEVEN OF DIAMONDS EIGHT OF DIAMONDS NINE OF DIAMONDS TEN OF DIAMONDS JACK OF DIAMONDS QUEEN OF DIAMONDS KING OF DIAMONDS ACE OF CLUBS TWO OF CLUBS THREE OF CLUBS FOUR OF CLUBS FIVE OF CLUBS SIX OF CLUBS SEVEN OF CLUBS EIGHT OF CLUBS NINE OF CLUBS TEN OF CLUBS JACK OF CLUBS QUEEN OF CLUBS KING OF CLUBS ";
        String actual = d.toString();
        assertEquals(expectedString, actual);
    }





}
