package com.example.pokerwebapp;

import java.util.ArrayList;

public class Driver {
    public static void main( String[] args ) {
        Player p1 = new Player("Mason");
        Player p2 = new Player( "Christoph" );
        Player p3 = new Player( "Horacio" );
        Player p4 = new Player( "Karis" );
        System.out.println("Starting Balance: " + p1.getBalance());
        System.out.println("Perform a Call action");
        p1.getPlayerChoice(50);
        System.out.println("Updated Balance: " + p1.getBalance());
        System.out.println("Total Round Bet: " + p1.getTotalRoundBet());
        System.out.println("Perform a Raise action");
        p1.getPlayerChoice(50);
        System.out.println("Updated Balance: " + p1.getBalance());
        System.out.println("Total Round Bet: " + p1.getTotalRoundBet());
        System.out.println("Perform a Check action");
        p1.getPlayerChoice(50);
        System.out.println("Updated Balance: " + p1.getBalance());
        System.out.println("Total Round Bet: " + p1.getTotalRoundBet());
        System.out.println("Perform a Fold action");
        p1.getPlayerChoice(50);
        System.out.println("Updated Balance: " + p1.getBalance());
        System.out.println("Total Round Bet: " + p1.getTotalRoundBet());



//        ArrayList<Player> playerList = new ArrayList<>();
//        playerList.add( p1 );
//        playerList.add( p2 );
//        playerList.add( p3 );
//        playerList.add( p4 );
//
//        Table table = new Table( playerList );
//        RoundController rc = new RoundController( table );
//        table.dealFlop();
//        table.dealTurn();
//        table.dealRiver();
//        System.out.println("Table after flop turn and river: " + table);
//
//        Deck deck = new Deck();
//        p1.addToPlayerHand( deck.dealCard() );
//        p1.addToPlayerHand( deck.dealCard() );
//
//        System.out.println("Player demonstration\n");
//        System.out.println(p1);
//        System.out.println();
//
//        Card card = deck.dealCard();
//        System.out.println( "Card demonstration");
//        System.out.println( card );
//        System.out.println();
//
//        System.out.println("Deck demonstration");
//        System.out.println( "Fresh deck: " + deck );
//        deck.shuffleDeck();
//        System.out.println("Shuffled deck: " + deck);
//
//        System.out.println( "Table demonstration\nFresh table" );
//        System.out.println( table );
//
//        table.roundOfBetting( 1, 0 );
//
//        System.out.println( table );

        // rc.playAGame();

    }

}
