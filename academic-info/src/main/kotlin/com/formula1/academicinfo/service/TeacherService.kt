package com.formula1.academicinfo.service

import com.formula1.academicinfo.dtos.StudentDto
import com.formula1.academicinfo.model.Discipline

interface TeacherService {
    fun proposeOptional(disciplineName: String, creditCount: Int, username: String):String

    fun getDisciplines(username: String): MutableSet<Discipline>

    fun getStudentsForDiscipline(username: String, disciplineId: Int): MutableSet<StudentDto>

    fun addGrade(disciplineId: Int, studentId: Int, value: Int): String

    fun checkIfUserIsTeacher(username: String) : Boolean
}