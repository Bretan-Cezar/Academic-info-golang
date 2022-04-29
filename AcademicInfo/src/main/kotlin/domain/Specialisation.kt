package domain

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.text

interface Specialisation : Entity<Specialisation> {
    val specialisationId : Int
    val facultyId : Faculty
    val specialisationName : String
}

object Specialisations : Table<Specialisation>("Specialisation") {
    val specialisationId = int("SpecID").primaryKey().bindTo { it.specialisationId }
    val facultyId = int("FacultyID").references(Faculties) { it.facultyId }
    val specialisationName = text("SpecName").bindTo { it.specialisationName }
}