package com.formula1.academicinfo.service

import com.formula1.academicinfo.model.*
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

    override fun getOptionalDisciplines(username: String, facultyId: Int): MutableSet<Discipline> {
        val user = this.userRepository.findUserByUsername(username)

        val student = this.studentRepository.getStudentByStudentId(user.userId)
        val teacherList: MutableSet<Teacher> = teacherRepository.findTeachersByFacultyId(facultyId)

        val optionals = mutableSetOf<Discipline>()
        for(teacher in teacherList)
            for(optional in teacher.disciplines) {
                val optionalDiscipline = optionalDisciplineRepository.getOptionalDisciplineByODisciplineId(optional.disciplineId)
                if(optionalDiscipline.approved)
                    optionals.add(optional)
            }

        return optionals
    }

    override fun getSpecializations(username: String): MutableSet<YearOfStudy> {
        val user = this.userRepository.findUserByUsername(username)

        val student = this.studentRepository.getStudentByStudentId(user.userId)

        val yearsOfStudy = mutableSetOf<YearOfStudy>()
        for(yos in student.yearsOfStudyStudent)
            yearsOfStudy.add(yos)

        return yearsOfStudy
    }

    override fun getFaculties(username: String): MutableSet<Faculty> {
        val user = this.userRepository.findUserByUsername(username)

        val student = this.studentRepository.getStudentByStudentId(user.userId)

        val faculties = mutableSetOf<Faculty>()
        for(yos in student.yearsOfStudyStudent)
            yos.facultyYos?.let { faculties.add(it) }

        return faculties
    }
}


