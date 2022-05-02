package com.formula1.academicinfo.service

import com.formula1.academicinfo.model.OptionalDiscipline

interface TeacherService {
    fun proposeOptional(optional: OptionalDiscipline):Int
}