package domain

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.text

interface OptionalDiscipline : Entity<OptionalDiscipline> {
    val optionalDisciplineId : Discipline
    val optionalDisciplineName : String
    val teacherId : Teacher
    val maxAttendants : Int?
    val optionalsGroupId : OptionalsGroup
    val curriculumId : Curriculum?
}

object OptionalDisciplines : Table<OptionalDiscipline>("Optional_Discipline") {
    val optionalDisciplineId = int("ODisciplineID").primaryKey().references(Disciplines) { it.optionalDisciplineId }
    val optionalDisciplineName = text("ODisciplineName").bindTo { it.optionalDisciplineName }
    val teacherId = int("TeacherID").references(Teachers) { it.teacherId }
    val maxAttendants = int("MaxAttendants").bindTo { it.maxAttendants }
    val optionalsGroupId = int("OGroupID").references(OptionalsGroups) { it.optionalsGroupId }
    val curriculumId = int("CurriculumID").references(Curriculums) { it.curriculumId }
}