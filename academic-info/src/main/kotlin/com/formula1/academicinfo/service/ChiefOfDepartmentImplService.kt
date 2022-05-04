package com.formula1.academicinfo.service

import com.formula1.academicinfo.dtos.OptionalDisciplineChiefDto
import com.formula1.academicinfo.repository.*
import org.springframework.stereotype.Service

@Service
class ChiefOfDepartmentImplService(private val optionalsDisciplineRepository: OptionalDisciplineRepository,
                                   private val disciplineRepository: DisciplineRepository,
                                   private val teacherRepository: TeacherRepository,
                                   private val userRepository: UserRepository,
                                   private val facultyRepository: FacultyRepository
): ChiefOfDepartmentService {

    override fun getOptionals(username: String): MutableSet<OptionalDisciplineChiefDto> {

        val user = this.userRepository.findUserByUsername(username)
        val teacher = this.teacherRepository.findTeacherByTeacherId(user.userId)
        val facultyId = teacher.faculty.facultyId

        val teachers = this.teacherRepository.findTeachersByFacultyId(facultyId)

        val optionals = mutableSetOf<OptionalDisciplineChiefDto>()

        for(t in teachers)
            for(discipline in t.disciplines) {
                val optional =
                    this.optionalsDisciplineRepository.getOptionalDisciplineByODisciplineId(discipline.disciplineId)
                if (!optional.approved) {

                    val o = OptionalDisciplineChiefDto()
                    val u = this.userRepository.findUserById(t.teacherId)
                    o.teacherName = u.fullName
                    o.creditCount = discipline.creditCount
                    o.oDisciplineId = discipline.disciplineId
                    o.disciplineName = discipline.disciplineName

                    optionals.add(o)
                }
            }

        return optionals
    }
}










//        val user = this.userRepository.findUserByUsername(username)
//
//        val teacher = this.teacherRepository.findTeacherByTeacherId(user.userId)
//
//        val facultyId = teacher.faculty?.facultyId
//
//        val faculty = facultyId?.let { facultyRepository.getFacultiesByFacultyId(it) }
//
//        val chiefOfDepartmentId = faculty?.teacherFaculty?.teacherId
//
//        if(chiefOfDepartmentId == teacher.teacherId){
//
//
//
//        }