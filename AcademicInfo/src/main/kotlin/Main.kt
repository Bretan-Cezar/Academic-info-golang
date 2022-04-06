import org.ktorm.database.Database
import org.ktorm.dsl.from
import org.ktorm.dsl.select
import domain.Users
import domain.ConnectionStringBuilder

fun main(args: Array<String>) {
    val database = Database.connect(
        ConnectionStringBuilder.createString("databaseinfo.txt"),
        user = "nwvqlqktmqxjcx",
        password = "e9c7dcff6424739a06c799da942397a93476ef3b124280b9b2b56c3bda69206b"
    )

//    for (row in database.from(Users).select())
//        println(row[Users.id].toString() + " " + row[Users.name].toString() + " " + row[Users.username].toString())
}
