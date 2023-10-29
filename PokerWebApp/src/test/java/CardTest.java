import com.example.pokerwebapp.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CardTest {

    Card c = new Card(Card.Rank.TWO, Card.Suit.SPADES);
    Card c1 = new Card(Card.Rank.FOUR, Card.Suit.CLUBS);
    Card c2 = new Card(Card.Rank.KING, Card.Suit.DIAMONDS);


    @Test
    public void cardRank()
    {
        assertAll(
                ()-> assertEquals(Card.Rank.TWO, c.getCardRank()),
                () -> assertEquals(Card.Rank.FOUR, c1.getCardRank()),
                () -> assertEquals(Card.Rank.KING, c2.getCardRank()));
    }
    @Test
    public void cardRankValue()
    {

        assertEquals(2, c.getRankValue());
        assertEquals(4, c1.getRankValue());
        assertEquals(13, c2.getRankValue());

    }

    @Test
    public void cardSuitValue()
    {
        assertAll(
                ()->assertEquals(Card.Suit.SPADES, c.getCardSuit()),
                ()->assertEquals(Card.Suit.CLUBS, c1.getCardSuit()),
                ()->assertEquals(Card.Suit.DIAMONDS, c2.getCardSuit())
        );
    }



}



