package domain

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.text

interface Discipline : Entity<Discipline> {
    val disciplineId : Int
    val disciplineName : String
    val teacherId : Teacher
    val creditCount : Int
}

object Disciplines : Table<Discipline>("Discipline") {
    val disciplineId = int("DisciplineID").primaryKey().bindTo { it.disciplineId }
    val disciplineName = text("DisciplineName").bindTo { it.disciplineName }
    val teacherId = int("TeacherId").references(Teachers) { it.teacherId }
    val creditCount = int("CreditCount").bindTo { it.creditCount }
}