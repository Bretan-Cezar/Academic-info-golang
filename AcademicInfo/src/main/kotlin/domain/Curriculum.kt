package domain

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.int

interface Curriculum : Entity<Curriculum> {
    val curriculumId : Int
    val yearOfStudyId : YearOfStudy
    val specialisationId : Specialisation
}

object Curriculums : Table<Curriculum>("Curriculum") {
    val curriculumId = int("CurriculumID").primaryKey().bindTo { it.curriculumId }
    val yearOfStudyId = int("YoSID").references(YearsOfStudy) { it.yearOfStudyId }
    val specialisationId = int("SpecID").references(Specialisations) { it.specialisationId }
}
