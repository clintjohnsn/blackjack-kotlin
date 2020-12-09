class Player(val user:User) {
    val hand = mutableListOf<Card>()
    var busted:Boolean = false
        get(){
            busted = handValue > 21
            return busted
        }
    var handValue: Int = 0
        get() {
            updateHandValue()
            return handValue
        }

    fun initHand(card1:Card,card2:Card){
        hand.clear()
        hand.add(card1)
        hand.add(card2)
    }

    fun updateHandValue(){
        var v = 0
        hand.forEach{
            v += it.value
        }
        handValue =v
        if(v>21){
            for(card in hand){
                if(!card.hard){
                    card.setHard()
                    if(!busted) return
                }
            }
        }
    }

    fun hit(deck:Deck){
        hand.add(deck.cards.removeFirst())
    }
    fun stand(deck:Deck){}
    fun lose(amount:Int){
        user.lose(amount)
    }
    fun win(amount:Int){
        user.win(amount)
    }
}