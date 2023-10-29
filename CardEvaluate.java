import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;
import java.util.Collections;

public class CardEvaluate {
    //function to compare hands
    public int compareHands(){
        return 0;
    }

    //function to check if cards are sequential
    public boolean isSequential(Card[] hand)
    {   //check each card with its right neighbor
        for(int i=0; i<hand.length-1;i++)
        {   //return false if cards aren't sequential
            if(hand[i].compareTo(hand[i+1])!=1||hand[i].compareTo(hand[i+1])!=12)
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

        //evaluate cards, return float, whole number for hand ranking, and decimal for card ranking to eval same hands
        //if cards are sequential and same suite = straight flush
        if (count_suit.containsValue(5) && isSequential(hand)) {
            System.out.println(hand[0].getCardRank() + "-high straight flush");
            return 9.0 + (hand[0].getRankValue()/100.0) +(hand[1].getRankValue()/10000.0)+(hand[2].getRankValue()/1000000.0)
                    +(hand[3].getRankValue()/100000000.0)+(hand[4].getRankValue()/10000000000.0);
        }

        //same suite non-sequence=flush
        else if (count_suit.containsValue(5)) {
            System.out.println(hand[0].getCardRank() + "-high flush");
            return 6.0 + (hand[0].getRankValue()/100.0)+(hand[1].getRankValue()/10000.0)+(hand[2].getRankValue()/1000000.0)
                    +(hand[3].getRankValue()/100000000.0)+(hand[4].getRankValue()/10000000000.0);
        }

        //sequential with different suites=straight
        else if (isSequential(hand)) {
            System.out.println(hand[0].getCardRank() + " straight");
            return 5.0 + (hand[0].getRankValue()/100.0)+(hand[1].getRankValue()/10000.0)+(hand[2].getRankValue()/1000000.0)
                    +(hand[3].getRankValue()/100000000.0)+(hand[4].getRankValue()/10000000000.0);
        }

        //if four cards same number = four of a kind, 1st-2nd decimals = 4 rank, 3rd-4th decimal = kicker rank
        else if (count_rank.containsValue(4)) {
            if(hand[0].compareTo(hand[1])>0)//kicker card is higher rank than rest
            {
                highOrKick = hand[0];
                lowOrQuad = hand[4];
            }
            else//four of a kind higher rank than kicker
            {   highOrKick = hand[4];
                lowOrQuad = hand[0];
            }
            System.out.println("Four of a kind, "+lowOrQuad.getCardRank());
            return 8.0 + (lowOrQuad.getRankValue() / 100.0) + (highOrKick.getRankValue() / 10000.0);
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
                    return 7.0 + (triplet.getRankValue()/100.0) + (flag2.getRankValue()/10000.0);
                }
                //if three cards of same rank = three of a kind
                else{
                    //get kickers in seperate list
                    List<Card> copy=getKickers(hand,entry.getValue());

                    System.out.println("Three of a kind, "+hand[2].getCardRank());
                    return 4+(hand[2].getRankValue()/100.0)+(copy.get(0).getRankValue()/10000.0)+(copy.get(1).getRankValue()/1000000.0);
                }
            }

            else if(entry.getValue()==2){
                //if two pairs of two ranks = two pair
                if(flag2!=null) {
                    Card second=new Card(entry.getKey(),Card.Suit.DIAMONDS);
                    //get kicker
                    Card kick=getKicker(hand,flag2.getRankValue(),second.getRankValue());
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
                    return 3.0+(highOrKick.getRankValue()/100.0)+(lowOrQuad.getRankValue()/10000.0)+(kick.getRankValue()/1000000.0);
                }
                else {flag2= new Card(entry.getKey(), Card.Suit.DIAMONDS);}
            }
        }
        //if one pair of a rank = one pair
        if(flag2!=null){
            //get kickers in seperate list
            List<Card> copy=getKickers(hand,flag2.getRankValue());
            System.out.println("One pair, "+ flag2.getCardRank());
            return 2+(flag2.getRankValue()/100.0)+(copy.get(0).getRankValue()/10000.0)+(copy.get(1).getRankValue()/1000000.0)
                    +(copy.get(2).getRankValue()/100000000.0);
        }

      //else, high card, rank by card rank
      else{
          System.out.println("High card: " + hand[0].getCardRank());
          return 1+(hand[0].getRankValue()/100.0)+(hand[1].getRankValue()/10000.0)+(hand[2].getRankValue()/1000000.0)
                  +(hand[3].getRankValue()/100000000.0)+(hand[4].getRankValue()/10000000000.0);
      }
  }

    //function to get kicker
    public Card getKicker(Card[] hand, int nonKick, int secondNonKick)
    {
        Card kick=hand[0];//placeholder
        for(Card c: hand)
        {
            if(c.getRankValue()!=nonKick && c.getRankValue()!=secondNonKick)
                return c;
        }
        return kick;
    }

  //function to get kickers
  public List<Card> getKickers(Card[] hand, int nonKick)
  {
      List<Card> copy=new ArrayList<>();
      for(Card c: hand)
      {
          if(c.getRankValue()!=nonKick)
              copy.add(c);
      }
      return copy;
  }

    public static void main(String[] args) {
        Card[] hand=new Card[5];

        Card c1 = new Card(Card.Rank.KING, Card.Suit.DIAMONDS);
        Card c2 = new Card(Card.Rank.KING, Card.Suit.CLUBS);
        Card c3 = new Card(Card.Rank.FIVE, Card.Suit.HEARTS);
        Card c4 = new Card(Card.Rank.FIVE, Card.Suit.SPADES);
        Card c5 = new Card(Card.Rank.SIX, Card.Suit.SPADES);
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
    }
}
