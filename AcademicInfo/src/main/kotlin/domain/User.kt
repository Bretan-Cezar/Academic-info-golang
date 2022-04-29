package domain

import org.ktorm.entity.Entity
import org.ktorm.schema.*
import java.time.LocalDate

interface User : Entity<User> {
    val id: Int
    var name: String
    var username: String
    var password: String
    var cnp: String
    var dob: LocalDate
    var email: String
    var phone: String
}

object Users : Table<User>("User") {
    val id = int("UserID").primaryKey().bindTo { it.id }
    val name = text("FullName").bindTo { it.name }
    val username = text("Username").bindTo { it.username }
    val password = text("Password").bindTo { it.password }
    val cnp = text("CNP").bindTo { it.cnp }
    val dob = date("DateOfBirth").bindTo { it.dob }
    val email = text("Email").bindTo { it.email }
    val phone = text("PhoneNumber").bindTo { it.phone }
}