package com.formula1.academicinfo.service

import com.formula1.academicinfo.model.OptionalDiscipline
import com.formula1.academicinfo.repository.DisciplineRepository
import com.formula1.academicinfo.repository.OptionalDisciplineRepository
import com.formula1.academicinfo.repository.OptionalsGroupRepository
import com.formula1.academicinfo.repository.TeacherRepository

class TeacherServiceImpl(

    private val optionalsDisciplineRepository: OptionalDisciplineRepository,
    private val disciplineRepository: DisciplineRepository,
    private val teacherRepository: TeacherRepository,
    private val optionalsGroupRepository: OptionalsGroupRepository

): TeacherService {
    override fun proposeOptional(optional: OptionalDiscipline): Int {
        //TODO Implementation

        return 0
    }


}