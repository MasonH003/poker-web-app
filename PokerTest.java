import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PokerTest {

    private Game pokerGame;
    private List<Player> playerList;

    @BeforeEach
    public void gameTest() {
        playerList = new ArrayList();
        Player p1 = new Player("Barry");
        Player p2 = new Player("Harriot");
        playerList.add(p1);
        playerList.add(p2);

        pokerGame = new Game(playerList);
    }

    @Test
    public void testShuffleDeck() {

        // Create two decks:
        Deck deck = new Deck();
        Deck shuffledDeck = new Deck();
        // Shuffle one of them:
        shuffledDeck.shuffleDeck();
        // Check to see if they are equal/the same:
        assertNotEquals(deck, shuffledDeck);
    }

    @Test
    public void testDealFlop() {

        // Deal flop:
        pokerGame.dealFlop();
        // Assert equals size of community cards == 3
        assertEquals(3, pokerGame.getGameCards().size());
    }

    @Test
    public void testDealTurn() {

        // Deal initial flop: 3 cards
        pokerGame.dealFlop();
        // Deal turn: 4 cards
        pokerGame.dealTurn();
        // Assert equals size of community cards == 4
        assertEquals(4, pokerGame.getGameCards().size());
    }

    @Test
    public void testDealRiver() {

        // Deal initial flop: 3 cards
        pokerGame.dealFlop();
        // Deal turn: 4 cards
        pokerGame.dealTurn();
        // Deal river: 5 cards
        pokerGame.dealRiver();
        // Assert equals size of community cards == 5
        assertEquals(5, pokerGame.getGameCards().size());
    }
}
