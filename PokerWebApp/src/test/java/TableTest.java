import com.example.pokerwebapp.Deck;
import com.example.pokerwebapp.Player;
import com.example.pokerwebapp.Table;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TableTest {

    private Table pokerTable;
    private List<Player> playerList;

    @BeforeEach
    public void gameTest() {
        playerList = new ArrayList();
        Player p1 = new Player("Barry");
        Player p2 = new Player("Harriot");
        playerList.add(p1);
        playerList.add(p2);

        pokerTable = new Table(playerList);
    }

    @Test
    public void testGetOpenBet() {
        assertEquals( 0, pokerTable.getOpenBet());
    }

    @Test
    public void testSetOpenBet() {
        pokerTable.setOpenBet( 24 );
        assertEquals( 24, pokerTable.getOpenBet());
    }

    @Test
    public void testIncOpenBet() {
        pokerTable.setOpenBet( 24 );
        pokerTable.incOpenBet( 10 );
        assertEquals( 34, pokerTable.getOpenBet());
    }

    @Test
    public void testGetPot() {
        assertEquals( 0, pokerTable.getPot());
    }

    @Test
    public void testSetPot() {
        pokerTable.setPot( 200 );
        assertEquals( 200, pokerTable.getPot());
    }

    @Test
    public void testIncPot() {
        pokerTable.setPot( 200 );
        pokerTable.incPot( 300 );
        assertEquals( 500, pokerTable.getPot());
    }

    @Test
    public void testGetPlayerList() {
        assertEquals( playerList, pokerTable.getPlayerList());
    }

    @Test
    public void testGetGameCards() {
        pokerTable.dealFlop();
        assertEquals( 3, pokerTable.getGameCards().size());
    }

    @Test
    public void testCountActivePlayers() {
        assertEquals( 2, pokerTable.countActivePlayers());
    }

    public void testCountActivePlayersWhenFold() {
        pokerTable.getPlayerList().get(0).setFold( true );
        assertEquals( 1, pokerTable.countActivePlayers());
    }

    @Test
    public void testDealhand() {

        // New player and deck:
        Player p1 = new Player("Barry");
        Deck deck = new Deck();
        // Deal Barry a hand:
        p1.addToPlayerHand(deck.dealCard());
        p1.addToPlayerHand(deck.dealCard());
        // Assert Barry's hand has 2 cards:
        assertEquals(2, p1.getPlayerHand().size());
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
        pokerTable.dealFlop();
        // Assert equals size of community cards == 3
        assertEquals(3, pokerTable.getGameCards().size());
    }

    @Test
    public void testDealTurn() {

        // Deal initial flop: 3 cards
        pokerTable.dealFlop();
        // Deal turn: 4 cards
        pokerTable.dealTurn();
        // Assert equals size of community cards == 4
        assertEquals(4, pokerTable.getGameCards().size());
    }

    @Test
    public void testDealRiver() {

        // Deal initial flop: 3 cards
        pokerTable.dealFlop();
        // Deal turn: 4 cards
        pokerTable.dealTurn();
        // Deal river: 5 cards
        pokerTable.dealRiver();
        // Assert equals size of community cards == 5
        assertEquals(5, pokerTable.getGameCards().size());
    }


}
