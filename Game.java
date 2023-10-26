import java.util.ArrayList;
import java.util.List;
public class Game {

    private Deck deck;
    private List<Card> gameCards;

    public Game() {

        deck = new Deck();
        gameCards = new ArrayList<>();
    }
    public List<Card> getGameCards() {
        return gameCards;
    }
    public void dealFlop() {
        for (int i = 0; i < 3; i++) {
            gameCards.add(deck.dealCard());
        }
    }
    public static void main(String[] args)
    {
        Deck deck = new Deck();

        deck.shuffleDeck();
        System.out.println(deck.getDeck());
    }
}
