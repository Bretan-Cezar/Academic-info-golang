package domain

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.text

interface Teacher : Entity<Teacher> {
    val teacherId : User
    val facultyId : Int
    val degree : String
}

object Teachers : Table<Teacher>("Teacher") {
    val teacherId = int("TeacherID").primaryKey().references(Users) { it.teacherId }
    val facultyId = int("FacultyID").bindTo { it.facultyId }
    val degree = text("Degree").bindTo { it.degree }
}