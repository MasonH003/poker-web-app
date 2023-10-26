import java.util.ArrayList;
import java.util.List;
public class Game {

    private Deck deck;
    private List<Card> gameCards;
    private List<Player> playerList;

    private final int BIGBLINDBET = 5;
    private final int SMALLBLINDBET = 2;

    public Game( List<Player> playerList ) {

        deck = new Deck();
        gameCards = new ArrayList<>();
        this.playerList = playerList;
    }

    public List<Card> getGameCards() {
        return gameCards;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    /**
     * Initial three cards drawn on table for everyone to use in their hand
     */
    public void dealFlop() {
        for (int i = 0; i < 3; i++) {
            gameCards.add(deck.dealCard());
        }
    }

    /**
     * purpose: find the closest player to the left of the Big Blind
     * that has not yet folded
     * @param bigBlind an int, the index of the big blind
     * @return
     */
    protected Player getNextLeft( int bigBlind ) {
        return null;
    }

    /**
     * purpose: do a round of betting
     * players can check if they're equal to the open bet,
     *      call if they're less than the open bet,
     *      raise if they have enough money,
     *      or fold.
     */
    protected void roundOfBetting( int roundNumber, int bigBlind ) {
        int smallBlind;
        if( bigBlind >= playerList.size() )
            smallBlind = 0;
        else
            smallBlind = bigBlind+1;

        Player turn = getNextLeft( bigBlind );

        if( roundNumber == 1 ) {
            playerList.get(bigBlind).subBalance(BIGBLINDBET);
            playerList.get(smallBlind).subBalance(SMALLBLINDBET);
        }
        else if( roundNumber > 1 && roundNumber < 5 ) {


        }


    }
    public static void main(String[] args)
    {
        Deck deck = new Deck();

        deck.shuffleDeck();
        System.out.println(deck.getDeck());
    }
}
