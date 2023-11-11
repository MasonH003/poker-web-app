import com.example.pokerwebapp.Card;
import com.example.pokerwebapp.CardEvaluate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class CardEvaluateTest {
    //royal flush
    Card[] hand1 ={new Card(Card.Rank.KING, Card.Suit.SPADES), new Card(Card.Rank.QUEEN, Card.Suit.SPADES),
            new Card(Card.Rank.JACK, Card.Suit.SPADES), new Card(Card.Rank.TEN, Card.Suit.SPADES), new Card(Card.Rank.ACE, Card.Suit.SPADES)};
    //four of a kind
    Card[] hand2 ={new Card(Card.Rank.ACE, Card.Suit.CLUBS), new Card(Card.Rank.FOUR, Card.Suit.HEARTS),
            new Card(Card.Rank.FOUR, Card.Suit.CLUBS), new Card(Card.Rank.FOUR, Card.Suit.DIAMONDS), new Card(Card.Rank.FOUR, Card.Suit.SPADES)};
    //full house
    Card[] hand3 ={new Card(Card.Rank.TEN, Card.Suit.HEARTS), new Card(Card.Rank.TEN, Card.Suit.CLUBS),
            new Card(Card.Rank.SIX, Card.Suit.HEARTS), new Card(Card.Rank.SIX, Card.Suit.SPADES), new Card(Card.Rank.SIX, Card.Suit.DIAMONDS)};
    //straight
    Card[] hand4 ={new Card(Card.Rank.KING, Card.Suit.SPADES), new Card(Card.Rank.JACK, Card.Suit.SPADES),
            new Card(Card.Rank.TEN, Card.Suit.SPADES), new Card(Card.Rank.TWO, Card.Suit.SPADES), new Card(Card.Rank.ACE, Card.Suit.SPADES)};
    //flush
    Card[] hand5 ={new Card(Card.Rank.KING, Card.Suit.SPADES), new Card(Card.Rank.QUEEN, Card.Suit.SPADES),
            new Card(Card.Rank.JACK, Card.Suit.CLUBS), new Card(Card.Rank.TEN, Card.Suit.SPADES), new Card(Card.Rank.ACE, Card.Suit.SPADES)};
    //three of a kind
    Card[] hand6 ={new Card(Card.Rank.SIX, Card.Suit.HEARTS), new Card(Card.Rank.SIX, Card.Suit.SPADES),
            new Card(Card.Rank.SIX, Card.Suit.DIAMONDS), new Card(Card.Rank.NINE, Card.Suit.HEARTS), new Card(Card.Rank.TEN, Card.Suit.CLUBS)};
    //two pair
    Card[] hand7 ={new Card(Card.Rank.SIX, Card.Suit.HEARTS), new Card(Card.Rank.FIVE, Card.Suit.SPADES),
            new Card(Card.Rank.SIX, Card.Suit.DIAMONDS), new Card(Card.Rank.TEN, Card.Suit.HEARTS), new Card(Card.Rank.TEN, Card.Suit.CLUBS)};
    //one pair
    Card[] hand8 ={new Card(Card.Rank.SIX, Card.Suit.HEARTS), new Card(Card.Rank.SIX, Card.Suit.SPADES),
            new Card(Card.Rank.TWO, Card.Suit.DIAMONDS), new Card(Card.Rank.NINE, Card.Suit.HEARTS), new Card(Card.Rank.TEN, Card.Suit.CLUBS)};
    //high card
    Card[] hand9 ={new Card(Card.Rank.KING, Card.Suit.CLUBS), new Card(Card.Rank.QUEEN, Card.Suit.HEARTS),
            new Card(Card.Rank.JACK, Card.Suit.DIAMONDS), new Card(Card.Rank.THREE, Card.Suit.HEARTS), new Card(Card.Rank.TWO, Card.Suit.CLUBS)};
    //straight flush non ace
    Card[] hand10 ={new Card(Card.Rank.KING, Card.Suit.SPADES), new Card(Card.Rank.QUEEN, Card.Suit.SPADES),
            new Card(Card.Rank.JACK, Card.Suit.SPADES), new Card(Card.Rank.TEN, Card.Suit.SPADES), new Card(Card.Rank.NINE, Card.Suit.SPADES)};

    CardEvaluate e = new CardEvaluate();

    //test sequential card checking, assume cards already sorted
    @Test
    public void isSequential()
    {
        assertAll(
                ()-> assertTrue(e.isSequential(hand1)),
                () -> assertFalse(e.isSequential(hand3)),
                () -> assertFalse(e.isSequential(hand4)),
                () -> assertTrue(e.isSequential(hand5)),
                () -> assertTrue(e.isSequential(hand10)));
    }

    //test whole of CardEvaluate
    @Test
    public void RankHand()
    {
        assertAll(
                ()-> assertEquals(9.141312111, e.RankHand(hand1)),
                ()-> assertEquals(9.1312111009, e.RankHand(hand10)),
                ()-> assertEquals(8.0404040401, e.RankHand(hand2)),
                ()-> assertEquals(7.1010060606, e.RankHand(hand3)),
                ()-> assertEquals(6.1311100201, e.RankHand(hand4)),
                ()-> assertEquals(5.1312111001, e.RankHand(hand5)),
                ()-> assertEquals(4.1009060606, e.RankHand(hand6)),
                ()-> assertEquals(3.1010060605, e.RankHand(hand7)),
                ()-> assertEquals(2.1009060602, e.RankHand(hand8)),
                ()-> assertEquals(1.1312110302, e.RankHand(hand9)));
    }

    //test decimal representation of cards, assume cards already sorted
    @Test
    public void BuildHand()
    {
        assertAll(
                ()-> assertEquals(0.1312111001, e.buildRank(hand1)),
                ()-> assertEquals(0.1312111009, e.buildRank(hand10)),
                ()-> assertEquals(0.1312110302, e.buildRank(hand9)));
    }
}
