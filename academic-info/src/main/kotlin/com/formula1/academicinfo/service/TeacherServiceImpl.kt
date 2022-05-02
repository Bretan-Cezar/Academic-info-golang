package com.formula1.academicinfo.service

import com.example.demo.repository.DisciplineRepository
import com.example.demo.repository.OptionalDisciplineRepository
import com.example.demo.repository.OptionalsGroupRepository
import com.example.demo.repository.TeacherRepository
import com.formula1.academicinfo.model.OptionalDiscipline

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