class Card(
    val suit:Suits,
    val symbol:String,
) {
    var value:Int
    var hard:Boolean = false

    init{
        when{
            "JKQ".contains(symbol) -> value=10
            symbol=="A" -> value=11
            symbol.toInt() in 2..10 ->value=symbol.toInt()
            else -> throw Exception("invalid symbol, value cant be calculated")
        }
    }
    fun setHard(){
        hard=true
        if(symbol=="A") {
            value=1
        }
    }

    override fun toString(): String {
        return "${suit} : ${symbol}"
    }

}