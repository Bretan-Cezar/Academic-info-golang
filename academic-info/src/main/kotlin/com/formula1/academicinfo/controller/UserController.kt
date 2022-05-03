package com.formula1.academicinfo.controller

import com.formula1.academicinfo.dtos.UpdateDTO
import com.formula1.academicinfo.dtos.UserDto
import com.formula1.academicinfo.security.jwtutils.TokenManager
import com.formula1.academicinfo.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("user")
class UserController(
    private val userService: UserService,
    private val tokenManager: TokenManager
) {

    @PutMapping("update")
    fun update(@RequestHeader("Authorization") token : String, @RequestBody updateDTO: UpdateDTO): ResponseEntity<Any> {
        val username = tokenManager.getUsernameFromToken(token.substring(7))
        return ResponseEntity.ok(userService.update(updateDTO.email, updateDTO.phone, username))
    }

    @GetMapping("getUser/{username}")
    fun getUser(@PathVariable("username") username: String): ResponseEntity<Any> {
        return ResponseEntity.ok(userService.loadUserByUsername(username))
    }
}