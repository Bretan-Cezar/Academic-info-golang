package com.formula1.academicinfo.service

import com.formula1.academicinfo.dtos.DisciplineDto
import com.formula1.academicinfo.model.Curriculum

interface CurriculumService {
    fun getCurriculumById(curriculumId: Int) : Curriculum

    fun getCurriculumsByStudentId(studentId: Int) : List<Curriculum>

    fun getDisciplinesByCurriculumId(curriculumId: Int): MutableSet<DisciplineDto>
}