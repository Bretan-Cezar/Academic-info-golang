package com.formula1.academicinfo.controller

import com.formula1.academicinfo.dtos.GetDisciplineDto
import com.formula1.academicinfo.service.CurriculumService
import com.formula1.academicinfo.service.OptionalsSelectionService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("curriculum")
class CurriculumController(
    private val curriculumService: CurriculumService
) {
    @GetMapping("getDisciplineDistribution")
    fun getDisciplineDistribution(@RequestBody getDisciplineDto: GetDisciplineDto) : ResponseEntity<Any> {
        return ResponseEntity.ok(
            curriculumService.getCurriculumById(getDisciplineDto.curriculumId).disciplines
        )
    }
}