package com.formula1.academicinfo.service

import com.formula1.academicinfo.model.Curriculum
import com.formula1.academicinfo.repository.CurriculumRepository
import com.formula1.academicinfo.repository.DisciplineRepository
import org.springframework.stereotype.Service

@Service
class CurriculumServiceImpl(
    private val curriculumRepository: CurriculumRepository
    ): CurriculumService {
    override fun getCurriculumById(curriculumId: Int): Curriculum {
        return curriculumRepository.findCurriculumById(curriculumId)
    }

    override fun getCurriculumsByStudentId(studentId: Int): List<Curriculum> {
        return curriculumRepository.getCurriculumByStudentId(studentId)
    }

}