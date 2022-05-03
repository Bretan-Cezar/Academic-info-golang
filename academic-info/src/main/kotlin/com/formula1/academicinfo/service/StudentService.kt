package com.formula1.academicinfo.service

import com.formula1.academicinfo.model.Discipline
import com.formula1.academicinfo.model.OptionalDiscipline
import org.springframework.stereotype.Service

@Service
interface StudentService {
//    fun getMandatoryDisciplinesByStudentId(id: Int): List<MandatoryDiscipline>
//    fun getOptionalDisciplinesByStudentId(id: Int): List<OptionalDiscipline>

      fun getOptionalDisciplines(username: String): MutableSet<Discipline>
}