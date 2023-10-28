import java.util.ArrayList;

public class RoundController {
    Table table;
    static final int FINAL_ROUND = 4;

    /**
     * Constructor for round controller
     *
     * @param table a Game that the round controller will be managing
     */
    RoundController(Table table) {
        this.table = table;
    }

    /**
     * NOTE: should probably be moved into Table.java
     * staying as is for Iteration 1
     * purpose: determine the player with the strongest hand
     *
     * @return the player with the strongest hand
     */
    protected Player showdown() {
        ArrayList<Player> playerList = (ArrayList<Player>) table.getPlayerList();
        Player best = playerList.get(0);
        for (Player p : playerList) {
            // if( !p.getFold() && table.evaluateHand( p.getPlayerHand() ) > table.evaluateHand( best.getPlayerHand() ) )
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

        // continue playing rounds until there aren't enough players to play

        // code duplication smells
        // functions dealFlop, dealTurn, dealRiver would have to be refactored to reduce duplication
        table.roundOfBetting(roundCount, bigBlind);
        if (table.countActivePlayers() == 1) {
            // determine winner and payout
            return;
        }

        roundCount++;
        table.dealFlop();
        System.out.println( "Round " + roundCount + "\n" + table  );
        table.roundOfBetting(roundCount, bigBlind);
        if (table.countActivePlayers() == 1) {
            // determine winner and payout
            return;
        }

        roundCount++;
        table.dealTurn();
        table.roundOfBetting(roundCount, bigBlind);
        if (table.countActivePlayers() == 1) {
            // determine winner and payout
            return;
        }

        roundCount++;
        table.dealRiver();
        table.roundOfBetting(roundCount, bigBlind);
        if (table.countActivePlayers() == 1) {
            // determine winner and payout
            return;
        }

        showdown();

        System.out.println( table );
        table.resetTable();



    }

}


