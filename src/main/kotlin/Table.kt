class Table(
    val name:String,
    val betSize:Int
) {
    fun canPlay(user: User){
        if(user.bankRoll >= this.betSize) true else false
    }
}