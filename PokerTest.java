import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    public void testDealFlop() {

        // Deal flop:
        pokerGame.dealFlop();
        // Assert equals size of community cards == 3
        assertEquals(3, pokerGame.getGameCards().size());
    }
}
