package com.formula1.academicinfo.controller


import com.formula1.academicinfo.dtos.AddOptionalDto
import com.formula1.academicinfo.security.jwtutils.TokenManager
import com.formula1.academicinfo.service.StudentService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("student")
class StudentController(
    private val studentService: StudentService,
    private val tokenManager: TokenManager
) {

    @GetMapping("getOptionals/{facultyId}")
    fun getOptionals(@RequestHeader("Authorization") token : String, @PathVariable("facultyId") facultyId: Int): ResponseEntity<Any>{
        val username = tokenManager.getUsernameFromToken(token.substring(7))
        return ResponseEntity.ok(studentService.getOptionalDisciplines(username, facultyId))
    }

    @GetMapping("getSpecializations/{facultyId}")
    fun getSpecializations(@RequestHeader("Authorization") token : String, @PathVariable("facultyId") facultyId: Int): ResponseEntity<Any>{
        val username = tokenManager.getUsernameFromToken(token.substring(7))
        return ResponseEntity.ok(studentService.getSpecializations(username, facultyId))
    }

    @GetMapping("getFaculties")
    fun getFaculties(@RequestHeader("Authorization") token : String): ResponseEntity<Any>{
        val username = tokenManager.getUsernameFromToken(token.substring(7))
        return ResponseEntity.ok(studentService.getFaculties(username))
    }

    @GetMapping("getFacultiesAndSpecializations")
    fun getFacultiesAndSpecializations(@RequestHeader("Authorization") token : String): ResponseEntity<Any>{
        val username = tokenManager.getUsernameFromToken(token.substring(7))
        return ResponseEntity.ok(studentService.getFacultiesAndSpecializations(username))
    }

    @GetMapping("getGrades/{yosId}")
    fun getGrades(@RequestHeader("Authorization") token : String, @PathVariable("yosId") yosId: Int): ResponseEntity<Any>{
        val username = tokenManager.getUsernameFromToken(token.substring(7))
        return ResponseEntity.ok(studentService.getGrades(username, yosId))
    }

    @PostMapping("addOptional")
    fun addOptional(@RequestHeader("Authorization") token : String,
                    @RequestBody addOptionalDto: AddOptionalDto) : ResponseEntity<Any> {
        val username = tokenManager.getUsernameFromToken(token.substring(7))
        return ResponseEntity.ok(studentService.addOptionalDiscipline(username, addOptionalDto.disciplineName, addOptionalDto.priority))
    }

    @GetMapping("getOptionals")
    fun getOptionals() : ResponseEntity<Any> {
        return ResponseEntity.ok(studentService.getOptionals())
    }

}