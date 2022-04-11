package domain

import domain.Users.primaryKey
import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.date
import org.ktorm.schema.int
import org.ktorm.schema.text

interface Student : Entity<Student> {
    val studentId : User
    val group : String
    val yearOfStudy: Int
    val fundingLevel: String
}

object Students : Table<Student>("Student") {
    val id = int("StudentID").primaryKey().references(Users) { it.studentId }
    val name = text("Group").bindTo { it.group }
    val yearOfStudy = int("YearOfStudy").bindTo { it.yearOfStudy }
    val fundingLevel = text("FundingLevel").bindTo { it.fundingLevel }
}