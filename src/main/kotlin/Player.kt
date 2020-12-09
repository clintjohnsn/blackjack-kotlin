open class Player() {
    constructor(user: User):this(){
        this.user = user
    }
    var user: User = User("","",0,"dealer","");

    val hand = mutableListOf<Card>()
    var busted:Boolean = false
        get(){
            field = handValue > 21
            return field
        }
    var handValue: Int = 0
        get(){
            updateHandValue()
            return field
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
                    if(!busted) break
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