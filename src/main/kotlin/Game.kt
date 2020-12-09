import java.lang.Exception

class Game(val user: User, val table: Table) {

    val dealer = Player()
    val player = Player(user)
    val deck = Deck()
    var winner: Player? =null

    init{
        if(!table.canPlay(user)) throw Exception("not enough balance")
        deck.cards.shuffle()
        dealer.initHand(deck.cards.removeFirst(),deck.cards.removeFirst())
        player.initHand(deck.cards.removeFirst(),deck.cards.removeFirst())
    }
    fun play() {
        println("Dealer face up card - ${dealer.hand[0]}")
        playerTurn(player)
        if(!player.busted){
            dealerTurn()
        }
        endGame(player)
    }
    fun dealerTurn(){
        println("Dealer hand: - ${dealer.hand}")
        println("Dealer Score:- ${dealer.handValue}")
        while(dealer.handValue < 17){
            println("Score less than 17, dealer hits")
            dealer.hit(deck)
            println("Dealer hand: - ${dealer.hand}")
            println("Dealer Score:- ${dealer.handValue}")
        }
        if(dealer.busted) println("Busted!")
    }
    fun playerTurn(player:Player){
        println("Player hand:- ${player.hand}")
        println("Score:- ${player.handValue}")
        var ans= "s"
        if(player.handValue <21){
            println("Hit(h) or Stand(s)?")
            ans = readLine()!!
        }
        while(ans=="h" && player.handValue <21){
            player.hit(deck)
            println("Your hand:- ${player.hand}")
            println("Score:- ${player.handValue}")
            if(!player.busted){
                println("Hit(h) or Stand(s)?")
                ans = readLine()!!
            }
        }
        if(player.busted) println("Busted!")
    }
    fun endGame(player: Player){
        when{
            player.busted || (!player.busted && !dealer.busted && dealer.handValue > player.handValue)->{
                println("Player lost")
                player.lose(table.betSize)
                winner = dealer
            }
            dealer.busted || (!dealer.busted && !player.busted && dealer.handValue < player.handValue)->{
                println("Player won")
                player.win(table.betSize)
                winner = player
            }
            else->{
                println("Draw/Push")
            }
        }
    }

}