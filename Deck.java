import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
public class Deck  {
    private final List<Card> cards;

    public Deck() {
        cards = new ArrayList<Card>();
        populateDeck();
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

    public List<Card> deal(int numCards)
    {
        List<Card> dealtCards = new ArrayList<Card>();
        for(int i = 0; i < numCards; i++)
        {
            dealtCards.add(cards.get(0));
            cards.remove(0);
        }
        return dealtCards;
    }

    public List<Card> getDeck()
    {
        return cards;
    }
//    public static void main(String[] args) {
//        Deck d = new Deck();
//        System.out.println(d.getDeck());
//        d.shuffleDeck();
//        System.out.println(d.getDeck());
//        List<Card> dealt = d.deal(5);
//        System.out.println("Dealt Cards: " + dealt);
//        System.out.println("New Deck: " + d.getDeck());
//    }

}




