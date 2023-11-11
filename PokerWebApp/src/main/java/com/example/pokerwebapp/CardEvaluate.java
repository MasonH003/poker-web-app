package com.example.pokerwebapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;
import java.util.Collections;
import java.math.BigDecimal;

public class CardEvaluate {
    //function to check if cards are sequential
    public boolean isSequential(Card[] hand)
    {
        int limit= hand.length-1;
        //if hand has king, check if hand could be ace straight/straight flush and remove ace from check if so
        if(hand[0].compareTo(hand[4])==12){
            limit=limit -1;
        }
        //check each card with its right neighbor
        for(int i=0; i<limit;i++)
        {   //return false if cards aren't sequential
            if(hand[i].compareTo(hand[i+1])!=1)
            {
                return false;
            }
        }
        //true if all cards are sequential
        return true;
    }

    //Sort hashmap based on values
    public HashMap<Card.Rank, Integer> sortByValue(HashMap<Card.Rank, Integer> suit_map)
    {
        // Create list from suit hash map
        List<Map.Entry<Card.Rank, Integer>> list= new LinkedList<>(suit_map.entrySet());

        // Sort list
        list.sort(Map.Entry.comparingByValue());
        //Put sorted data back into hashmap
        HashMap<Card.Rank, Integer> result = new LinkedHashMap<>();
        for (Map.Entry<Card.Rank, Integer> i : list) {
            result.put(i.getKey(), i.getValue());
        }
        return result;
    }
    //represent hand as a decimal, first two decimal spaces represent first card rank value, third and fourth spaces
    //represent second card's, etc
    //cards ranked from highest rank to lowest
    public double buildRank(Card[] hand){
        BigDecimal bd0 = BigDecimal.valueOf(hand[0].getRankValue() / 100.0);
        BigDecimal bd1 = BigDecimal.valueOf(hand[1].getRankValue() / 10000.0);
        BigDecimal bd2 = BigDecimal.valueOf(hand[2].getRankValue() / 1000000.0);
        BigDecimal bd3 = BigDecimal.valueOf(hand[3].getRankValue() / 100000000.0);
        BigDecimal bd4 = BigDecimal.valueOf(hand[4].getRankValue() / 10000000000.0);
        bd0=bd0.add(bd1);
        bd0=bd0.add(bd2);
        bd0=bd0.add(bd3);
        bd0=bd0.add(bd4);
        return bd0.doubleValue();
    }

    public double RankHand(Card[] hand) {
        //initialize suit and rank maps
        HashMap<Card.Suit, Integer> count_suit = new HashMap<>();
        HashMap<Card.Rank, Integer> count_rank = new HashMap<>();
        //cards for comparing certain hands
        Card highOrKick;
        Card lowOrQuad;
        Card flag2=null;

        //count rank and suit of cards
        for (Card card : hand) {
            count_suit.compute(card.getCardSuit(), (key, value) -> ((value == null) ? 1 : value + 1));
            count_rank.compute(card.getCardRank(), (key, value) -> ((value == null) ? 1 : value + 1));
        }
        //sort by values
        Arrays.sort(hand, Collections.reverseOrder());
        count_rank= sortByValue(count_rank);
        System.out.println(count_suit);
        System.out.println(count_rank);

        //make decimal representing card, each two decimal spaces represents a card's rank
        double handValues = buildRank(hand);

        //evaluate cards, return float, whole number for hand ranking, and decimal for card ranking to eval same hands
        //if cards are sequential and same suite = straight flush
        if (count_suit.containsValue(5) && isSequential(hand)) {

            //if ace high-flush: royal flush, highest flush in game
            if(handValues==.1312111001){
                System.out.println("Royal flush");
                return 9.1413121110;
            }
            //else return straight flush
            else{
                System.out.println(hand[0].getCardRank() + "-high straight flush");
                return 9.0 + handValues;
            }
        }

        //same suite non-sequence=flush
        else if (count_suit.containsValue(5)) {
            System.out.println(hand[0].getCardRank() + "-high flush");
            return 6.0 + handValues;
        }

        //sequential with different suites=straight
        else if (isSequential(hand)) {
            System.out.println(hand[0].getCardRank() + " straight");
            return 5.0 + handValues;
        }

        //if four cards same number = four of a kind
        else if (count_rank.containsValue(4)) {
            if(hand[0].compareTo(hand[1])>0)//kicker card is higher rank than rest
            {
                lowOrQuad = hand[4];
            }
            else//four of a kind higher rank than kicker
            {
                lowOrQuad = hand[0];
            }
            System.out.println("Four of a kind, "+lowOrQuad.getCardRank());
            return 8.0 + handValues;
        }

        //check for hands with three cards and/or two cards of same ranks
        for (Map.Entry<Card.Rank, Integer> entry: count_rank.entrySet())
        {
            if (entry.getValue()==3)
            {
                //if three cards share rank and two cards share different rank=full house
                if(flag2!=null){
                    Card triplet=new Card(entry.getKey(), Card.Suit.DIAMONDS);
                    System.out.println("Full house, "+ triplet.getCardRank() +" over "+ flag2.getCardRank());
                    return 7.0 + handValues;
                }
                //if three cards of same rank = three of a kind
                else{
                    System.out.println("Three of a kind, "+hand[2].getCardRank());
                    return 4+handValues;
                }
            }

            else if(entry.getValue()==2){
                //if two pairs of two ranks = two pair
                if(flag2!=null) {
                    Card second=new Card(entry.getKey(),Card.Suit.DIAMONDS);

                    //rank higher pair over lower
                    if(flag2.compareTo(second)>0){
                        highOrKick=flag2;
                        lowOrQuad=second;
                    }
                    else{
                        highOrKick=second;
                        lowOrQuad=flag2;
                    }
                    System.out.println("Two pair, "+ highOrKick.getCardRank() +" and "+ lowOrQuad.getCardRank());
                    return 3.0+handValues;
                }
                else {flag2= new Card(entry.getKey(), Card.Suit.DIAMONDS);}
            }
        }
        //if one pair of a rank = one pair
        if(flag2!=null){
            System.out.println("One pair, "+ flag2.getCardRank());
            return 2+handValues;
        }

      //else, high card, rank by card rank
      else{
          System.out.println("High card: " + hand[0].getCardRank());
          return 1+handValues;
      }
  }

