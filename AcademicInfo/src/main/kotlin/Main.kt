import domain.Student
import org.ktorm.database.Database
import org.ktorm.dsl.associate
import org.ktorm.dsl.from
import org.ktorm.dsl.select
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar
import java.sql.DriverManager

fun main(args: Array<String>) {

//    val jdbcUrl = "jdbc:postgresql://localhost:5432/AcademicInfo"
//
//    val connection = DriverManager
//        .getConnection(jdbcUrl, "formula1", "formula1")

    val jdbcUrl = "jdbc:postgresql://localhost:5432/AcademicInfo"
    val database = Database.connect(url=jdbcUrl, user = "formula1", password = "formula1")


    val t = object : Table<Nothing>("Student") {
        val id2 = int("ID`").primaryKey()
        val name2 = varchar("Name")
    }

    val configs = database.from(t).select().associate { row -> row[t.id2] to row[t.name2] }
    println(configs)
}
