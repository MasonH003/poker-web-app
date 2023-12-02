import com.example.pokerwebapp.Card;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.example.pokerwebapp.Deck;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DeckTest {


    @Test
    public void properDeckSize()
    {
        Deck d = new Deck();
        assertEquals(52, d.remainingDeckSize());
    }

    @Test
    public void deckSizeWhenEmptyConstructorUsed()
    {
        Deck d = new Deck( true );
        assertEquals( 0, d.remainingDeckSize() );
    }

    @Test
    public void deckSortTest()
    {
        Deck d = new Deck( true );
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add( new Card( Card.Rank.TWO, Card.Suit.SPADES ) );
        cards.add( new Card( Card.Rank.ACE, Card.Suit.DIAMONDS ) );
        cards.add( new Card( Card.Rank.THREE, Card.Suit.HEARTS ) );
        d.setCards( cards );
        d.sortDeckByRank();
        //System.out.println( d.getDeck() );
        assertAll(
                ()->assertEquals( Card.Rank.ACE, d.getDeck().get(0).getCardRank() ),
                ()->assertEquals( Card.Rank.TWO, d.getDeck().get(1).getCardRank() ),
                ()->assertEquals( Card.Rank.THREE, d.getDeck().get(2).getCardRank() )
        );
    }

    @Test
    public void testCombineDecks() {
        Deck d1 = new Deck( true );
        Deck d2 = new Deck( true );

        ArrayList<Card> c1 = new ArrayList<>();
        ArrayList<Card> c2 = new ArrayList<>();

        c1.add( new Card( Card.Rank.TWO, Card.Suit.SPADES ) );
        c1.add( new Card( Card.Rank.FOUR, Card.Suit.DIAMONDS ));
        c2.add( new Card( Card.Rank.THREE, Card.Suit.HEARTS ));
        c2.add( new Card( Card.Rank.KING, Card.Suit.CLUBS ) );

        d1.setCards( c1 );
        d2.setCards( c2 );

        Deck d3 = Deck.combineDecks( d1, d2 );
        assertAll(
                ()->assertEquals( Card.Rank.TWO, d3.getDeck().get(0).getCardRank() ),
                ()->assertEquals( Card.Rank.FOUR, d3.getDeck().get(1).getCardRank() ),
                ()->assertEquals( Card.Rank.THREE, d3.getDeck().get(2).getCardRank() ),
                ()->assertEquals( Card.Rank.KING, d3.getDeck().get(3).getCardRank() ),
                ()->assertEquals( 4, d3.remainingDeckSize())
        );

    }

    @Test
    public void testRoyalFlush() {
        Deck d = new Deck( true );
        ArrayList<Card> cards = new ArrayList<>();
        cards.add( new Card( Card.Rank.ACE, Card.Suit.SPADES ) );
        cards.add( new Card( Card.Rank.JACK, Card.Suit.SPADES ));
        cards.add( new Card( Card.Rank.KING, Card.Suit.SPADES ));
        cards.add( new Card( Card.Rank.QUEEN, Card.Suit.SPADES ));
        cards.add( new Card( Card.Rank.TEN, Card.Suit.SPADES ));
        d.setCards( cards );
        assertTrue( d.hasRoyalFlush() );
    }

    @Test
    public void testNotRoyalFlush() {
        Deck d = new Deck( true );
        ArrayList<Card> cards = new ArrayList<>();
        cards.add( new Card( Card.Rank.ACE, Card.Suit.DIAMONDS ) );
        cards.add( new Card( Card.Rank.JACK, Card.Suit.SPADES ));
        cards.add( new Card( Card.Rank.KING, Card.Suit.SPADES ));
        cards.add( new Card( Card.Rank.QUEEN, Card.Suit.SPADES ));
        cards.add( new Card( Card.Rank.TEN, Card.Suit.SPADES ));
        d.setCards( cards );
        assertFalse( d.hasRoyalFlush() );
    }

    @Test
    public void testThreeOfAKind() {
        Deck d = new Deck( true );
        ArrayList<Card> cards = new ArrayList<>();
        cards.add( new Card( Card.Rank.ACE, Card.Suit.DIAMONDS ) );
        cards.add( new Card( Card.Rank.QUEEN, Card.Suit.HEARTS ));
        cards.add( new Card( Card.Rank.KING, Card.Suit.SPADES ));
        cards.add( new Card( Card.Rank.QUEEN, Card.Suit.SPADES ));
        cards.add( new Card( Card.Rank.QUEEN, Card.Suit.DIAMONDS ));
        d.setCards( cards );
        assertTrue( d.hasThreeOfAKind() );
    }

    @Test
    public void testNotThreeOfAKind() {
        Deck d = new Deck( true );
        ArrayList<Card> cards = new ArrayList<>();
        cards.add( new Card( Card.Rank.ACE, Card.Suit.DIAMONDS ) );
        cards.add( new Card( Card.Rank.QUEEN, Card.Suit.HEARTS ));
        cards.add( new Card( Card.Rank.KING, Card.Suit.SPADES ));
        cards.add( new Card( Card.Rank.JACK, Card.Suit.SPADES ));
        cards.add( new Card( Card.Rank.QUEEN, Card.Suit.DIAMONDS ));
        d.setCards( cards );
        assertFalse( d.hasThreeOfAKind() );
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
