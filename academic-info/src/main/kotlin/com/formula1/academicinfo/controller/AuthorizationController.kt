package com.formula1.academicinfo.controller

import com.formula1.academicinfo.dtos.Message
import com.formula1.academicinfo.dtos.RegisterDTO
import com.formula1.academicinfo.model.User
import com.formula1.academicinfo.security.jwtutils.JwtRequestModel
import com.formula1.academicinfo.security.jwtutils.TokenManager
import com.formula1.academicinfo.service.UserService
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("auth")
class AuthorizationController(private val userService: UserService,
                              private val authenticationManager: AuthenticationManager,
                              private val tokenManager: TokenManager,
                              private val passwordEncoder: PasswordEncoder) {

    @PostMapping("login")
    fun createToken(@RequestBody request: JwtRequestModel) : ResponseEntity<Any> {
        return try {
            authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(request.username, request.password)
            )
            val userDetails = userService.loadUserByUsername(request.username)
            val token = tokenManager.generateJwtToken(userDetails)
            ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, token).body(Message("Successful", userDetails.username))
        } catch (ex : BadCredentialsException) {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Message("Unauthorized user", ""))
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
}