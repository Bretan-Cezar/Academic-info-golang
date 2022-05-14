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

    @PostMapping("addOptional/{username}")
    fun addOptional(@PathVariable("username") username: String,
                    @RequestBody addOptionalDto: AddOptionalDto) : ResponseEntity<Any> {
        return ResponseEntity.ok(studentService.addOptionalDiscipline(username, addOptionalDto.disciplineName, addOptionalDto.priority))
    }

    @GetMapping("getOptionals/")
    fun getOptionals() : ResponseEntity<Any> {
        return ResponseEntity.ok(studentService.getOptionals())
    }

}