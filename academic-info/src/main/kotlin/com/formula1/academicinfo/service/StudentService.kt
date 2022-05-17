package com.formula1.academicinfo.service

import com.formula1.academicinfo.dtos.FacultyandSpecializationDto
import com.formula1.academicinfo.dtos.GradeDto
import com.formula1.academicinfo.dtos.OptionalDisciplineDto
import com.formula1.academicinfo.model.Faculty
import com.formula1.academicinfo.model.OptionalsSelection
import com.formula1.academicinfo.model.YearOfStudy
import org.springframework.stereotype.Service

@Service
interface StudentService {
//    fun getMandatoryDisciplinesByStudentId(id: Int): List<MandatoryDiscipline>
//    fun getOptionalDisciplinesByStudentId(id: Int): List<OptionalDiscipline>

      fun getFacultiesAndSpecializations(username: String): MutableSet<FacultyandSpecializationDto>

      fun getOptionalDisciplines(username: String, facultyId: Int): MutableSet<OptionalDisciplineDto>

      fun getSpecializations(username: String, facultyId: Int): MutableSet<YearOfStudy>

      fun getFaculties(username: String): MutableSet<Faculty>

      fun getGrades(username: String, yosId: Int): MutableSet<GradeDto>

      fun addOptionalDiscipline(username: String, disciplineName: String, priority: Int) : String

      fun getOptionals(): MutableList<OptionalsSelection>

      fun checkIfUserIsStudent(username: String): Boolean
}