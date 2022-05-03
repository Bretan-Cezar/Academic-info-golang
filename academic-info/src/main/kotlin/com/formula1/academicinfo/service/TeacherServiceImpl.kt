package com.formula1.academicinfo.service

import com.formula1.academicinfo.model.Discipline
import com.formula1.academicinfo.model.OptionalDiscipline
import com.formula1.academicinfo.repository.DisciplineRepository
import com.formula1.academicinfo.repository.OptionalDisciplineRepository
import com.formula1.academicinfo.repository.TeacherRepository
import com.formula1.academicinfo.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class TeacherServiceImpl(

    private val optionalsDisciplineRepository: OptionalDisciplineRepository,
    private val disciplineRepository: DisciplineRepository,
    private val teacherRepository: TeacherRepository,
    private val userRepository: UserRepository

): TeacherService {
    override fun proposeOptional(disciplineName: String, creditCount: Int, username: String): String {

        val user = this.userRepository.findUserByUsername(username)

        val teacher = this.teacherRepository.findTeacherByTeacherId(user.userId)

        if(disciplineRepository.findDisciplineByDisciplineName(disciplineName) == null) {
            if (teacher.degree == "lecturer") {
                val discipline = Discipline()
                discipline.isOptional = true
                discipline.creditCount = creditCount
                discipline.disciplineName = disciplineName
                discipline.teacherDiscipline = teacher
                this.disciplineRepository.save(discipline)

                val optionalDiscipline = OptionalDiscipline()
                optionalDiscipline.approved = false
                optionalDiscipline.oDisciplineId = discipline.disciplineId
                optionalDiscipline.optionalDiscipline = discipline
                optionalDiscipline.maxAttendants = 0

                this.optionalsDisciplineRepository.save(optionalDiscipline)
            }
            else {
                return "You don't have permission to propose optionals!"
            }
        }
        else {
            return "There already exists an optional with the given name!"
        }
        return "Optional added successfully!"
    }

}