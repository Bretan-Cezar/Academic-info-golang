package com.formula1.academicinfo.service

import com.formula1.academicinfo.model.Curriculum
import com.formula1.academicinfo.model.OptionalDiscipline
import com.formula1.academicinfo.repository.OptionalDisciplineRepository
import com.formula1.academicinfo.repository.StudentRepository

class StudentServiceImpl (
    private val studentRepository: StudentRepository,
    private val optionalDisciplineRepository: OptionalDisciplineRepository

        ): StudentService{
//    override fun getMandatoryDisciplinesByStudentId(id: Int): List<MandatoryDiscipline> {
//        val curriculums : MutableSet<Curriculum>
//        curriculums = studentRepository.getCurriculumsByStudentId(id)
//
//        val disciplines: MutableList<MandatoryDiscipline> = arrayListOf()
//
//        for (curriculum in curriculums){
//            disciplines.addAll(mandatoryDisciplineRepository.getMandatoryDisciplinesByCurriculumId(curriculum.curriculumId))
//        }
//
//        return disciplines
//    }
//    override fun getOptionalDisciplinesByStudentId(id: Int): List<OptionalDiscipline> {
//        val curriculums : MutableSet<Curriculum>
//        curriculums = studentRepository.getCurriculumsByStudentId(id)
//
//        val disciplines: MutableList<OptionalDiscipline> = arrayListOf()
//
//        for (curriculum in curriculums){
//            disciplines.addAll(optionalDisciplineRepository.getOptionalDisciplinesByCurriculumId(curriculum.curriculumId))
//        }
//
//        return disciplines
//    }

}


