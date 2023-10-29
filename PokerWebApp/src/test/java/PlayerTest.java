import com.example.pokerwebapp.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.example.pokerwebapp.Player;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    Player p;
    @BeforeEach
    public void init()
    {
        p = new Player("Chris");
    }
    @Test
    public void addPlayerBalance()
    {
        p.addBalance(500);
        assertEquals(1000, p.getBalance());
    }

    @Test
    public void subPlayerBalance()
    {
        p.subBalance(500);
        assertEquals(0, p.getBalance());
    }

    @Test
    public void playerHand()
    {
        Card card = new Card(Card.Rank.ACE, Card.Suit.SPADES);
        Card card2 = new Card(Card.Rank.KING, Card.Suit.CLUBS);
        List<Card> expectedHand = new ArrayList<Card>();
        p.addToPlayerHand(card);
        p.addToPlayerHand(card2);
        expectedHand.add(card);
        expectedHand.add(card2);
        assertEquals(expectedHand,p.getPlayerHand());
    }

    @Test
    public void setGetFold()
    {
        p.setFold(false);
        assertFalse(p.getFold());
    }

    @Test
    public void setGetTotalRoundBet()
    {
        p.setTotalRoundBet(50);
        assertEquals(50, p.getTotalRoundBet());
    }

    @Test
    public void setGetCheck()
    {
        p.setChecked(false);
        assertFalse(p.getCheckedStatus());
    }

//    @Test
//    public void playerChoice()
//    {
//        p.getPlayerChoice(40);
//
//    }
}
