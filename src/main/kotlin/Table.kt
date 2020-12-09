class Table(
    val name:String,
    val betSize:Int
) {
    fun canPlay(user: User):Boolean{
        return if(user.bankRoll >= this.betSize) true else false
    }
}