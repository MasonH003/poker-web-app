package com.example.pokerwebapp;

import java.util.ArrayList;

public class Driver {
    public static void main( String[] args ) {
        Player p1 = new Player("Mason");
        Player p2 = new Player( "Christoph" );
        Player p3 = new Player( "Horacio" );
        Player p4 = new Player( "Karis" );3

        ArrayList<Player> playerList = new ArrayList<>();
        playerList.add( p1 );
        playerList.add( p2 );
        playerList.add( p3 );
        playerList.add( p4 );

        Table table = new Table( playerList );
        RoundController rc = new RoundController( table );

        rc.playAGame();

    }

}
