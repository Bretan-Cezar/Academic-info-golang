package domain

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.int

interface OptionalsSelection : Entity<OptionalsSelection> {
    val studentId : Student
    val optionalDisciplineId : OptionalDiscipline
    val priority : Int
}

object OptionalsSelections : Table<OptionalsSelection>("Optionals_Selection") {
    val studentId = int("StudentID").primaryKey().references(Students) { it.studentId }
    val optionalDisciplineId = int("ODisciplineID").primaryKey().references(OptionalDisciplines)
    { it.optionalDisciplineId}
    val priority = int("Priority").bindTo { it.priority }
}