package com.formula1.academicinfo.dtos

import java.time.LocalDate

class UserDto {

    var userId: Int = 0

    var fullName: String = ""

    var username: String = ""

    var password: String = ""

    var cnp: String = ""

    var dateOfBirth: LocalDate? = null

    var email: String = ""

    var phone: String = ""

}