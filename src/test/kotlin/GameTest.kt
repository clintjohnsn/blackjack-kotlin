
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GameTest {

    lateinit var user:User
    lateinit var table:Table
    lateinit var game:Game


    @BeforeEach
    fun `initialize`(){
        user = User("tony.stark@marvel.com", "edward", 1500, "Iron Man", "...")
        table=Table("Titan",100)
        game =Game(user,table)
        game.deck.cards.clear()
    }


    @Test
    fun `game is initialized properly`() {
        val game =Game(user,table)
        assertEquals(48,game.deck.cards.size)
        assertEquals(2,game.dealer.hand.size)
        assertEquals(2,game.dealer.hand.size)
        assertEquals(false,game.dealer.busted)
        assertEquals(false,game.player.busted)
        assertEquals("Titan",game.table.name)
        assertEquals(100,game.table.betSize)
        assertEquals("tony.stark@marvel.com",game.user.email)
        assertEquals(1500,game.user.bankRoll)
        assertEquals(null,game.winner)
    }

    @Test
    fun `cards are dealt properly`(){
        //setup
        val c1 = Card(Suits.Clubs,"6")
        val c2 = Card(Suits.Clubs,"10")
        val c3 = Card(Suits.Clubs,"K")
        val c4=  Card(Suits.Diamonds,"9")
        // action
        game.player.initHand(c1,c2)
        game.dealer.initHand(c3,c4)
        //assert
        assertEquals(16,game.player.handValue)
        assertEquals(19,game.dealer.handValue)
        assertEquals(c1,game.player.hand[0])
        assertEquals(c2,game.player.hand[1])
        assertEquals(c3,game.dealer.hand[0])
        assertEquals(c4,game.dealer.hand[1])
    }

    @Test
    fun `dealer busts`(){
        //setup
        val c1 = Card(Suits.Clubs,"9")
        val c2 = Card(Suits.Clubs,"10")
        val c3 = Card(Suits.Clubs,"K")
        val c4=  Card(Suits.Diamonds,"6")
        game.player.initHand(c1,c2)
        game.dealer.initHand(c3,c4)
        game.deck.cards.addAll(listOf(
            Card(Suits.Spades,"10")
        ))

        //action
        game.dealerTurn()

        //assert
        assertEquals(26,game.dealer.handValue)
        assertEquals(true,game.dealer.busted)
    }

    @Test
    fun `dealer wins`(){

    }

    @Test
    fun `dealer loses`(){

    }

    @Test
    fun `draw or push state`(){

    }
}