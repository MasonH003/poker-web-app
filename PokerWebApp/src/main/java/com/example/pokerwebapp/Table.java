package com.example.pokerwebapp;

import java.util.ArrayList;
import java.util.List;
public class Table {

    private String name;
    private Deck deck;
    private List<Card> gameCards;
    private List<Player> playerList;
    private int pot; // this is the total money in the pot
    private int openBet; //this is the highest bet in the current round
    private final int BIGBLINDBET = 5;
    private final int SMALLBLINDBET = 2;

    public Table(List<Player> playerList ) {
        name = "";
        deck = new Deck();
        deck.shuffleDeck();
        gameCards = new ArrayList<>();
        this.playerList = playerList;
        this.pot = 0;
        this.openBet = 0;
    }

    public String getName(String name) {return name;}

    public void setName(String name) {this.name = name;}

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
     * purpose: if there is only one player left, return them so they can be given the pot
     * @return a com.example.pokerwebapp.Player, the last un-folded player, or null if incorrectly called with more than 1 active player
     */
    public Player findLastPlayer() {
        if( countActivePlayers() != 1)
            return null;
        for( Player p : playerList ) {
            if( !p.getFold() )
                return p;
        }
        return null;
    }

    /**
     * purpose: give the winning player the pot
     * @param p a com.example.pokerwebapp.Player, the winner of the hand
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
        resetPlayerCheckStatus();
        resetPlayerFoldStatus();
        // consider handling players with no money left here
    }

    /**
     * purpose: reset the fold status of every player
     */
    public void resetPlayerFoldStatus()
    {
        for(Player p : playerList)
        {
            p.setFold(false);
        }
    }
    public void resetPlayerCheckStatus()
    {
        for(Player p : playerList)
        {
            p.setChecked(false);
        }
    }

    public void resetTotalRoundBet()
    {
        for(Player p : playerList)
        {
            p.setTotalRoundBet(0);
        }

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
        String choice = "";

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

            for( Player p : playerList ) {
                p.addToPlayerHand( deck.dealCard() );
                p.addToPlayerHand( deck.dealCard() );
            }

            better = getNextLeft( bigBlind );

            // Fixed? Do betting here
            for(; !passedAll || playerList.get(better).getTotalRoundBet() != this.openBet; better++ ) {
                if( better >= playerList.size() )
                    better = 0;

                //don't ask for a choice from player who has already folded in the round
                if(!playerList.get(better).getFold()) {
                    choice = playerList.get(better).getPlayerChoice(openBet);
                }
                //fold, skip the players turn
                if (playerList.get(better).getFold())
                {
                    continue;
                }
                else if(choice.equals("call"))
                {
                    this.pot+=openBet;
                }
                else if(choice.equals("raise"))
                {
                    int raiseAmount = playerList.get(better).getRaiseAmount();
                    this.pot+=raiseAmount;
                    openBet = playerList.get(better).getTotalRoundBet() + raiseAmount;
                }

                if( better == bigBlind )
                    passedAll = true;
                //System.out.println("better: " + better + "\tbig blind: " + bigBlind +"\n");
            }
            //after a round completes reset the openBet to 0 and every players totalroundbet
            this.openBet = 0;
            resetTotalRoundBet();
        }

        // it is not the first round
        else if( roundNumber > 1 && roundNumber < 5 ) {
            better = getNextLeft( bigBlind );

            // loop through all players until everyone has had a chance to bet once, and everyone still in play
            // is equal to the open bet
            for(; !passedAll || playerList.get(better).getTotalRoundBet() != this.openBet; better++ ) {
                //don't ask for a choice from player who has already folded or checked in the round
                if(!playerList.get(better).getFold() && !playerList.get(better).getCheckedStatus()) {
                    choice = playerList.get(better).getPlayerChoice(openBet);
                }
                //fold
                if (playerList.get(better).getFold())
                {
                    continue;
                }
                else if(choice.equals("call"))
                {
                    this.pot+=openBet;
                }
                else if(choice.equals("raise"))
                {
                    int raiseAmount = playerList.get(better).getRaiseAmount();
                    this.pot+=raiseAmount;
                    openBet = playerList.get(better).getTotalRoundBet() + raiseAmount;
                }
                //if a player checks and
                else if(playerList.get(better).getCheckedStatus())
                {
                    continue;
                }

                if( countActivePlayers() == 1 ) {
                        return;
                    }
                if( better == bigBlind )
                    passedAll = true;
                }
            //after a round is completed reset check status, openbet, and totalroundbet of players
            resetPlayerCheckStatus();
            resetTotalRoundBet();
            this.openBet = 0;
        }
        //after the 4 rounds complete, reset a players fold status
        resetPlayerFoldStatus();

    }




    public static void main(String[] args)
    {
        Deck deck = new Deck();

        deck.shuffleDeck();
        System.out.println(deck.getDeck());
    }
}
