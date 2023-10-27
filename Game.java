import java.util.ArrayList;
import java.util.List;
public class Game {

    private Deck deck;
    private List<Card> gameCards;
    private List<Player> playerList;
    private int pot; // this is the total money in the pot
    private int open_bet; //this is the highest bet in the current round
    private final int BIGBLINDBET = 5;
    private final int SMALLBLINDBET = 2;

    public Game( List<Player> playerList ) {

        deck = new Deck();
        gameCards = new ArrayList<>();
        this.playerList = playerList;
        this.pot = 0;
        this.open_bet = 0;
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
     * purpose: give the winning player the pot
     * @param p a Player, the winner of the hand
     */
    public void win( Player p ) {
        p.addBalance(this.pot);
        this.pot = 0;
        this.open_bet = 0;
    }

    /**
     * During a round, draw new card on table for everyone to use in their hand
     */
    public void dealTurn() {
        gameCards.add(deck.dealCard());
    }


    /**
     * During the last round, draw new card on table for everyone to use in their hand
     */
    public void dealRiver() {
        gameCards.add(deck.dealCard());
    }



    /**
     * purpose: find the closest player to the left of the Big Blind
     * that has not yet folded
     * @param bigBlind an int, the index of the big blind
     * @return
     */
    protected int getNextLeft( int bigBlind ) {
        Player p;
        int next = bigBlind+1;
        while( next != bigBlind )
        {
            if( next >= playerList.size() )
                next = 0;

            if( !playerList.get(next).getFold() )
                return next;

            next++;


        }

        return -1; // the big blind is the only player left
    }

    /**
     * purpose: do a round of betting
     * players can check if they're equal to the open bet,
     *      call if they're less than the open bet,
     *      raise if they have enough money,
     *      or fold.
     */
    protected void roundOfBetting( int roundNumber, int bigBlind ) {
        int better;
        int smallBlind;
        if( bigBlind >= playerList.size() )
            smallBlind = 0;
        else
            smallBlind = bigBlind+1;

        Player turn = playerList.get( getNextLeft( bigBlind ) );

        if( roundNumber == 1 ) {
            playerList.get(bigBlind).subBalance(BIGBLINDBET);
            this.pot += BIGBLINDBET;
            playerList.get(smallBlind).subBalance(SMALLBLINDBET);
            this.pot += SMALLBLINDBET;

            better = getNextLeft( bigBlind );
        }
        else if( roundNumber > 1 && roundNumber < 5 ) {


        }

        // open bet returns to 0 at the end of each betting round
        this.open_bet = 0;

    }
    public static void main(String[] args)
    {
        Deck deck = new Deck();

        deck.shuffleDeck();
        System.out.println(deck.getDeck());
    }
}
