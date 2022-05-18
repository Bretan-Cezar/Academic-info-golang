package com.formula1.academicinfo.controller

import com.formula1.academicinfo.dtos.AddGradeDto
import com.formula1.academicinfo.dtos.ProposeOptionalDto
import com.formula1.academicinfo.dtos.UpdateDTO
import com.formula1.academicinfo.dtos.UserDto
import com.formula1.academicinfo.security.jwtutils.TokenManager
import com.formula1.academicinfo.service.TeacherService
import com.formula1.academicinfo.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("teacher")
class TeacherController(
    private val teacherService: TeacherService,
    private val tokenManager: TokenManager
) {

    @PostMapping("proposeOptional")
    fun proposeOptional(@RequestHeader("Authorization") token: String, @RequestBody proposeOptionalDto: ProposeOptionalDto): ResponseEntity<Any>{
        val username = tokenManager.getUsernameFromToken(token.substring(7))
        return ResponseEntity.ok(teacherService.proposeOptional(proposeOptionalDto.disciplineName, proposeOptionalDto.creditCount, username))
    }

    @GetMapping("getDisciplines")
    fun getDisciplines(@RequestHeader("Authorization") token : String): ResponseEntity<Any>{
        val username = tokenManager.getUsernameFromToken(token.substring(7))
        return ResponseEntity.ok(teacherService.getDisciplines(username))
    }

    @GetMapping("getStudentsForDiscipline/{disciplineId}")
    fun getStudentsForDiscipline(@RequestHeader("Authorization") token : String, @PathVariable("disciplineId") disciplineId: Int): ResponseEntity<Any>{
        val username = tokenManager.getUsernameFromToken(token.substring(7))
        return ResponseEntity.ok(teacherService.getStudentsForDiscipline(username, disciplineId))
    }

    @PostMapping("addGrade")
    fun addGrade(@RequestBody addGradeDto: AddGradeDto): ResponseEntity<Any>{
        return ResponseEntity.ok(this.teacherService.addGrade(addGradeDto.disciplineId, addGradeDto.studentId, addGradeDto.value))
    }

}