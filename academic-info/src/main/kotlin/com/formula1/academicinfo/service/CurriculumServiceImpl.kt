package com.example.demo.service

import com.formula1.academicinfo.model.OptionalDiscipline
import com.formula1.academicinfo.model.MandatoryDiscipline
import com.formula1.academicinfo.model.Discipline
import com.formula1.academicinfo.model.Curriculum
import com.formula1.academicinfo.repository.CurriculumRepository
import com.formula1.academicinfo.repository.DisciplineRepository

class CurriculumServiceImpl(

    private val disciplineRepository: DisciplineRepository,
    private val curriculumRepository: CurriculumRepository,

    ): CurriculumService {

}