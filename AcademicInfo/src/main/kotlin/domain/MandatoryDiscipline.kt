package domain

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.text

interface MandatoryDiscipline : Entity<MandatoryDiscipline> {
    val mandatoryDisciplineId : Discipline
    val mandatoryDisciplineName : String
    val teacherId : Teacher
    val curriculumId : Curriculum
    val semesterNumber : Int
}

object MandatoryDisciplines : Table<MandatoryDiscipline>("Mandatory_Discipline") {
    val mandatoryDisciplineId = int("MDisciplineID").primaryKey().references(Disciplines) { it.mandatoryDisciplineId }
    val mandatoryDisciplineName = text("MDisciplineName").bindTo { it.mandatoryDisciplineName }
    val teacherId = int("TeacherID").references(Teachers) { it.teacherId }
    val curriculumId = int("CurriculumID").references(Curriculums) { it.curriculumId }
    val semesterNumber = int("SemesterNo").bindTo { it.semesterNumber }
}