    public static void main(String[] args) {
        Card[] hand=new Card[5];

        Card c1 = new Card(Card.Rank.KING, Card.Suit.SPADES);
        Card c2 = new Card(Card.Rank.QUEEN, Card.Suit.SPADES);
        Card c3 = new Card(Card.Rank.TWO, Card.Suit.SPADES);
        Card c4 = new Card(Card.Rank.FOUR, Card.Suit.SPADES);
        Card c5 = new Card(Card.Rank.ACE, Card.Suit.CLUBS);
        hand[0]=c1;
        hand[1]=c2;
        hand[2]=c3;
        hand[3]=c4;
        hand[4]=c5;

        CardEvaluate e = new CardEvaluate();
        System.out.println(e.RankHand(hand));
        for (Card num : hand) {
            System.out.print(num + " ");
        }

        Card[] hand1 ={new Card(Card.Rank.KING, Card.Suit.SPADES), new Card(Card.Rank.QUEEN, Card.Suit.SPADES),
                new Card(Card.Rank.TEN, Card.Suit.SPADES), new Card(Card.Rank.JACK, Card.Suit.SPADES), new Card(Card.Rank.ACE, Card.Suit.SPADES)};
        Card[] hand2 ={new Card(Card.Rank.ACE, Card.Suit.CLUBS), new Card(Card.Rank.FOUR, Card.Suit.HEARTS),
                new Card(Card.Rank.FOUR, Card.Suit.CLUBS), new Card(Card.Rank.FOUR, Card.Suit.DIAMONDS), new Card(Card.Rank.FOUR, Card.Suit.SPADES)};
        Card[] hand3 ={new Card(Card.Rank.SIX, Card.Suit.HEARTS), new Card(Card.Rank.SIX, Card.Suit.SPADES),
                new Card(Card.Rank.SIX, Card.Suit.DIAMONDS), new Card(Card.Rank.TEN, Card.Suit.HEARTS), new Card(Card.Rank.TEN, Card.Suit.CLUBS)};
        Card[] hand4 ={new Card(Card.Rank.KING, Card.Suit.SPADES), new Card(Card.Rank.TWO, Card.Suit.SPADES),
                new Card(Card.Rank.TEN, Card.Suit.SPADES), new Card(Card.Rank.JACK, Card.Suit.SPADES), new Card(Card.Rank.ACE, Card.Suit.SPADES)};
        Card[] hand5 ={new Card(Card.Rank.KING, Card.Suit.SPADES), new Card(Card.Rank.QUEEN, Card.Suit.SPADES),
                new Card(Card.Rank.TEN, Card.Suit.CLUBS), new Card(Card.Rank.JACK, Card.Suit.SPADES), new Card(Card.Rank.ACE, Card.Suit.SPADES)};
        Card[] hand6 ={new Card(Card.Rank.SIX, Card.Suit.HEARTS), new Card(Card.Rank.SIX, Card.Suit.SPADES),
                new Card(Card.Rank.SIX, Card.Suit.DIAMONDS), new Card(Card.Rank.NINE, Card.Suit.HEARTS), new Card(Card.Rank.TEN, Card.Suit.CLUBS)};
        Card[] hand7 ={new Card(Card.Rank.SIX, Card.Suit.HEARTS), new Card(Card.Rank.FIVE, Card.Suit.SPADES),
                new Card(Card.Rank.SIX, Card.Suit.DIAMONDS), new Card(Card.Rank.TEN, Card.Suit.HEARTS), new Card(Card.Rank.TEN, Card.Suit.CLUBS)};
        Card[] hand8 ={new Card(Card.Rank.SIX, Card.Suit.HEARTS), new Card(Card.Rank.SIX, Card.Suit.SPADES),
                new Card(Card.Rank.TWO, Card.Suit.DIAMONDS), new Card(Card.Rank.NINE, Card.Suit.HEARTS), new Card(Card.Rank.TEN, Card.Suit.CLUBS)};
        Card[] hand9 ={new Card(Card.Rank.KING, Card.Suit.CLUBS), new Card(Card.Rank.QUEEN, Card.Suit.HEARTS),
                new Card(Card.Rank.JACK, Card.Suit.DIAMONDS), new Card(Card.Rank.TWO, Card.Suit.HEARTS), new Card(Card.Rank.THREE, Card.Suit.CLUBS)};

        System.out.println(e.RankHand(hand1));
        System.out.println(e.RankHand(hand2));
        System.out.println(e.RankHand(hand3));
        System.out.println(e.RankHand(hand4));
        System.out.println(e.RankHand(hand5));
        System.out.println(e.RankHand(hand6));
        System.out.println(e.RankHand(hand7));
        System.out.println(e.RankHand(hand8));
        System.out.println(e.RankHand(hand9));

    }
}
