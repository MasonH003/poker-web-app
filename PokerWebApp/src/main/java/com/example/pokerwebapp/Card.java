package com.example.pokerwebapp;

import java.util.Locale;

//A simple card class
public class Card implements Comparable<Card>{
    public enum Suit {
        SPADES, HEARTS, DIAMONDS, CLUBS
    }
    public enum Rank {
        ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6),
        SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(11), QUEEN(12), KING(13);
        private final int cardValue;

        Rank(int cardValue)
        {
            this.cardValue=cardValue;
        }
    }
    public int getRankValue()
    {
        return rank.cardValue;
    }

    private final Rank rank;
    private final Suit suit;

    public Card(Rank rank, Suit suit)
    {
        this.rank = rank;
        this.suit = suit;
    }
    public Suit getCardSuit()
    {
        return suit;
    }
    public Rank getCardRank()
    {
        return rank;
    }

    public String generateImageUrl() {
        String str = "";
        if( rank == Rank.ACE || rank == Rank.JACK || rank == Rank.QUEEN || rank == rank.KING )
            str = "images/playing-cards/" + rank + "_of_" + suit + ".png";
        else
            str = "images/playing-cards/" + rank.cardValue + "_of_" + suit + ".png";
        return str.toLowerCase();
    }

    @Override
    public int compareTo(Card card)
    {
        return rank.cardValue - card.rank.cardValue;
    }
    @Override
    public String toString()
    {
        return rank + " OF " + suit;
    }
    /*public static void main(String[] args)
    {
        Card card = new Card(Rank.ACE, Suit.SPADES);
        System.out.println(card);
        System.out.println(card.getRankValue());

        System.out.println( card.generateImageUrl() );
        Card card2 = new Card(Rank.TWO, Suit.CLUBS);
        System.out.println( card2.generateImageUrl() );
        System.out.println( "images/playing-cards/2_of_clubs.png");
    }*/
}
