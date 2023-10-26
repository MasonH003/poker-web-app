import java.util.ArrayList;

public class RoundController {
    Game table;
    static final int FINAL_ROUND = 4;

    /**
     * Constructor for round controller
     * @param table a Game that the round controller will be managing
     */
    RoundController( Game table ) {
        this.table = table;
    }

    /**
     * purpose: determine the player with the strongest hand
     * @return the player with the strongest hand
     */
    protected Player showdown() {
        ArrayList<Player> playerList = (ArrayList<Player>) table.getPlayerList();
        Player best = playerList.get(0);
        for( Player p : playerList )
        {
            //if( table.evaluateHand( p.getPlayerHand() ) > table.evaluateHand( best.getPlayerHand() ) )
                best = p;
        }

        return best;

    }


    /**
     * purpose: run a game of poker
     */
    protected void playAGame() {
        int roundCount = 1;
        int bigBlind = 0;
        //bet, then flop and bet, then turn and bet, then river and bet

        // continue playing rounds until there aren't enough players to play

        table.roundOfBetting( roundCount, bigBlind );

        roundCount++;
        table.dealFlop();
        table.roundOfBetting( roundCount, bigBlind );

        roundCount++;
        // table.dealTurn();
        table.roundOfBetting( roundCount, bigBlind );

        roundCount++;
        //table.dealRiver();
        table.roundOfBetting( roundCount, bigBlind );

        showdown();
        }




    }


