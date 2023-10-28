import java.util.ArrayList;
import java.util.List;
public class Table {

    private Deck deck;
    private List<Card> gameCards;
    private List<Player> playerList;
    private int pot; // this is the total money in the pot
    private int openBet; //this is the highest bet in the current round
    private final int BIGBLINDBET = 5;
    private final int SMALLBLINDBET = 2;

    public Table(List<Player> playerList ) {

        deck = new Deck();
        deck.shuffleDeck();
        gameCards = new ArrayList<>();
        this.playerList = playerList;
        this.pot = 0;
        this.openBet = 0;
    }

    public List<Card> getGameCards() {
        return gameCards;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public int getOpenBet() {
        return openBet;
    }

    public void setOpenBet(int openBet) {
        this.openBet = openBet;
    }

    public void incOpenBet( int increment ) {
        this.openBet += increment;
    }

    public int getPot() {
        return pot;
    }

    public void setPot(int pot) {
        this.pot = pot;
    }

    public void incPot( int increment ) {
        this.pot += increment;
    }

    @Override
    public String toString() {
        String str = "Table cards: ";
        for( Card c : gameCards )
            str = str + c + ", ";
        str = str + "\n" + "Pot: " + pot + "\tOpen Bet: " + openBet + "\n";
        for( Player p : playerList ) {
            str = str + p;
            str = str + "\n";
        }
        return str;
    }

    /**
     * purpose: get the number of players who have not folded yet
     * @return an int, the number of players who have not folded yet
     */
    public int countActivePlayers() {
        int count = 0;
        for( Player p : playerList ) {
            if( !p.getFold() )
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
        this.openBet = 0;
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
     * purpose: reset the state of the table to be ready for a new round
     */
    protected void resetTable() {
        this.pot = 0;
        this.openBet = 0;
        this.gameCards = new ArrayList<>();
        this.deck.resetDeck();

        // consider handling players with no money left here
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
            this.openBet = BIGBLINDBET;

            better = getNextLeft( bigBlind );


        }

        // it is not the first round
        else if( roundNumber > 1 && roundNumber < 5 ) {
            better = getNextLeft( bigBlind );

            // loop through all players until everyone has had a chance to bet once, and everyone still in play
            // is equal to the open bet
            for(; !passedAll || playerList.get(better).getTotalRoundBet() != this.openBet; better++ ) {
                choice = playerList.get(better).getPlayerChoice( this.openBet);
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
        this.openBet = 0;

    }
    public static void main(String[] args)
    {
        Deck deck = new Deck();

        deck.shuffleDeck();
        System.out.println(deck.getDeck());
    }
}
