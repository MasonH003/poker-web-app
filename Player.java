import java.util.ArrayList;
import java.util.List;

public class Player {

    private String name;
    private int balance;
    private List<Card> hand;
    private boolean isFolded = false;
    private int totalRoundBet;

    public Player(String name) {
        this.name = name;
        this.balance = 500;
        this.hand = new ArrayList<Card>();
        this.totalRoundBet = 0;
    }

    public void setPlayerHand(Card dealtCard) {
        hand.add(dealtCard);
    }

    //fix getter to return a copy not a reference
    public List<Card> getPlayerHand()
    {
        return hand;
    }

    public void addBalance(int addAmount)
    {
        balance+=addAmount;
    }
    public void subBalance(int subAmount)
    {
        balance-=subAmount;
    }
    public int getBalance() {
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

    @Override
    public String toString()
    {
        return "Player name: " + this.name + "\tBalance: " + this.balance + "\tHand: " + this.hand;
    }

    public int getBet() {
        // get a player's bet
        return -1;
    }
    public static void main(String[] args)
    {
        Player p1 = new Player("Christoph");
        Player p2 = new Player("Alex");
        Deck deck = new Deck();
        deck.shuffleDeck();
        System.out.println(deck.getDeck());
        p1.setPlayerHand(deck.dealCard());
        p1.setPlayerHand(deck.dealCard());
        System.out.println(p1);
        p2.setPlayerHand(deck.dealCard());
        p2.setPlayerHand(deck.dealCard());
        System.out.println(p2);
    }
}

