import java.util.ArrayList;
import java.util.*;

public class CardEvaluate {
    //function to compare hands

    /*don't think I need this anymore but just incase I do, I'll clean this up later
       /* for(Suit i:Suit.values())
            {
                count_suit.put(i,0);
            }
      for(Rank i:Rank.values())
      {
          count_rank.put(i,0);
      }*/
  public int RankHand(ArrayList<Card> hand){
      //initalize suit and rank maps counting
      Map<Card.Suit, Integer> count_suit = new HashMap<>();
      Map<Card.Rank, Integer> count_rank = new HashMap<>();

      //count rank and suit of cards
      for (Card card : hand) {
          count_suit.compute(card.getCardSuit(), (key, value) -> ((value == null) ? 1 : value + 1));
          count_rank.compute(card.getCardRank(), (key, value) -> ((value == null) ? 1 : value + 1));
          //System.out.println(hand.get(i));
      }
      System.out.println(count_suit);
      System.out.println(count_rank);
      //sort by values


      //evaluate cards, make a thing with two values, one for hand ranking, and one of card ranking to eval same hands
      for(int i=0;i<5;i++) {
          //if cards are sequential and same suite = straight flush
          if(count_suit.containsValue(5))//and function for checking sequence == true
          {
            System.out.println("Straight flush");
          }
          //same suite non-sequence=flush
          else if(count_suit.containsValue(5)){
              System.out.println("Flush");
          }
          //sequence different suites=straight
          //else if(count_suit.containsValue(5)){
              //and function for checking sequence == true System.out.println("Straight");
          //}
          //if four cards same number %13 = four of a kind
          else if(count_suit.containsValue(4)){
              System.out.println("Four of a kind, ");
          }
          //if three cards of one rank and two of another, full house
          else if(count_rank.containsValue(3)&&count_rank.containsValue(2)){
              System.out.println("Full house, ");
          }

          //if three cards of same rank = three of a kind
          else if(count_rank.containsValue(3)){
              System.out.println("Three of a kind, ");
          }
          //if two pairs of two ranks = two pair
          //else if(count_rank.containsValue(2))//2 2s{
          //    System.out.println("Two pair, ");
          //}
          //if one pair of a rank = one pair
          else if(count_rank.containsValue(2)){
              System.out.println("One pair, ");
          }
          //else, high card, rank by card rank
          else{
              System.out.println("High card: ");
          }
      }
      return 0;
  }
    public static void main(String[] args) {
        ArrayList<Card> hand= new ArrayList<>();

        Card c1 = new Card(Card.Rank.ACE, Card.Suit.SPADES);
        Card c2 = new Card(Card.Rank.TWO, Card.Suit.CLUBS);
        Card c3 = new Card(Card.Rank.THREE, Card.Suit.SPADES);
        Card c4 = new Card(Card.Rank.FOUR, Card.Suit.CLUBS);
        Card c5 = new Card(Card.Rank.FIVE, Card.Suit.HEARTS);
        hand.add(c1);
        hand.add(c2);
        hand.add(c3);
        hand.add(c4);
        hand.add(c5);
        //System.out.println(hand[0]);
        CardEvaluate e = new CardEvaluate();
        e.RankHand(hand);

    }
}
