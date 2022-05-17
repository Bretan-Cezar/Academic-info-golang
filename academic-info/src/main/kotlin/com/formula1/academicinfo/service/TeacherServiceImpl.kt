package com.formula1.academicinfo.service

import com.formula1.academicinfo.dtos.StudentDto
import com.formula1.academicinfo.model.Discipline
import com.formula1.academicinfo.model.Grade
import com.formula1.academicinfo.model.OptionalDiscipline
import com.formula1.academicinfo.model.composite.GradeId
import com.formula1.academicinfo.repository.*
import org.springframework.stereotype.Service

@Service
class TeacherServiceImpl(

    private val optionalsDisciplineRepository: OptionalDisciplineRepository,
    private val disciplineRepository: DisciplineRepository,
    private val teacherRepository: TeacherRepository,
    private val userRepository: UserRepository,
    private val gradeRepository: GradeRepository,
    private val studentRepository: StudentRepository

): TeacherService {
    override fun proposeOptional(disciplineName: String, creditCount: Int, username: String): String {

        val user = this.userRepository.findUserByUsername(username)

        val teacher = this.teacherRepository.findTeacherByTeacherId(user.userId)

//        if(disciplineRepository.findDisciplineByDisciplineName(disciplineName) == null) {
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
//        }
//        else {
//            return "There already exists an optional with the given name!"
//        }
        return "Optional added successfully!"
    }

    override fun getDisciplines(username: String): MutableSet<Discipline> {
        val user = this.userRepository.findUserByUsername(username)

        val teacher = this.teacherRepository.findTeacherByTeacherId(user.userId)

        return teacher.disciplines

    }

    override fun getStudentsForDiscipline(username: String, disciplineId: Int): MutableSet<StudentDto> {
        val user = this.userRepository.findUserByUsername(username)

        val teacher = this.teacherRepository.findTeacherByTeacherId(user.userId)

        val discipline = this.disciplineRepository.findDisciplineByDisciplineId(disciplineId)

        val curriculums = discipline.curriculums

        val result = mutableSetOf<StudentDto>()

        for(c in curriculums){
            val u = this.userRepository.findUserById(c.curriculumYos.studentYos.studentId)

            /// daca vrem sa afisam doar studentii fara note de la acea materie
//            val stud = this.studentRepository.getStudentByStudentId(u.userId)

//            if(stud.grades.size == 0) {
                val s = StudentDto()
                s.studentId = u.userId
                s.studentName = u.fullName
                s.group = u.student?.group.toString()

                result.add(s)
//            }
        }

        return result
    }

    override fun addGrade(disciplineId: Int, studentId: Int, value: Int): String {

        val discipline = this.disciplineRepository.findDisciplineByDisciplineId(disciplineId)

        val student = this.studentRepository.getStudentByStudentId(studentId)

        if(value >= 4){
            if(value <= 10){

                return if(this.gradeRepository.existsGradeById(GradeId(studentId, disciplineId))){
                    "You cannot assign a grade for this student! It already has one!"
                } else{

                    val grade = Grade()
                    grade.gradeId = GradeId(studentId, disciplineId)
                    grade.value = value
                    grade.gradeDiscipline = discipline
                    grade.gradeStudent = student
                    this.gradeRepository.save(grade)

                    "Grade added successfully!"
                }
            } else {
                return "Invalid grade!"
            }
        } else{
            return "Invalid grade!"
        }
    }

    override fun checkIfUserIsTeacher(username: String): Boolean {
        return teacherRepository.findTeacherByUsername(username) != null
    }

}