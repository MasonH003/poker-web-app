package com.example.pokerwebapp;

import java.lang.reflect.Array;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
public class Deck  {

    public enum HandType {
        UNRANKED(0), HIGH_CARD(1), PAIR(2), TWO_PAIR(3), THREE_OF_A_KIND(4), STRAIGHT(5),
        FLUSH(6), FULL_HOUSE(7), FOUR_OF_A_KIND(8), STRAIGHT_FLUSH(9), ROYAL_FLUSH(10);
        private final int handValue;

        HandType(int handValue)
        {
            this.handValue=handValue;
        }
    }
    public int getHandAsNumber()
    {
        return handType.handValue;
    }
    private List<Card> cards;
    private HandType handType;

    public Deck( ) {
        cards = new ArrayList<Card>();
        populateDeck();
        handType = HandType.UNRANKED;
    }


    public Deck( boolean emptyDeck ) {
        cards = new ArrayList<Card>();
        if( !emptyDeck )
            populateDeck();
        handType = HandType.UNRANKED;
    }

    @Override
    public String toString() {
        String str = "";
        for( Card c : cards )
            str = str + c.toString() + " ";
        return str;
    }
    private void populateDeck() {
        for (Card.Suit suit : Card.Suit.values())
            for (Card.Rank rank : Card.Rank.values()) {
                cards.add(new Card(rank, suit));
            }
    }
    public void shuffleDeck()
    {
        Collections.shuffle(cards);
    }

//    public List<com.example.pokerwebapp.Card> deal(int numCards)
//    {
//        List<com.example.pokerwebapp.Card> dealtCards = new ArrayList<com.example.pokerwebapp.Card>();
//        for(int i = 0; i < numCards; i++)
//        {
//            dealtCards.add(cards.remove(0));
//        }
//        return dealtCards;
//    }

    public Card dealCard()
    {
        return cards.remove(0);
    }
    //fix getter to return a copy not a reference
    public List<Card> getDeck()
    {
        return cards;
    }

    /**
     * purpose: reset the deck with all cards, shuffled
     */
    public void resetDeck() {
        this.cards = new ArrayList<Card>();
        populateDeck();
        shuffleDeck();
    }
    public int remainingDeckSize()
    {
        return cards.size();
    }

    public static Deck combineDecks( Deck d1, Deck d2 ) {
        ArrayList<Card> cards = new ArrayList<Card>();
        for( Card c : d1.getDeck() )
            cards.add(c);
        for( Card c : d2.getDeck() )
            cards.add(c);
        Deck d = new Deck(true);
        d.setCards( cards );
        return d;
    }

    public void setCards( ArrayList<Card> cards ) {
        this.cards = cards;
    }


    public boolean findCard( Card.Suit suit, Card.Rank rank ) {
        for( Card c : cards )
        {
            if( c.getCardSuit() == suit && c.getCardRank() == rank )
                return true;
        }
        return false;
    }

    public boolean findCardByRank( Card.Rank rank ) {
        for( Card c : cards )
        {
            if( c.getCardRank() == rank )
                return true;
        }
        return false;
    }

    public boolean findCard( Card needle ) {
        for( Card c : cards )
        {
            if( c.getCardSuit() == needle.getCardSuit() && c.getCardRank() == needle.getCardRank() )
                return true;
        }
        return false;
    }


    /**
     * purpose: Sorts deck according to rank
     * inputs: n/a
     * outputs: the deck is sorted
     */
    public void sortDeckByRank() {
        cards.sort( Comparator.naturalOrder() );
    }

    public HandType evaluateHand() {
        sortDeckByRank();
        if( hasRoyalFlush() )
            return HandType.ROYAL_FLUSH;
        else if( hasStraightFlush() )
            return HandType.STRAIGHT_FLUSH;
        else if( hasFourOfAKind() )
            return HandType.FOUR_OF_A_KIND;
        else if( hasFullHouse() )
            return HandType.FULL_HOUSE;
        else if( hasFlush() )
            return HandType.FLUSH;
        else if( hasStraight() )
            return HandType.STRAIGHT;
        else if( hasThreeOfAKind() )
            return HandType.THREE_OF_A_KIND;
        else if( hasTwoPair() )
            return HandType.TWO_PAIR;
        else if( hasPair() )
            return HandType.PAIR;
        else if( !cards.isEmpty() )
            return HandType.HIGH_CARD;
        else
            return HandType.UNRANKED;
    }


