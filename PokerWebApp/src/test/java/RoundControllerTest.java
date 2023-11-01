
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.example.pokerwebapp.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


public class RoundControllerTest {


    @Test
    public void testShowdown() {
        Player p1 = new Player( "player one" );
        Player p2 = new Player( "player two" );
        Player p3 = new Player( "player three" );
        ArrayList<Player> pList = new ArrayList<>();

        Deck deck = new Deck();


        pList.add( p1 );
        pList.add( p2 );
        pList.add( p3 );


        Table table = new Table( pList );

        table.dealFlop();
        table.dealTurn();
        table.dealRiver(); // table has ace, two, three, four, five
        p1.addToPlayerHand( deck.dealCard() );
        p1.addToPlayerHand( deck.dealCard() ); // player1 has six, seven, straight flush
        p2.addToPlayerHand( deck.dealCard() );
        p2.addToPlayerHand( deck.dealCard() ); // player2 has eight, nine, flush
        p3.addToPlayerHand( deck.dealCard() );
        p3.addToPlayerHand( deck.dealCard() ); // player3 has ten, jack, flush

        RoundController rc = new RoundController( table );
        assertEquals( p1, rc.showdown() );

    }

}
