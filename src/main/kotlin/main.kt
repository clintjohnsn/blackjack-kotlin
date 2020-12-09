import java.lang.Exception

fun main(args: Array<String>) {

    // mock user data
    val UsersList = mutableListOf<User>(
        User("tony.stark@marvel.com", "edward", 1500, "Iron Man", "..."),
        User("donald.blake@marvel.com", "hammer", 10, "Thor", "..."),
        User("bruce.banner@marvel.com", "anger", 500, "Hulk", "..."),
        User("steve.rogers@marvel.com", "shield", 100, "Captain America", "..."),
        User("natasha@marvel.com    ", "Наташа", 200, "Black Widow", "..."),
    )
    // mock table data
    val TableList = mutableListOf<Table>(
        Table("Ego",1),
        Table("Earth",2),
        Table("Asgard",5),
        Table("Vormir",20),
        Table("Titan",100)
    )

    //assume user
    val user = UsersList[0]
    // assume user selects table
    val table=TableList[4]

    println("User: ${user.userName},Bankroll: ${user.bankRoll}" )
    println("Table: ${table.name},Betsize: ${table.betSize}")

    var ans = "y"
    while(ans=="y"){
        val game = Game(user,table)
        game.play()
        println("Play again? y/n: Bankroll ${user.bankRoll}")
        ans = readLine()!!
    }


    println("User: ${user.userName} Bankroll: ${user.bankRoll}" )

}