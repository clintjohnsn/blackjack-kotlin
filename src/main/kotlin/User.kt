class User(
    val email:String,
    val password:String,
    var bankRoll:Int,
    val userName:String,
    val avatar:String) {

    fun win(amount:Int){
        bankRoll+=amount
    }

    fun lose(amount:Int){
        bankRoll-=amount
    }
}