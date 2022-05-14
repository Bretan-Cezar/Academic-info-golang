package com.formula1.academicinfo.service

import com.formula1.academicinfo.model.Curriculum
import com.formula1.academicinfo.dtos.DisciplineDto
import com.formula1.academicinfo.repository.CurriculumRepository
import org.springframework.stereotype.Service

@Service
class CurriculumServiceImpl(private val curriculumRepository: CurriculumRepository) : CurriculumService {
    override fun getCurriculumById(curriculumId: Int): Curriculum {
        return curriculumRepository.findCurriculumById(curriculumId)
    }

    override fun getCurriculumsByStudentId(studentId: Int): List<Curriculum> {
        return curriculumRepository.getCurriculumByStudentId(studentId)
    }

    override fun getDisciplinesByCurriculumId(curriculumId: Int): MutableSet<DisciplineDto> {
        val curriculum = curriculumRepository.findCurriculumById(curriculumId)
        val disciplines = curriculum.disciplines

        val dtos : MutableSet<DisciplineDto> = mutableSetOf()

        for (discipline in disciplines){
            val dto = DisciplineDto()
            dto.discipline_name = discipline.disciplineName
            dto.credit_count = discipline.creditCount
            dto.discipline_type = if (discipline.isOptional) "Optional" else "Mandatory"
            dto.teacher_name = discipline.teacherDiscipline.teacherUser.fullName
            dtos.add(dto)
        }

        return dtos
    }
}