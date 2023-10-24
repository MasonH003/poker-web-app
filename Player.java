import java.util.ArrayList;
import java.util.List;

public class Player {
    private int money;
    private List<Card> hand;

    public Player() {
        this.money = 500;
        this.hand = new ArrayList<Card>();
    }

    public void playerHand(Card dealtCard) {
        hand.add(dealtCard);
    }
    //fix getter to return a copy not a reference
    public List<Card> getPlayerHand()
    {
        return hand;
    }

    public int getPlayerBalance() {
        return money;
    }

    public void setPlayerBalance(int money)
    {
        this.money = money;
    }
//    public static void main(String[] args)
//    {
//        Deck deck = new Deck();
//        Player p1 = new Player();
//        deck.shuffleDeck();
//        p1.playerHand(deck.deal());
//        p1.playerHand(deck.deal());
//        System.out.println(p1.getPlayerBalance());
//        System.out.println(p1.getPlayerHand());
//        int p1balance = p1.getPlayerBalance();
//        p1.setPlayerBalance(1000 + p1balance);
//        System.out.println(p1.getPlayerBalance());
//
//    }
}

