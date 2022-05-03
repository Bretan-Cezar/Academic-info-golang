package com.example.demo.service

import com.formula1.academicinfo.repository.CurriculumRepository
import com.formula1.academicinfo.repository.DisciplineRepository

class CurriculumServiceImpl(

    private val disciplineRepository: DisciplineRepository,
    private val curriculumRepository: CurriculumRepository,

    ): CurriculumService {

}