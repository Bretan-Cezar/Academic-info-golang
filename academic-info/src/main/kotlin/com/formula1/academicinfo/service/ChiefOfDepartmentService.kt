package com.formula1.academicinfo.service

import com.formula1.academicinfo.dtos.OptionalDisciplineChiefDto


interface ChiefOfDepartmentService {
    fun getOptionals(username: String): MutableSet<OptionalDisciplineChiefDto>
}