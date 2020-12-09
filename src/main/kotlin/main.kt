fun main(args: Array<String>) {

    val UsersList = mutableListOf<User>(
        User("tony.stark@marvel.com", "edward", 1500, "Iron Man", "..."),
        User("donald.blake@marvel.com", "hammer", 10, "Thor", "..."),
        User("bruce.banner@marvel.com", "anger", 500, "Hulk", "..."),
        User("steve.rogers@marvel.com", "shield", 100, "Captain America", "..."),
        User("natasha@marvel.com    ", "Наташа", 200, "Black Widow", "..."),
    )
    val TableList = mutableListOf<Table>(
        Table("Ego",1),
        Table("Earth",2),
        Table("Asgard",5),
        Table("Vormir",20),
        Table("Titan",100)
    )

    val User = UsersList[0]
    val Table=TableList[4]

}