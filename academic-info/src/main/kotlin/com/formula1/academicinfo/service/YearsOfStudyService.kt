package com.formula1.academicinfo.service

import com.formula1.academicinfo.dtos.GetYearsDto

interface YearsOfStudyService {
    fun getYearsOfStudyByUsername(username: String): MutableSet<GetYearsDto>
}