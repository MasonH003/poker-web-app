import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
public class Deck  {
    private List<Card> cards;

    public Deck() {
        cards = new ArrayList<Card>();
        populateDeck();
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

//    public List<Card> deal(int numCards)
//    {
//        List<Card> dealtCards = new ArrayList<Card>();
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
    public static void main(String[] args) {
        Deck deck = new Deck();
        System.out.println(deck.getDeck());
        Card dealtCard = deck.dealCard();
        System.out.println(dealtCard);
        System.out.println(deck.remainingDeckSize());
        System.out.println(deck.getDeck());
        System.out.println(deck.dealCard());
        System.out.println(deck.dealCard());
        System.out.println(deck.dealCard());
    }

}




