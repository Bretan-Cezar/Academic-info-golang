package domain

import org.ktorm.entity.Entity
import org.ktorm.schema.*
import java.util.*

interface User : Entity<User> {
    val id: Int
    var name: String
    var username: String
    var password: String
    var cnp: String
    var dob: Date
    var email: String
    var phone: String
}

object Users : Table<User>("User") {
    val id = int("UserID").primaryKey()
    val name = text("FullName")
    val username = text("Username")
    val password = text("Password")
    val cnp = text("CNP")
    val dob = date("DateOfBirth")
    val email = text("Email")
    val phone = text("PhoneNumber")
}