import org.ktorm.database.Database
import org.ktorm.dsl.from
import org.ktorm.dsl.select
import domain.Users
import domain.ConnectionStringBuilder

fun main(args: Array<String>) {
    val database = Database.connect(
        ConnectionStringBuilder.createString(),
        user = "uggzmunmxiudlk",
        password = "442041d90e73cc1457c7817557b193560d379205ea68cb6fa1f5964978cd5c42"
    )

//    for (row in database.from(Users).select())
//        println(row[Users.id].toString() + " " + row[Users.name].toString() + " " + row[Users.username].toString())
}
