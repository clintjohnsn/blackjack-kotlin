
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
        game.endGame(game.player)

        //assert
        assertEquals(26,game.dealer.handValue)
        assertEquals(19,game.player.handValue)
        assertEquals(true,game.dealer.busted)
        assertEquals(game.player,game.winner)
    }

    @Test
    fun `dealer wins`(){
        //setup
        val c1 = Card(Suits.Clubs,"9")
        val c2 = Card(Suits.Clubs,"10")
        val c3 = Card(Suits.Clubs,"K")
        val c4=  Card(Suits.Diamonds,"6")
        game.player.initHand(c1,c2)
        game.dealer.initHand(c3,c4)
        game.deck.cards.addAll(listOf(
            Card(Suits.Spades,"4")
        ))

        //action
        game.dealerTurn()
        game.endGame(game.player)

        //assert
        assertEquals(20,game.dealer.handValue)
        assertEquals(19,game.player.handValue)
        assertEquals(false,game.dealer.busted)
        assertEquals(game.dealer, game.winner)
    }

    @Test
    fun `dealer loses`(){
        //setup
        val c1 = Card(Suits.Clubs,"9")
        val c2 = Card(Suits.Clubs,"10")
        val c3 = Card(Suits.Clubs,"K")
        val c4=  Card(Suits.Diamonds,"4")
        game.player.initHand(c1,c2)
        game.dealer.initHand(c3,c4)
        game.deck.cards.addAll(listOf(
            Card(Suits.Spades,"3")
        ))

        //action
        game.dealerTurn()
        game.endGame(game.player)

        //assert
        assertEquals(17,game.dealer.handValue)
        assertEquals(19,game.player.handValue)
        assertEquals(false,game.dealer.busted)
        assertEquals(game.player, game.winner)
    }

    @Test
    fun `draw or push state`(){
        //setup
        val c1 = Card(Suits.Clubs,"9")
        val c2 = Card(Suits.Clubs,"10")
        val c3 = Card(Suits.Clubs,"K")
        val c4=  Card(Suits.Diamonds,"4")
        game.player.initHand(c1,c2)
        game.dealer.initHand(c3,c4)
        game.deck.cards.addAll(listOf(
            Card(Suits.Spades,"5")
        ))

        //action
        game.dealerTurn()
        game.endGame(game.player)

        //assert
        assertEquals(19,game.dealer.handValue)
        assertEquals(19,game.player.handValue)
        assertEquals(false,game.dealer.busted)
        assertEquals(null, game.winner)
    }
    @Test
    fun `both checkmate`(){
        //setup
        val c1 = Card(Suits.Clubs,"A")
        val c2 = Card(Suits.Clubs,"10")
        val c3 = Card(Suits.Clubs,"K")
        val c4=  Card(Suits.Diamonds,"A")
        game.player.initHand(c1,c2)
        game.dealer.initHand(c3,c4)
        game.deck.cards.addAll(listOf(
            Card(Suits.Spades,"5")
        ))

        //action
        game.dealerTurn()
        game.endGame(game.player)

        //assert
        assertEquals(21,game.dealer.handValue)
        assertEquals(21,game.player.handValue)
        assertEquals(false,game.dealer.busted)
        assertEquals(null, game.winner)
    }

    @Test
    fun `player wins after hitting`(){
        //setup
        val c1 = Card(Suits.Clubs,"9")
        val c2 = Card(Suits.Clubs,"10")
        val c3 = Card(Suits.Clubs,"K")
        val c4=  Card(Suits.Diamonds,"4")
        game.player.initHand(c1,c2)
        game.dealer.initHand(c3,c4)
        game.deck.cards.addAll(listOf(
            Card(Suits.Spades,"5")
        ))

        //action
        game.player.hit(game.deck)
        game.endGame(game.player)

        //assert
        assertEquals(14,game.dealer.handValue)
        assertEquals(24,game.player.handValue)
        assertEquals(true,game.player.busted)
        assertEquals(game.dealer, game.winner)
    }


    @Test
    fun `player wins after standing`(){
        //setup
        val c1 = Card(Suits.Clubs,"9")
        val c2 = Card(Suits.Clubs,"10")
        val c3 = Card(Suits.Clubs,"K")
        val c4=  Card(Suits.Diamonds,"4")
        game.player.initHand(c1,c2)
        game.dealer.initHand(c3,c4)
        game.deck.cards.addAll(listOf(
            Card(Suits.Spades,"10")
        ))

        //action
        game.player.stand(game.deck)
        game.dealerTurn()
        game.endGame(game.player)

        //assert
        assertEquals(24,game.dealer.handValue)
        assertEquals(19,game.player.handValue)
        assertEquals(true,game.dealer.busted)
        assertEquals(game.player, game.winner)
    }

    @Test
    fun `player loses after standing`(){
        //setup
        val c1 = Card(Suits.Clubs,"9")
        val c2 = Card(Suits.Clubs,"10")
        val c3 = Card(Suits.Clubs,"K")
        val c4=  Card(Suits.Diamonds,"4")
        game.player.initHand(c1,c2)
        game.dealer.initHand(c3,c4)
        game.deck.cards.addAll(listOf(
            Card(Suits.Spades,"6")
        ))

        //action
        game.player.stand(game.deck)
        game.dealerTurn()
        game.endGame(game.player)

        //assert
        assertEquals(20,game.dealer.handValue)
        assertEquals(19,game.player.handValue)
        assertEquals(false,game.player.busted)
        assertEquals(false,game.dealer.busted)
        assertEquals(game.dealer, game.winner)
    }
}