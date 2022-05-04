package com.formula1.academicinfo.controller


import com.formula1.academicinfo.dtos.ProposeOptionalDto
import com.formula1.academicinfo.dtos.UpdateDTO
import com.formula1.academicinfo.dtos.UserDto
import com.formula1.academicinfo.security.jwtutils.TokenManager
import com.formula1.academicinfo.service.StudentService
import com.formula1.academicinfo.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("student")
class StudentController(
    private val studentService: StudentService,
    private val tokenManager: TokenManager
) {

    @GetMapping("getOptionals/{username}/{facultyId}")
    fun getOptionals(@PathVariable("username") username: String, @PathVariable("facultyId") facultyId: Int): ResponseEntity<Any>{
        return ResponseEntity.ok(studentService.getOptionalDisciplines(username, facultyId))
    }

    @GetMapping("getSpecializations/{username}/{facultyId}")
    fun getSpecializations(@PathVariable("username") username: String, @PathVariable("facultyId") facultyId: Int): ResponseEntity<Any>{
        return ResponseEntity.ok(studentService.getSpecializations(username, facultyId))
    }

    @GetMapping("getFaculties/{username}")
    fun getFaculties(@PathVariable("username") username: String): ResponseEntity<Any>{
        return ResponseEntity.ok(studentService.getFaculties(username))
    }

    @GetMapping("getFacultiesAndSpecializations/{username}")
    fun getFacultiesAndSpecializations(@PathVariable("username") username: String): ResponseEntity<Any>{
        return ResponseEntity.ok(studentService.getFacultiesAndSpecializations(username))
    }

    @GetMapping("getGrades/{username}/{yosId}")
    fun getGrades(@PathVariable("username") username: String, @PathVariable("yosId") yosId: Int): ResponseEntity<Any>{
        return ResponseEntity.ok(studentService.getGrades(username, yosId))
    }

}