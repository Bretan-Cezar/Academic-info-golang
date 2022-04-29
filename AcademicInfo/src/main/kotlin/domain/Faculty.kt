package domain

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.text

interface Faculty : Entity<Faculty> {
    val facultyId : Int
    val facultyName : String
    val address : String
    val email : String
    val phoneNumber : String
    val chiefTeacherId : Teacher
}

object Faculties : Table<Faculty>("Faculty") {
    val facultyId = int("FacultyID").primaryKey().bindTo { it.facultyId }
    val facultyName = text("FacultyName").bindTo { it.facultyName }
    val address = text("Address").bindTo { it.address }
    val email = text("Email").bindTo { it.email }
    val phoneNumber = text("PhoneNumber").bindTo { it.phoneNumber }
    val chiefTeacherId = int("ChiefTeacherID").references(Teachers) { it.chiefTeacherId }
}