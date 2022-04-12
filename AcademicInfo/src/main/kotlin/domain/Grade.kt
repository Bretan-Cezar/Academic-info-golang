package domain

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.int

interface Grade : Entity<Grade> {
    val studentId : Student
    val disciplineId : Discipline
    val value : Int
}

object Grades : Table<Grade>("Grade") {
    val studentId = int("StudentID").primaryKey().references(Students) { it.studentId }
    val disciplineId = int("DisciplineID").primaryKey().references(Disciplines) { it.disciplineId }
    val value = int("Value").bindTo { it.value }
}