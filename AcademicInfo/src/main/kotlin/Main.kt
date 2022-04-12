import domain.ConnectionStringBuilder
import org.ktorm.database.Database
import org.ktorm.logging.Slf4jLoggerAdapter
import org.slf4j.LoggerFactory

fun main(args: Array<String>) {
    // TODO ORM Classes listed below
    // Contract_Curriculum_Distribution
    // Contract_Optionals_Distribution

    val database = Database.connect(
        ConnectionStringBuilder.createString(),
        user = "uggzmunmxiudlk",
        password = "442041d90e73cc1457c7817557b193560d379205ea68cb6fa1f5964978cd5c42"
        //logger = Slf4jLoggerAdapter(LoggerFactory.getLogger("Main.kt"))
    )

//    for (row in database.from(Users).select())
//        println(row[Users.id].toString() + " " + row[Users.name].toString() + " " + row[Users.username].toString())
}
