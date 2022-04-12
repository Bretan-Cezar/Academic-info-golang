package domain

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.int

interface OptionalsGroup : Entity<OptionalsGroup> {
    val optionalsGroupId : Int
    val yearOfStudy : YearOfStudy
    val semesterNumber : Int
}

object OptionalsGroups : Table<OptionalsGroup>("Optionals_Group") {
    val optionalsGroupId = int("OGroupID").primaryKey().bindTo { it.optionalsGroupId }
    val yearOfStudy = int("YoSID").references(YearsOfStudy) { it.yearOfStudy }
    val semesterNumber = int("SemesterNo").bindTo { it.semesterNumber }
}