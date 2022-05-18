package com.formula1.academicinfo.controller

import com.formula1.academicinfo.dtos.ApprovedOptionalDto
import com.formula1.academicinfo.dtos.GetDisciplineByTeacherAndYearDto
import com.formula1.academicinfo.security.jwtutils.TokenManager
import com.formula1.academicinfo.service.ChiefOfDepartmentService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("chiefOfDepartment")
class ChiefOfDepartmentController(
    private val chiefOfDepartmentService: ChiefOfDepartmentService,
    private val tokenManager: TokenManager
) {
    @GetMapping("getOptionals")
    fun getOptionals(@RequestHeader("Authorization") token : String): ResponseEntity<Any>{
        val username = tokenManager.getUsernameFromToken(token.substring(7))
        return ResponseEntity.ok(chiefOfDepartmentService.getOptionals(username))
    }

    @PutMapping("approveOptional")
    fun approveOptional(@RequestHeader("Authorization") token: String, @RequestBody approvedOptionalDto: ApprovedOptionalDto): ResponseEntity<Any>{
        val username = tokenManager.getUsernameFromToken(token.substring(7))
        return ResponseEntity.ok(chiefOfDepartmentService.approveOptional(approvedOptionalDto.oDisciplineId, approvedOptionalDto.maxAttendants))
    }

    @GetMapping("getDisciplines/{teacherId}&{yosId}")
    fun getDisciplinesGivenByTeacherInYear(@PathVariable("teacherId") teacherId: Int,
                                            @PathVariable("yosId") yosId: Int) : ResponseEntity<Any> {
        val disciplines = chiefOfDepartmentService.getDisciplinesGivenByTeacherInAYear(teacherId, yosId)

        val dtoList = mutableListOf<GetDisciplineByTeacherAndYearDto>()

        disciplines.forEach() {
            dtoList.add(GetDisciplineByTeacherAndYearDto(it.disciplineName, it.creditCount, it.isOptional))
        }

        return ResponseEntity.ok(dtoList)
    }

    @GetMapping("getTeachers/{facultyId}")
    fun getTeachersFromFaculty(@PathVariable("facultyId") facultyId: Int) : ResponseEntity<Any> {
        return ResponseEntity.ok(chiefOfDepartmentService.getTeachers(facultyId))
    }

    @GetMapping("getBestTeacher/{chiefId}")
    fun getBestTeacher(@PathVariable("chiefId") chiefId: Int) : ResponseEntity<Any> {
        return ResponseEntity.ok(chiefOfDepartmentService.getBestTeacher(chiefId))
    }

    @GetMapping("getWorstTeacher/{chiefId}")
    fun getWorstTeacher(@PathVariable("chiefId") chiefId: Int) : ResponseEntity<Any> {
        return ResponseEntity.ok(chiefOfDepartmentService.getWorstTeacher(chiefId))
    }
}
