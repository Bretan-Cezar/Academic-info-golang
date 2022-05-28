package com.formula1.academicinfo.service

import com.formula1.academicinfo.dtos.OptionalDisciplineChiefDto
import com.formula1.academicinfo.dtos.TeacherPerformanceDto
import com.formula1.academicinfo.model.Discipline
import com.formula1.academicinfo.model.Teacher


interface ChiefOfDepartmentService {
    fun getOptionals(username: String): MutableSet<OptionalDisciplineChiefDto>

    fun approveOptional(optionalId: Int, maxAttendants: Int): String

    fun getDisciplinesGivenByTeacher(teacherId: Int) : List<Discipline>

    fun getTeachers(facultyId: Int) : Set<Teacher>

    fun checkIfUserIsChiefOfDepartment(username: String) : Boolean

    fun getBestTeacher(username: String): TeacherPerformanceDto

    fun getWorstTeacher(username: String): TeacherPerformanceDto
}