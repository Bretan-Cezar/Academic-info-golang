package com.formula1.academicinfo.controller

import com.formula1.academicinfo.dtos.ProfileDTO
import com.formula1.academicinfo.dtos.UserDto
import com.formula1.academicinfo.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("user")
class UserController(
    private val userService: UserService
) {

    @PutMapping("update")
    fun update(@RequestBody userDto: UserDto): ResponseEntity<Any> {
        return ResponseEntity.ok(userService.update(userDto.email, userDto.phone, userDto.userId))
    }

    @GetMapping("getUser")
    fun getUser(@RequestBody profileDto: ProfileDTO): ResponseEntity<Any> {
        return ResponseEntity.ok(userService.loadUserByUsername(profileDto.username))
    }

}