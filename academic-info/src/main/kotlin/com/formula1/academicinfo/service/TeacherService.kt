package com.formula1.academicinfo.service

import com.formula1.academicinfo.dtos.ProposeOptionalDto
import com.formula1.academicinfo.model.Discipline
import com.formula1.academicinfo.model.OptionalDiscipline

interface TeacherService {
    fun proposeOptional(disciplineName: String, creditCount: Int, username: String):String
}