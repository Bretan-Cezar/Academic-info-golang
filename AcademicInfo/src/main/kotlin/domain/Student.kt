package domain

import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

//class Student(id: Int, name: String) {
//
//
//}

open class Student(tableName: String) : Table<Nothing>(tableName) {
    val id = int("ID`").primaryKey()
    val name = varchar("Name")
}