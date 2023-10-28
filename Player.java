import java.util.ArrayList;
import java.util.List;

public class Player {

    private String name;
    private int balance;
    private List<Card> hand;
    private boolean isFolded = false;
    private int totalRoundBet; // how much this player has bet this round; reset after every round

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

    public void setTotalRoundBet(int totalRoundBet) {
        this.totalRoundBet = totalRoundBet;
    }

    public int getTotalRoundBet() {
        return totalRoundBet;
    }

    @Override
    public String toString()
    {
        return "Player name: " + this.name + "\tBalance: " + this.balance + "\tHand: " + this.hand;
    }

    /**
     * purpose: get a player's choice of action when it's their turn
     * @param open_bet an int, the open bet of the round (i.e. what the player's totalRoundBet must match to keep playing)
     * @return an int, 0 if check, positive if calling or raising, -1 if folding
     */
    public int getPlayerChoice(int open_bet ) {
        // get a player's choice on their turn
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

