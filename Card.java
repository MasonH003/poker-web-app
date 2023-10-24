//A simple Card class
public class Card {
    public enum Suit {
        SPADES, HEARTS, DIAMONDS, CLUBS
    }
    public enum Rank {
        TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6),
        SEVEN(7), EIGHT(8), NINE(9), TEN(10), JOKER(11), QUEEN(12), KING(13), ACE(14);
        private final int cardValue;

        Rank(int cardValue)
        {
            this.cardValue=cardValue;
        }
        public int getCardRankValue()
        {
            return cardValue;
        }
    }
    private final Rank rank;
    private final Suit suit;

    Card(Rank rank, Suit suit)
    {
        this.rank = rank;
        this.suit = suit;
    }
    public Suit getCardSuit()
    {
        return suit;
    }
    public Rank getCardRank()
    {
        return rank;
    }

    @Override
    public String toString()
    {
        return getCardRank() + " OF " + getCardSuit();
    }

//    public static void main(String[] args)
//    {
//        Card card = new Card(Rank.ACE, Suit.SPADES);
//        System.out.println(card);
//        System.out.println(card.getCardRank().getCardRankValue());
//    }
}
