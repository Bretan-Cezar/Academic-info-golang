package com.formula1.academicinfo.service

import com.formula1.academicinfo.dtos.DisciplineDto

interface CurriculumService {
    fun getDisciplinesByCurriculumId(curriculumId: Int): MutableSet<DisciplineDto>
}