package com.example.pokerwebapp.controller.servlet;
import com.example.pokerwebapp.Deck;
import com.example.pokerwebapp.Card;

import java.io.*;
import java.util.ArrayList;

import com.example.pokerwebapp.controller.service.AccountService;
import com.example.pokerwebapp.model.entity.Account;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "demoHandEvaluationServlet", value = "/demoHandEvaluationServlet")
public class DEMOHandEvaluationServlet extends HttpServlet {


    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String rank1 = request.getParameter("rank1");
        String suit1 = request.getParameter("suit1");
        String rank2 = request.getParameter("rank2");
        String suit2 = request.getParameter("suit2");
        String rank3 = request.getParameter("rank3");
        String suit3 = request.getParameter("suit3");
        String rank4 = request.getParameter("rank4");
        String suit4 = request.getParameter("suit4");
        String rank5 = request.getParameter("rank5");
        String suit5 = request.getParameter("suit5");

        Card card1 = createCard( rank1, suit1 );
        Card card2 = createCard( rank2, suit2 );
        Card card3 = createCard( rank3, suit3 );
        Card card4 = createCard( rank4, suit4 );
        Card card5 = createCard( rank5, suit5 );


        ArrayList<Card> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);

        Deck deck = new Deck( true );
        deck.setCards( cards );
        Deck.HandType result = deck.evaluateHand();


        String handTypeStr = convertHandTypeToString( result );



        HttpSession session = request.getSession();
        session.setAttribute( "handType", handTypeStr );
        request.setAttribute( "handType", handTypeStr );

        session.setAttribute( "demoCard1", card1 );
        session.setAttribute( "demoCard2", card2 );
        session.setAttribute( "demoCard3", card3 );
        session.setAttribute( "demoCard4", card4 );
        session.setAttribute( "demoCard5", card5 );

        response.sendRedirect("handevaluatedemo.jsp");


        /*
        Account registered = AccountService.registerUser(newUser);
        if(registered==null) {
            response.sendRedirect("registernewaccount.jsp?error=1");
        } else {
            //Lets also "log-in"
            HttpSession session = request.getSession();
            registered.setPassword("");
            session.setAttribute("User", registered);
            response.sendRedirect("registrationsuccess.jsp");
        } */
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request,response);
    }

    public Card createCard( String rankStr, String suitStr ) {
        Card.Rank rank = Card.Rank.ACE;
        Card.Suit suit = Card.Suit.SPADES;

        switch( rankStr ) {
            case "2":
                rank = Card.Rank.TWO;
                break;
            case "3":
                rank = Card.Rank.THREE;
                break;
            case "4":
                rank = Card.Rank.FOUR;
                break;
            case "5":
                rank = Card.Rank.FIVE;
                break;
            case "6":
                rank = Card.Rank.SIX;
                break;
            case "7":
                rank = Card.Rank.SEVEN;
                break;
            case "8":
                rank = Card.Rank.EIGHT;
                break;
            case "9":
                rank = Card.Rank.NINE;
                break;
            case "10":
                rank = Card.Rank.TEN;
                break;
            case "J":
                rank = Card.Rank.JACK;
                break;
            case "Q":
                rank = Card.Rank.QUEEN;
                break;
            case "K":
                rank = Card.Rank.KING;
                break;
            case "A":
                rank = Card.Rank.ACE;
                break;
        }

        switch( suitStr ) {
            case "D":
                suit = Card.Suit.DIAMONDS;
                break;
            case "S":
                suit = Card.Suit.SPADES;
                break;
            case "H":
                suit = Card.Suit.HEARTS;
                break;
            case "C":
                suit = Card.Suit.CLUBS;
                break;
        }

        return new Card( rank, suit );
    }

    public String convertHandTypeToString( Deck.HandType ht ) {
        String htStr = "e";

        switch( ht ) {
            case ROYAL_FLUSH:
                htStr = "Royal Flush";
                break;
            case STRAIGHT_FLUSH:
                htStr = "Straight Flush";
                break;
            case FOUR_OF_A_KIND:
                htStr = "Four of a Kind";
                break;
            case FULL_HOUSE:
                htStr = "Full House";
                break;
            case FLUSH:
                htStr = "Flush";
                break;
            case STRAIGHT:
                htStr = "Straight";
                break;
            case THREE_OF_A_KIND:
                htStr = "Three of a Kind";
                break;
            case TWO_PAIR:
                htStr = "Two Pair";
                break;
            case PAIR:
                htStr = "Pair";
                break;
            case HIGH_CARD:
                htStr = "High Card";
                break;
            case UNRANKED:
                htStr = "Hand Evaluation Error";
                break;
        }

        return htStr;
    }

}