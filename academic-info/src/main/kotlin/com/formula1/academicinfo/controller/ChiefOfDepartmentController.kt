package com.formula1.academicinfo.controller

import com.formula1.academicinfo.dtos.ApprovedOptionalDto
import com.formula1.academicinfo.dtos.ProposeOptionalDto
import com.formula1.academicinfo.dtos.UpdateDTO
import com.formula1.academicinfo.dtos.UserDto
import com.formula1.academicinfo.security.jwtutils.TokenManager
import com.formula1.academicinfo.service.ChiefOfDepartmentService
import com.formula1.academicinfo.service.TeacherService
import com.formula1.academicinfo.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("chiefOfDepartment")
class ChiefOfDepartmentController(
    private val chiefOfDepartmentService: ChiefOfDepartmentService,
    private val tokenManager: TokenManager
) {
    @GetMapping("getOptionals/{username}")
    fun getOptionals(@PathVariable("username") username: String): ResponseEntity<Any>{
        return ResponseEntity.ok(chiefOfDepartmentService.getOptionals(username))
    }

    @PutMapping("approveOptional")
    fun approveOptional(@RequestHeader("Authorization") token: String, @RequestBody approvedOptionalDto: ApprovedOptionalDto): ResponseEntity<Any>{
        val username = tokenManager.getUsernameFromToken(token.substring(7))
        return ResponseEntity.ok(chiefOfDepartmentService.approveOptional(approvedOptionalDto.oDisciplineId, approvedOptionalDto.maxAttendants))
    }
}