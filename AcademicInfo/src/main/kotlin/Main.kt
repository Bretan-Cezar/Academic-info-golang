import org.ktorm.database.Database
import org.ktorm.dsl.from
import org.ktorm.dsl.select
import domain.Users

fun main(args: Array<String>) {
    val database = Database.connect(
        "jdbc:postgresql://localhost:5432/AcademicInfo",
        user = "formula1",
        password = "formula1"
    )

    for (row in database.from(Users).select())
        println(row[Users.id].toString() + " " + row[Users.name].toString() + " " + row[Users.username].toString())
}
