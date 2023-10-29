package com.example.pokerwebapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Player {

    private String name;
    private int balance;
    private List<Card> hand;
    private boolean isFolded = false;
    private int totalRoundBet; // how much this player has bet this round; reset after every round

    private int raiseAmount; // how much a player raises their bet by

    private boolean isChecked = false;

    public Player(String name) {
        this.name = name;
        this.balance = 500;
        this.hand = new ArrayList<Card>();
        this.totalRoundBet = 0;
    }

    public void addToPlayerHand(Card dealtCard) {
        hand.add(dealtCard);
    }

    //fix getter to return a copy not a reference
    public List<Card> getPlayerHand() {
        return hand;
    }

    public void addBalance(int addAmount) {
        balance += addAmount;
    }

    public void subBalance(int subAmount) {
        balance -= subAmount;
    }

    public int getBalance() {
        return balance;
    }

    public void setFold(boolean fold) {
        isFolded = fold;
    }

    public boolean getFold() {
        return isFolded;
    }

    public void setTotalRoundBet(int totalRoundBet) {
        this.totalRoundBet = totalRoundBet;
    }

    public int getTotalRoundBet() {
        return totalRoundBet;
    }
    public int getRaiseAmount() {
        return raiseAmount;
    }

    public void setChecked(boolean check)
    {
        isChecked=check;
    }

    public boolean getCheckedStatus()
    {
        return this.isChecked;
    }

    @Override
    public String toString() {
        return "Player name: " + this.name + "\tBalance: " + this.balance + "\tHand: " + this.hand;
    }


    public String getPlayerChoice(int open_bet) {
        while(true) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter your action(check/call/raise/fold)");
            String choice = scan.nextLine();
            if (choice.equals("fold")) {
                isFolded = true;
                return "fold";
            } else if (choice.equals("call") && this.balance >= open_bet) {
                this.balance -= open_bet;
                this.totalRoundBet += open_bet;
                return "call";
            } else if (choice.equals("raise")) {
                do {
                    System.out.println("Enter a valid raise amount");
                    raiseAmount = scan.nextInt();
                    if (raiseAmount + totalRoundBet < open_bet) {
                        System.out.println("Invalid raise amount");
                    }
                } while (this.totalRoundBet + raiseAmount < open_bet);
                this.balance -= raiseAmount;
                this.totalRoundBet += raiseAmount;
                return "raise";

            } else if (choice.equals("check") && open_bet == 0) {
                isChecked=true;
                return "check";
            }

        }
    }




    public static void main(String[] args)
    {
        Player p1 = new Player("Christoph");
        Player p2 = new Player("Alex");
        Deck deck = new Deck();
        deck.shuffleDeck();
        System.out.println(deck.getDeck());
        p1.addToPlayerHand(deck.dealCard());
        p1.addToPlayerHand(deck.dealCard());
        System.out.println(p1);
        p2.addToPlayerHand(deck.dealCard());
        p2.addToPlayerHand(deck.dealCard());
        System.out.println(p2);
        p2.getPlayerChoice(40);
        System.out.println(p2.getBalance());
        System.out.println(p2.totalRoundBet);
        p2.getPlayerChoice(40);
        System.out.println(p2.getBalance());
        System.out.println(p2.totalRoundBet);
    }
}

