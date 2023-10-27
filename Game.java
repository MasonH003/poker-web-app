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
     * purpose: get the number of player who have not folded yet
     * @return an int, the number of players who have not folded yet
     */
    public int countActivePlayers() {
        int count = 0;
        for( Player p : playerList ) {
            if( p.getFold() )
                count++;
        }
        return count;
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
     * Initial three cards drawn on table for everyone to use in their hand
     */
    public void dealFlop() {
        for (int i = 0; i < 3; i++) {
            gameCards.add(deck.dealCard());
        }
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
     *      raise,
     *      or fold.
     */
    protected void roundOfBetting( int roundNumber, int bigBlind ) {
        int better;
        int smallBlind;
        boolean passedAll = false; //a boolean to measure whether everyone's had a chance to bet or not
        int choice;

        if( bigBlind >= playerList.size() )
            smallBlind = 0;
        else
            smallBlind = bigBlind+1;

        Player turn = playerList.get( getNextLeft( bigBlind ) );

        if( roundNumber == 1 ) { // it is the first round, so the BB and SB must put in some money
            playerList.get(bigBlind).subBalance(BIGBLINDBET);
            this.pot += BIGBLINDBET;
            playerList.get(smallBlind).subBalance(SMALLBLINDBET);
            this.pot += SMALLBLINDBET;
            this.open_bet = BIGBLINDBET;

            better = getNextLeft( bigBlind );


        }

        // it is not the first round
        else if( roundNumber > 1 && roundNumber < 5 ) {
            better = getNextLeft( bigBlind );

            // loop through all players until everyone has had a chance to bet once, and everyone still in play
            // is equal to the open bet
            for( ; !passedAll || playerList.get(better).getTotalRoundBet() != this.open_bet ; better++ ) {
                choice = playerList.get(better).makeABet( this.open_bet );
                if( choice < 0 ) // if the player's choice is fold, set them to folded
                {
                    playerList.get(better).setFold(true);
                    if( countActivePlayers() == 1 ) {
                        return;
                    }
                }
                if( better == bigBlind )
                    passedAll = true;
            }


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
