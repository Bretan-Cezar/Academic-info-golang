package domain

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.text

interface YearOfStudy : Entity<YearOfStudy> {
    val yearOfStudyId : Int
    val facultyId : Faculty
    val studyLevel : String
}

object YearsOfStudy : Table<YearOfStudy>("Year_Of_Study") {
    val yearOfStudyId = int("YoSID").primaryKey().bindTo { it.yearOfStudyId }
    val facultyId = int("FacultyID").references(Faculties) { it.facultyId }
    val studyLevel = text("StudyLevel").bindTo { it.studyLevel }
}