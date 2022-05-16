package com.formula1.academicinfo.controller

import com.formula1.academicinfo.dtos.LoginMessageDto
import com.formula1.academicinfo.dtos.PasswordDTO
import com.formula1.academicinfo.dtos.RegisterDTO
import com.formula1.academicinfo.model.User
import com.formula1.academicinfo.security.jwtutils.JwtRequestModel
import com.formula1.academicinfo.security.jwtutils.TokenManager
import com.formula1.academicinfo.service.*
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("auth")
class AuthorizationController(
    private val userService: UserService,
    private val authenticationManager: AuthenticationManager,
    private val tokenManager: TokenManager,
    private val passwordEncoder: PasswordEncoder,
    private val teacherService: TeacherService,
    private val studentService: StudentService,
    private val chiefOfDepartmentService: ChiefOfDepartmentService,
    private val adminService: AdminService
    ) {

    @PostMapping("login")
    fun createToken(@RequestBody request: JwtRequestModel) : ResponseEntity<Any> {
        return try {
            authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(request.username, request.password)
            )
            val userDetails = userService.loadUserByUsername(request.username)
            val token = tokenManager.generateJwtToken(userDetails)
            ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, token).body(LoginMessageDto("Successful",
                getUserType(userDetails.username).type))
        } catch (ex : BadCredentialsException) {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(LoginMessageDto("Unauthorized user", ""))
        }
    }

    @PostMapping("register")
    fun register(@RequestBody registerDTO: RegisterDTO) : ResponseEntity<User> {
        val user = User()
        user.fullName = registerDTO.fullName
        user.cnp = registerDTO.cnp
        user.password = passwordEncoder.encode(registerDTO.password)
        user.dateOfBirth = registerDTO.dateOfBirth
        user.email = registerDTO.email
        user.phoneNumber = registerDTO.phoneNumber
        user.username = registerDTO.username
        return ResponseEntity.ok(userService.save(user))
    }

    private fun getUserType(username: String): UserType {
        val studentResult = checkIfUserIsStudent(username)
        if (studentResult != UserType.NONE) return studentResult
        val teacherResult = checkIfUserIsTeacher(username)
        if (teacherResult != UserType.NONE) return teacherResult
        val adminResult = checkIfUserIsAdmin(username)
        if (adminResult != UserType.NONE) return adminResult
        return UserType.NONE
    }

    private fun checkIfUserIsStudent(username: String): UserType {
        return if (studentService.checkIfUserIsStudent(username)) UserType.STUDENT else UserType.NONE
    }

    private fun checkIfUserIsTeacher(username: String): UserType {
        return if (chiefOfDepartmentService.checkIfUserIsChiefOfDepartment(username)) UserType.CHIEF else {
                if (teacherService.checkIfUserIsTeacher(username)) UserType.TEACHER else UserType.NONE
        }
    }

    private fun checkIfUserIsAdmin(username: String): UserType {
        return if (adminService.checkIfUserIsAdmin(username)) UserType.ADMIN else UserType.NONE
    }

    private enum class UserType(val type: String) {
        STUDENT("student"),
        TEACHER("teacher"),
        CHIEF("chiefOfDepartment"),
        ADMIN("admin"),
        NONE("none")
    }
}