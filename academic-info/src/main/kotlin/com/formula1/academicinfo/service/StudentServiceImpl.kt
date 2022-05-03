package com.formula1.academicinfo.service

import com.formula1.academicinfo.model.Curriculum
import com.formula1.academicinfo.model.OptionalDiscipline
import com.formula1.academicinfo.model.Teacher
import com.formula1.academicinfo.repository.OptionalDisciplineRepository
import com.formula1.academicinfo.repository.StudentRepository
import com.formula1.academicinfo.repository.TeacherRepository
import com.formula1.academicinfo.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class StudentServiceImpl (
    private val studentRepository: StudentRepository,
    private val userRepository: UserRepository,
    private val optionalDisciplineRepository: OptionalDisciplineRepository,
    private val teacherRepository: TeacherRepository

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
    override fun getOptionalDisciplines(username: String): MutableSet<OptionalDiscipline> {
        val user = this.userRepository.findUserByUsername(username)

        val student = this.studentRepository.getStudentByStudentId(user.userId)

        val facultyId = student.yearsOfStudyStudent.iterator().next().facultyYos?.facultyId;

        var teacherList = mutableSetOf<Teacher>()
        teacherList = facultyId?.let { teacherRepository.findTeachersByFacultyId(it) }!!

//        var optionals = mutableSetOf<Dis>()

        val list = mutableSetOf<OptionalDiscipline>()

        return list
    }

}


