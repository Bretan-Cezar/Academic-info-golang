package com.formula1.academicinfo.service

import com.formula1.academicinfo.model.Discipline
import com.formula1.academicinfo.model.Faculty
import com.formula1.academicinfo.model.OptionalDiscipline
import com.formula1.academicinfo.model.YearOfStudy
import org.springframework.stereotype.Service

@Service
interface StudentService {
//    fun getMandatoryDisciplinesByStudentId(id: Int): List<MandatoryDiscipline>
//    fun getOptionalDisciplinesByStudentId(id: Int): List<OptionalDiscipline>

      fun getOptionalDisciplines(username: String, facultyId: Int): MutableSet<Discipline>

      fun getSpecializations(username: String): MutableSet<YearOfStudy>

      fun getFaculties(username: String): MutableSet<Faculty>
}