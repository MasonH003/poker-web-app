import java.util.ArrayList;
import java.util.List;

public class Player {
    private int balance;
    private List<Card> hand;

    private boolean isFolded;

    public Player() {
        this.balance = 500;
        this.hand = new ArrayList<Card>();
        this.isFolded = false;
    }

    public void playerHand(Card dealtCard) {
        hand.add(dealtCard);
    }

    //fix getter to return a copy not a reference
    public List<Card> getPlayerHand()
    {
        return hand;
    }

    public void addBalance(int money)
    {
        balance+=money;
    }

    public int getPlayerBalance() {
        return balance;
    }

    public void setFold(boolean fold)
    {
        isFolded = fold;
    }

    public boolean getFold()
    {
        return isFolded;
    }

    public void check()
    {

    }

    public void call()
    {

    }

    public void raise(int raiseAmount)
    {

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
//        p1.addBalance(1000);
//        System.out.println(p1.getPlayerBalance());
//
//    }
}