    /**
     * Below are nine helper methods to evaluate a hand
     * Each determines if the hand meets the conditions of
     * the handtype in its name
     * NOTE: These methods assume that the deck is sorted according to rank.
     * input: n/a
     * @return a boolean, false if it is not this kind of hand, true if it is
     */
    public boolean hasRoyalFlush() {
        if( findCard( Card.Suit.DIAMONDS, Card.Rank.ACE ) && findCard( Card.Suit.DIAMONDS, Card.Rank.TEN )
                && findCard( Card.Suit.DIAMONDS, Card.Rank.JACK ) && findCard( Card.Suit.DIAMONDS, Card.Rank.QUEEN )
                && findCard( Card.Suit.DIAMONDS, Card.Rank.KING ) )
            return true;
        else if( findCard( Card.Suit.HEARTS, Card.Rank.ACE ) && findCard( Card.Suit.HEARTS, Card.Rank.TEN )
                && findCard( Card.Suit.HEARTS, Card.Rank.JACK ) && findCard( Card.Suit.HEARTS, Card.Rank.QUEEN )
                && findCard( Card.Suit.HEARTS, Card.Rank.KING ) )
            return true;
        else if( findCard( Card.Suit.SPADES, Card.Rank.ACE ) && findCard( Card.Suit.SPADES, Card.Rank.TEN )
                && findCard( Card.Suit.SPADES, Card.Rank.JACK ) && findCard( Card.Suit.SPADES, Card.Rank.QUEEN )
                && findCard( Card.Suit.SPADES, Card.Rank.KING ) )
            return true;
        else if( findCard( Card.Suit.DIAMONDS, Card.Rank.ACE ) && findCard( Card.Suit.DIAMONDS, Card.Rank.TEN )
                && findCard( Card.Suit.DIAMONDS, Card.Rank.JACK ) && findCard( Card.Suit.DIAMONDS, Card.Rank.QUEEN )
                && findCard( Card.Suit.DIAMONDS, Card.Rank.KING ) )
            return true;
        else
            return false;
    }
    public boolean hasStraightFlush() {
        return false;
    }
    public boolean hasFourOfAKind() {
        for( Card c : cards ) { // pick one card in the deck
            int counter = 0;
            for( Card check : cards ) { //compare the one card to the whole deck and see how many there are
                if( c.getCardRank() == check.getCardRank() )
                    counter++;
            }
            if( counter >= 4 )
                return true;
        }

        return false;
    }
    public boolean hasFullHouse() {
        return false;
    }
    public boolean hasFlush() {
        int diamonds=0, hearts=0, clubs=0, spades=0;
        for( Card c : cards )
        {
            if( c.getCardSuit() == Card.Suit.DIAMONDS )
                diamonds++;
            else if( c.getCardSuit() == Card.Suit.HEARTS )
                hearts++;
            else if( c.getCardSuit() == Card.Suit.CLUBS )
                clubs++;
            else if( c.getCardSuit() == Card.Suit.SPADES )
                spades++;
        }

        return (diamonds >= 5 || hearts >= 5 || clubs >= 5 || spades >= 5);
    }
    public boolean hasStraight() {
        int counter = 0;
        int old = -1;
        for( Card c : cards )
        {
            if( old == -1 ) // if it's the first card in the deck
                counter = 1;
            // if we have a duplicate, this doesnt necessarily mean the straight is broken so continue
            else if( c.getRankValue() == old )
                continue;
            else if( c.getRankValue() == old+1 )
                counter++;
            else
                counter = 1;
            old = c.getRankValue();
            // pick up the edge case of an ACE acting as the end of a straight after a King
            if( counter == 4 && old == 13 && this.findCardByRank(Card.Rank.ACE) )
                return true;
            if( counter >= 5 )
                return true;
        }
        return false;
    }
    public boolean hasThreeOfAKind() {
        for( Card c : cards ) { // pick one card in the deck
            int counter = 0;
            for( Card check : cards ) { //compare the one card to the whole deck and see how many there are
                if( c.getCardRank() == check.getCardRank() )
                    counter++;
            }
            if( counter >= 3 )
                return true;
        }

        return false;
    }
    public boolean hasTwoPair() {
        return false;
    }
    public boolean hasPair() {
        for( Card c : cards ) { // pick one card in the deck
            int counter = 0;
            for( Card check : cards ) { //compare the one card to the whole deck and see how many there are
                if( c.getCardRank() == check.getCardRank() )
                    counter++;
            }
            if( counter >= 2 )
                return true;
        }

        return false;
    }


//    public static void main(String[] args) {
//        Deck deck = new Deck();
//        System.out.println(deck);
//        Card dealtCard = deck.dealCard();
//        System.out.println(dealtCard);
//        System.out.println(deck.remainingDeckSize());
//        System.out.println(deck.getDeck());
//        System.out.println(deck.dealCard());
//        System.out.println(deck.dealCard());
//        System.out.println(deck.dealCard());
//    }

}


