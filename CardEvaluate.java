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
    {   //check card with its right neighbor
        for(int i=0; i<hand.length-1;i++)
        {   //return false if cards aren't sequential
            if(hand[i].compareTo(hand[i+1])!=1||hand[i].compareTo(hand[i+1])!=12)
            {
                return false;
            }
        }
        return true;
    }

    //Sort hashmap based on values
    public HashMap<Card.Rank, Integer> sortByValuePLACEHOLDER(HashMap<Card.Rank, Integer> suit_map)
    {
        // Create list from suit hash map
        List<Map.Entry<Card.Rank, Integer>> list= new LinkedList<Map.Entry<Card.Rank, Integer>>(suit_map.entrySet());

        // Sort list with Collections.sort() and Comparator to compare
        Collections.sort(list, new Comparator<Map.Entry<Card.Rank, Integer>>()
        {
            public int compare(Map.Entry<Card.Rank, Integer> suit1, Map.Entry<Card.Rank, Integer> suit2)
            {
                return (suit1.getValue()).compareTo(suit2.getValue());
            }
        });
        //Put sorted data back into hashmap
        HashMap<Card.Rank, Integer> result = new LinkedHashMap<Card.Rank, Integer>();
        for (Map.Entry<Card.Rank, Integer> i : list) {
            result.put(i.getKey(), i.getValue());
        }
        return result;
    }

    public double RankHand(Card[] hand) {
        //initialize suit and rank maps
        HashMap<Card.Suit, Integer> count_suit = new HashMap<>();
        HashMap<Card.Rank, Integer> count_rank = new HashMap<>();

        //count rank and suit of cards
        for (Card card : hand) {
            count_suit.compute(card.getCardSuit(), (key, value) -> ((value == null) ? 1 : value + 1));
            count_rank.compute(card.getCardRank(), (key, value) -> ((value == null) ? 1 : value + 1));
        }
        //sort by values
        Arrays.sort(hand, Collections.reverseOrder());
        count_rank= sortByValuePLACEHOLDER(count_rank);
        System.out.println(count_suit);
        System.out.println(count_rank);

        //evaluate cards, return float, whole number for hand ranking, and decimal for card ranking to eval same hands
        //if cards are sequential and same suite = straight flush
        if (count_suit.containsValue(5) && isSequential(hand)) {
            System.out.println(hand[0].getCardRank() + "-high straight flush");
            return 9.0 + (hand[0].getRankValue() / 100.0);
        }

        //same suite non-sequence=flush
        else if (count_suit.containsValue(5)) {
            System.out.println(hand[0].getCardRank() + "-high flush");
            return 6.0 + (hand[0].getRankValue() / 100.0);
        }
        //sequential with different suites=straight
        else if (isSequential(hand)) {
            System.out.println(hand[0].getCardRank() + " straight");
            return 5.0 + (hand[0].getRankValue() / 100.0);
        }

        //if four cards same number = four of a kind, 1st-2nd decimals = 4 rank, 3rd-4th decimal = kicker rank
        else if (count_rank.containsValue(4)) {
            System.out.println("Four of a kind, ");
            if(hand[0].compareTo(hand[1])>0)//kicker card higher rank than others
            {return 8.0 + (hand[4].getRankValue() / 100.0)+ (hand[0].getRankValue() / 10000.0);}
            else//four of a kind higher rank than kicker
            {return 8.0 + (hand[0].getRankValue() / 100.0) + (hand[4].getRankValue() / 10000.0);}
        }
        boolean flag2=false;
        boolean flag3=false;

        for (Map.Entry<Card.Rank, Integer> entry: count_rank.entrySet())
        {
            if (entry.getValue()==3)
            {
                flag3=true;
            }

            else if(entry.getValue()==2){
                //if three cards of one rank and two of another, full house
                if(flag3) {
                    System.out.println("Full house, ");
                    return 7.0;
                }
                //if two pairs of two ranks = two pair
                else if(flag2) {
                    System.out.println("Two pair, ");
                    return 3.0;
                }
                else
                {flag2=true;}
            }
        }
        //if three cards of same rank = three of a kind
        if(flag3){
                System.out.println("Three of a kind, ");
                return 4+(hand[2].getRankValue()/100.0);
        }
        //if one pair of a rank = one pair
        else if(flag2){
            System.out.println("One pair, ");
            return 2;
        }

      //else, high card, rank by card rank
      else{
          System.out.println("High card: " + hand[0].getCardRank());
          return 1+(hand[0].getRankValue()/100.0);
      }
  }
    public static void main(String[] args) {
        Card[] hand=new Card[5];

        Card c1 = new Card(Card.Rank.THREE, Card.Suit.DIAMONDS);
        Card c2 = new Card(Card.Rank.FOUR, Card.Suit.CLUBS);
        Card c3 = new Card(Card.Rank.FIVE, Card.Suit.HEARTS);
        Card c4 = new Card(Card.Rank.FOUR, Card.Suit.SPADES);
        Card c5 = new Card(Card.Rank.FIVE, Card.Suit.SPADES);
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
